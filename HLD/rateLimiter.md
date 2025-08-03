# Rate limiter

## Problem: 
Design rate limiter — A rate limiter is a tool that monitors the number of requests per a window time a service agrees to allow. If the request count exceeds the number agreed by the service owner and the user (in a decided window time), the rate limiter blocks all the excess calls(say by throwing exceptions). The user can be a human or any other service(ex: in a micro service based architecture)



## Various Levels: 
There are different levels at which one can design rate limiters. Listing a few…

 - Rate limiter in a single machine, single threaded scenario
 - Rate limiter in a single machine, multi threaded scenario — Handling race conditions
 - Rate limiter in a distributed scenario —Distributed Cache Usage like redis
 - Rate limiter from client side —Prevent network calls from client to server for all the excess requests.

## Fixed window counters

Space Complexity: O(1) — Storing the current window count
Time Complexity: O(1) — Get and simple atomic increment operation
#### Pros
- Easy to implement
- Less memory footprint, ’cause all that is being done is storing the counts
- Can use inbuilt concurrency with redis like technologies
#### Cons
This in incorrect. Explanation: In the above case, if all the 7 requests in the 1AM-2AM bucket occurs from 1:30AM-2AM, and all the 8 requests from 2AM-3AM bucket occur from 2AM-2:30AM, then effectively we have 15(7 + 8) requests in the time range of 1:30AM-2:30AM, which is violating the condition of 10req/hr

## Sliding window logs

#### Pros
- Works perfectly
#### Cons
- High memory footprint. All the request timestamps needs to be maintained for a window time, thus requires lots of memory to handle multiple users or large window times
- High time complexity for removing the older timestamps

### Data Modelling
In memory Design (Single machine with multiple threads)

```java
class RequestTimestamps {
    private final Deque<Long> timestamps;
    private final ReentrantLock lock;
    private final int requests;
    private final int windowTimeInSec;

    public RequestTimestamps(int requests, int windowTimeInSec) {
        this.timestamps = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.requests = requests;
        this.windowTimeInSec = windowTimeInSec;
    }

    public void evictOlderTimestamps(long currentTimestamp) {
        while (!timestamps.isEmpty() && (currentTimestamp - timestamps.peekFirst() > windowTimeInSec)) {
            timestamps.pollFirst();
        }
    }

    public Deque<Long> getTimestamps() {
        return timestamps;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public int getRequests() {
        return requests;
    }
}

public class SlidingWindowLogsRateLimiter {

    private final Map<String, RequestTimestamps> ratelimiterMap;
    private final ReentrantLock lock;

    public SlidingWindowLogsRateLimiter() {
        this.ratelimiterMap = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
    }

    public void addUser(String userId, int requests, int windowTimeInSec) {
        lock.lock();
        try {
            if (ratelimiterMap.containsKey(userId)) {
                throw new IllegalArgumentException("User already present");
            }
            ratelimiterMap.put(userId, new RequestTimestamps(requests, windowTimeInSec));
        } finally {
            lock.unlock();
        }
    }

    public void removeUser(String userId) {
        lock.lock();
        try {
            ratelimiterMap.remove(userId);
        } finally {
            lock.unlock();
        }
    }

    public static long getCurrentTimestampInSec() {
        return System.currentTimeMillis() / 1000;
    }

    public boolean shouldAllowServiceCall(String userId) {
        if (!ratelimiterMap.containsKey(userId)) {
            throw new IllegalArgumentException("User is not present. Please white list and register the user for service");
        }

        RequestTimestamps userTimestamps = ratelimiterMap.get(userId);
        userTimestamps.getLock().lock();
        try {
            long currentTimestamp = getCurrentTimestampInSec();
            userTimestamps.evictOlderTimestamps(currentTimestamp);
            userTimestamps.getTimestamps().addLast(currentTimestamp);

            return userTimestamps.getTimestamps().size() <= userTimestamps.getRequests();
        } finally {
            userTimestamps.getLock().unlock();
        }
    }
}
```
The following solutions use redis based pipelines which provide ways to use optimistic locking in redis.

```java
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

```


## Sliding window counters
- This is a hybrid of Fixed Window Counters and Sliding Window logs
- Space Complexity: O(number of buckets)
- Time Complexity: O(1) — Fetch the recent bucket, increment and check against the total sum of buckets(can be stored in a totalCount variable).
#### Pros
- No large memory footprint as only the counts are stored
#### Cons
- Works only for not-so-strict look back window times, especially for smaller unit times

### Data Modelling
In memory Design (Single machine with multiple threads)

