import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

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