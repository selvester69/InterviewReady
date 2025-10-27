
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Response;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowLogRateLimiterRedis {

    private static final String REQUESTS = "requests";
    private static final String WINDOW_TIME = "window_time";
    private static final String METADATA_SUFFIX = "_metadata";
    private static final String TIMESTAMPS_SUFFIX = "_timestamps";

    private final JedisPool jedisPool;

    public SlidingWindowLogRateLimiterRedis(String host, int port) {
        this.jedisPool = new JedisPool(host, port);
    }

    public static long getCurrentTimestampInSec() {
        return System.currentTimeMillis() / 1000;
    }

    public void addUser(String userId, int requests, int windowTimeInSec) {
        try (Jedis jedis = jedisPool.getResource()) {
            String metadataKey = userId + METADATA_SUFFIX;
            Map<String, String> metadata = new HashMap<>();
            metadata.put(REQUESTS, String.valueOf(requests));
            metadata.put(WINDOW_TIME, String.valueOf(windowTimeInSec));
            jedis.hmset(metadataKey, metadata);
        }
    }

    public int[] getRateForUser(String userId) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            String metadataKey = userId + METADATA_SUFFIX;
            Map<String, String> val = jedis.hgetAll(metadataKey);
            if (val == null || val.isEmpty() || !val.containsKey(REQUESTS) || !val.containsKey(WINDOW_TIME)) {
                throw new Exception("Un-registered or invalid metadata for user: " + userId);
            }
            return new int[]{
                    Integer.parseInt(val.get(REQUESTS)),
                    Integer.parseInt(val.get(WINDOW_TIME))
            };
        }
    }

    public void removeUser(String userId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(userId + METADATA_SUFFIX, userId + TIMESTAMPS_SUFFIX);
        }
    }

    public boolean shouldAllowServiceCall(String userId) throws Exception {
        int[] rate = getRateForUser(userId);
        int maxRequests = rate[0];
        int unitTime = rate[1];

        long currentTimestamp = getCurrentTimestampInSec();
        long oldestPossibleEntry = currentTimestamp - unitTime;
        String timestampsKey = userId + TIMESTAMPS_SUFFIX;

        try (Jedis jedis = jedisPool.getResource()) {
            // Evict older entries. This is not part of the transaction in the original Python code,
            // which is a design choice to reduce transaction complexity.
            jedis.zremrangeByScore(timestampsKey, 0, oldestPossibleEntry);

            // Use a transaction to atomically add the new timestamp and get the new count.
            jedis.watch(timestampsKey);
            Transaction t = jedis.multi();

            // Add current request timestamp. The score and member are the same.
            t.zadd(timestampsKey, currentTimestamp, String.valueOf(currentTimestamp));

            // Get the count of requests in the current window. zcard is O(1).
            Response<Long> currentRequestCount = t.zcard(timestampsKey);

            List<Object> results = t.exec();

            if (results == null) {
                // The key was modified by another client after WATCH was called.
                // For simplicity, we deny the request. A more robust implementation might retry the transaction.
                return false;
            }

            long count = currentRequestCount.get();
            System.out.println("User: " + userId + ", Current request count: " + count + ", Max requests: " + maxRequests);
            return count <= maxRequests;
        } catch (JedisException e) {
            System.err.println("Jedis Exception: " + e.getMessage());
            // In case of a Redis error, it's safer to deny the request.
            return false;
        }
    }
}