```java
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RequestCounters {
    // Every window time is broken down into bucketSize parts
    // 100 req/min translates to requests = 100 and windowTimeInSec = 60
    private final Map<Long, Integer> counts;
    private int totalCounts;
    private final int requests;
    private final int windowTimeInSec;
    private final int bucketSize; // Represents the number of buckets in a window
    private final ReentrantLock lock; // Lock specific to this RequestCounters instance

    public RequestCounters(int requests, int windowTimeInSec, int bucketSize) {
        this.counts = new ConcurrentHashMap<>();
        this.totalCounts = 0;
        this.requests = requests;
        this.windowTimeInSec = windowTimeInSec;
        this.bucketSize = bucketSize;
        this.lock = new ReentrantLock();
    }

    public RequestCounters(int requests, int windowTimeInSec) {
        this(requests, windowTimeInSec, 10); // Default bucket size
    }

    // Gets the bucket for the timestamp
    // The bucket is the start timestamp of the bucket
    public long getBucket(long timestamp) {
        // Calculate the duration of each bucket
        double bucketDuration = (double) windowTimeInSec / bucketSize;
        return (long) ((timestamp / bucketDuration) * bucketDuration);
    }

    // Gets the oldest valid bucket timestamp corresponding to the current time window
    private long getOldestValidBucket(long currentTimestamp) {
        return getBucket(currentTimestamp - windowTimeInSec);
    }

    // Remove all the older buckets that are not relevant anymore
    public void evictOlderBuckets(long currentTimestamp) {
        // This method is expected to be called with the instance's lock held by the caller
        long oldestValidBucket = getOldestValidBucket(currentTimestamp);
        Iterator<Map.Entry<Long, Integer>> iterator = counts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            if (entry.getKey() < oldestValidBucket) {
                totalCounts -= entry.getValue();
                iterator.remove();
            }
        }
    }

    // Public methods to allow the RateLimiter to interact with this object
    public int getTotalCounts() {
        return totalCounts;
    }

    public int getRequests() {
        return requests;
    }

    public Map<Long, Integer> getCounts() {
        return counts;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    // Method to increment total counts, used by the rate limiter
    public void incrementTotalCounts() {
        this.totalCounts++;
    }
}
```
```java
public class SlidingWindowCounterRateLimiter {

    private final Map<String, RequestCounters> ratelimiterMap;
    private final ReentrantLock serviceLock; // Lock for modifications to ratelimiterMap (addUser, removeUser)

    public SlidingWindowCounterRateLimiter() {
        this.ratelimiterMap = new ConcurrentHashMap<>();
        this.serviceLock = new ReentrantLock();
    }

    /**
     * Adds a new user with a specified request rate.
     *
     * @param userId          The ID of the user.
     * @param requests        The maximum number of requests allowed within the window.
     * @param windowTimeInSec The time window in seconds.
     * @throws IllegalArgumentException if the user is already present.
     */
    public void addUser(String userId, int requests, int windowTimeInSec) {
        serviceLock.lock();
        try {
            if (ratelimiterMap.containsKey(userId)) {
                throw new IllegalArgumentException("User " + userId + " is already present.");
            }
            ratelimiterMap.put(userId, new RequestCounters(requests, windowTimeInSec));
        } finally {
            serviceLock.unlock();
        }
    }

    /**
     * Adds a new user with default rate limits (100 req/min).
     *
     * @param userId The ID of the user.
     * @throws IllegalArgumentException if the user is already present.
     */
    public void addUser(String userId) {
        addUser(userId, 100, 60); // Default of 100 req/minute
    }

    /**
     * Removes a user from the rate limiter.
     *
     * @param userId The ID of the user to remove.
     */
    public void removeUser(String userId) {
        serviceLock.lock();
        try {
            ratelimiterMap.remove(userId);
        } finally {
            serviceLock.unlock();
        }
    }

    /**
     * Gets the current timestamp in seconds.
     * This is a utility method.
     *
     * @return The current timestamp in seconds.
     */
    public long getCurrentTimestampInSec() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Determines if a service call should be allowed for a given user based on rate limits.
     *
     * @param userId The ID of the user.
     * @return true if the call is allowed, false otherwise.
     * @throws IllegalArgumentException if the user is not registered.
     */
    public boolean shouldAllowServiceCall(String userId) {
        RequestCounters userTimestamps = ratelimiterMap.get(userId);

        if (userTimestamps == null) {
            throw new IllegalArgumentException("User " + userId + " is not present in the rate limiter.");
        }

        userTimestamps.getLock().lock(); // Acquire lock for this specific user's counters
        try {
            long currentTimestamp = getCurrentTimestampInSec();

            // remove all the existing older timestamps
            userTimestamps.evictOlderBuckets(currentTimestamp);

            long currentBucket = userTimestamps.getBucket(currentTimestamp);

            // Increment count for the current bucket
            // If the key doesn't exist, it puts 1. If it exists, it increments.
            userTimestamps.getCounts().merge(currentBucket, 1, Integer::sum);
            userTimestamps.incrementTotalCounts(); // Increment the total count for this user

            if (userTimestamps.getTotalCounts() > userTimestamps.getRequests()) {
                return false;
            }
            return true;
        } finally {
            userTimestamps.getLock().unlock(); // Release lock for this specific user's counters
        }
    }
}
```

### Sliding Window Counters — Redis Design

```java
import redis.clients.jedis.Jedis;

public class RedisConnection {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 6379;
    private static final int DEFAULT_DB = 0;

    /**
     * Establishes and returns a Jedis connection to Redis.
     * @param host Redis host.
     * @param port Redis port.
     * @param db Redis database index.
     * @return A Jedis connection instance.
     */
    public static Jedis getConnection(String host, int port, int db) {
        Jedis jedis = new Jedis(host, port);
        jedis.select(db);
        return jedis;
    }

    /**
     * Establishes and returns a Jedis connection to Redis using default parameters.
     * @return A Jedis connection instance.
     */
    public static Jedis getConnection() {
        return getConnection(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DB);
    }
}
```

SlidingWindowCounterRateLimiter.java

```java
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SlidingWindowCounterRateLimiter {

    /**
     * Representation of data stored in Redis
     *
     * metadata
     * --------
     * "userid_metadata": {
     * "requests": 2,
     * "window_time": 30
     * }
     *
     * counts
     * -------
     * "userid_counts": {
     * "bucket1": 2,
     * "bucket2": 3
     * }
     */
    public static final String REQUESTS_FIELD = "requests"; // key in the metadata representing the max number of requests
    public static final String WINDOW_TIME_FIELD = "window_time"; // key in the metadata representing the window time
    public static final String METADATA_SUFFIX = "_metadata"; // metadata suffix
    public static final String COUNTS_SUFFIX = "_counts"; // count buckets suffix

    private final int bucketSize; // in seconds
    private final Jedis jedis; // Redis connection

    public SlidingWindowCounterRateLimiter(int bucketSize) {
        this.bucketSize = bucketSize;
        this.jedis = RedisConnection.getConnection(); // Use the helper class
    }

    public SlidingWindowCounterRateLimiter() {
        this(10); // Default bucket size
    }

    // Current timestamp in seconds.
    public long getCurrentTimestampInSec() {
        return System.currentTimeMillis() / 1000;
    }

    public long getBucket(long timestamp, int windowTimeInSec) {
        double factor = (double) windowTimeInSec / this.bucketSize;
        return (long) ((timestamp / factor) * factor);
    }

    /**
     * Adds a new user's rate of requests to be allowed.
     * Uses Redis hashes to store the user metadata.
     *
     * @param userId          The ID of the user.
     * @param requests        The maximum number of requests allowed within the window.
     * @param windowTimeInSec The time window in seconds.
     */
    public void addUser(String userId, int requests, int windowTimeInSec) {
        // TODO: Make sure that the given windowTimeInSec is a multiple of bucketSize (validation can be added here)
        String metadataKey = userId + METADATA_SUFFIX;
        Map<String, String> metadata = new HashMap<>();
        metadata.put(REQUESTS_FIELD, String.valueOf(requests));
        metadata.put(WINDOW_TIME_FIELD, String.valueOf(windowTimeInSec));
        jedis.hmset(metadataKey, metadata);
    }

    /**
     * Adds a new user with default rate limits (100 req/min).
     * @param userId The ID of the user.
     */
    public void addUser(String userId) {
        addUser(userId, 100, 60); // Default of 100 req/minute
    }

    /**
     * Get the user metadata storing the number of requests per window time.
     *
     * @param userId The ID of the user.
     * @return A two-element array: [allowedRequests, windowTime].
     * @throws IllegalArgumentException if the user is unregistered.
     */
    public int[] getRateForUser(String userId) {
        String metadataKey = userId + METADATA_SUFFIX;
        Map<String, String> val = jedis.hgetAll(metadataKey);

        if (val == null || val.isEmpty()) {
            throw new IllegalArgumentException("Un-registered user: " + userId);
        }

        try {
            int requests = Integer.parseInt(val.get(REQUESTS_FIELD));
            int windowTime = Integer.parseInt(val.get(WINDOW_TIME_FIELD));
            return new int[]{requests, windowTime};
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid metadata for user: " + userId, e);
        }
    }

    /**
     * Removes a user's metadata and timestamps.
     *
     * @param userId The ID of the user to remove.
     */
    public void removeUser(String userId) {
        jedis.del(userId + METADATA_SUFFIX, userId + COUNTS_SUFFIX);
    }

    /**
     * Atomically increments hash key val by unit and returns. Uses optimistic locking
     * over userId + self.COUNTS redis key.
     * This method is designed to be called within a Jedis transaction.
     *
     * @param userId       The ID of the user.
     * @param bucket       The bucket timestamp to increment.
     * @param transaction  The Jedis Transaction object.
     * @return A list of all values (counts) of the buckets after the increment.
     */
    private List<String> _incrementAHashKeyValByUnitAtomically(String userId, long bucket, Transaction transaction) {
        String countsKey = userId + COUNTS_SUFFIX;

        // Watch the countsKey to ensure no external changes interfere with the transaction
        transaction.watch(countsKey);

        // Get the current count for the bucket
        String countStr = jedis.hget(countsKey, String.valueOf(bucket));
        int currentBucketCount = (countStr == null) ? 0 : Integer.parseInt(countStr);

        // Start transaction block
        transaction.multi();
        transaction.hset(countsKey, String.valueOf(bucket), String.valueOf(currentBucketCount + 1));
        transaction.hvals(countsKey); // Get all values after the update

        // This method does not execute the transaction. It prepares it.
        // The transaction is executed by the caller (shouldAllowServiceCall).
        return null; // Return value is not used here, as exec() returns the result
    }

    /**
     * Deciding if the rate has been crossed.
     * We're using Redis hashes to store the counts.
     *
     * @param userId The ID of the user.
     * @return true if the service call is allowed, false otherwise.
     * @throws IllegalArgumentException if the user is not registered.
     */
    public boolean shouldAllowServiceCall(String userId) {
        int[] rateInfo = getRateForUser(userId);
        int allowedRequests = rateInfo[0];
        int windowTime = rateInfo[1];

        String countsKey = userId + COUNTS_SUFFIX;

        // Evict older entries outside the transaction if possible to reduce transaction size
        Set<String> allBucketKeys = jedis.hkeys(countsKey);
        long currentTimestamp = getCurrentTimestampInSec();
        long oldestPossibleEntry = currentTimestamp - windowTime;

        List<String> bucketsToBeDeleted = allBucketKeys.stream()
                .map(Long::parseLong)
                .filter(bucket -> bucket < oldestPossibleEntry)
                .map(String::valueOf) // Convert back to string for hdel
                .collect(Collectors.toList());

        if (!bucketsToBeDeleted.isEmpty()) {
            // hdel command accepts multiple fields
            jedis.hdel(countsKey, bucketsToBeDeleted.toArray(new String[0]));
        }

        long currentBucket = getBucket(currentTimestamp, windowTime);

        // Transaction to atomically increment the current bucket and get all counts
        // The transaction observes (watches) the relevant keys before starting.
        // If any of the watched keys change before EXEC, the transaction is aborted.
        List<Object> transactionResults = jedis.runInTransaction(
                (Transaction transaction) -> {
                    // This lambda defines the operations within the transaction
                    _incrementAHashKeyValByUnitAtomically(userId, currentBucket, transaction);
                    // The _incrementAHashKeyValByUnitAtomically method already adds commands to the transaction,
                    // so we just need to return null here, as the result will be from transaction.exec()
                    return null;
                },
                countsKey, userId + METADATA_SUFFIX // Watch these keys
        );

        // The transactionResults list will contain the results of the commands executed within the transaction.
        // The last command added was hvals, so its result will be the last in the list.
        if (transactionResults == null || transactionResults.isEmpty()) {
            // Transaction might have aborted or had an issue
            // This could happen if a watched key changed. In such cases, the operation needs to be retried.
            // For a production system, you'd add retry logic here.
            System.err.println("Redis transaction failed or aborted for user: " + userId);
            // Re-evaluate or throw an exception, depending on desired retry/error handling.
            // For this conversion, we'll assume it's a temporary failure and deny for safety.
            return false;
        }

        @SuppressWarnings("unchecked") // Cast is safe if hvals returns List<String>
        List<String> requestsStrings = (List<String>) transactionResults.get(transactionResults.size() - 1);

        long totalRequestsInWindow = requestsStrings.stream()
                .mapToLong(Long::parseLong)
                .sum();

        return totalRequestsInWindow <= allowedRequests;
    }
}
```


## More details on below blog 

#### https://www.figma.com/blog/an-alternative-approach-to-rate-limiting/