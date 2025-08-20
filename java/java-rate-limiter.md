## Java: Basic Rate Limiter

```java
import java.util.concurrent.TimeUnit;

/**
 * A basic, non-thread-safe implementation of a rate limiter using the
 * Token Bucket algorithm.
 *
 * The Token Bucket algorithm works by simulating a bucket that holds tokens.
 * A fixed number of tokens are added to the bucket at a regular interval.
 * Each incoming request consumes one token. If the bucket is empty,
 * the request is denied.
 *
 * This example is not thread-safe and is designed to illustrate the basic
 * logic without synchronization overhead.
 */
public class BasicRateLimiter {

    private final long capacity;
    private final long refillRate; // tokens per second
    private long availableTokens;
    private long lastRefillTimestamp;

    /**
     * @param capacity The maximum number of tokens the bucket can hold.
     * @param refillRate The rate at which tokens are added to the bucket (per second).
     */
    public BasicRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.availableTokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    /**
     * Attempts to acquire a token for a request.
     * @return true if a token was acquired (request is allowed), false otherwise.
     */
    public boolean tryAcquire() {
        refill();
        if (availableTokens > 0) {
            availableTokens--;
            return true;
        }
        return false;
    }

    /**
     * Refills the bucket with new tokens based on the time elapsed.
     */
    private void refill() {
        long now = System.nanoTime();
        long timeSinceLastRefill = now - lastRefillTimestamp;
        long tokensToAdd = (timeSinceLastRefill * refillRate) / TimeUnit.SECONDS.toNanos(1);
        
        if (tokensToAdd > 0) {
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter with a capacity of 5 and a refill rate of 1 token per second
        BasicRateLimiter rateLimiter = new BasicRateLimiter(5, 1);

        System.out.println("Attempting 10 requests...");
        for (int i = 1; i <= 10; i++) {
            System.out.print("Request " + i + ": ");
            if (rateLimiter.tryAcquire()) {
                System.out.println("🟢 ALLOWED");
            } else {
                System.out.println("🔴 DENIED");
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }
        
        System.out.println("\nWaiting for 2 seconds to allow tokens to refill...");
        TimeUnit.SECONDS.sleep(2);
        
        System.out.println("\nAttempting 5 more requests...");
        for (int i = 11; i <= 15; i++) {
            System.out.print("Request " + i + ": ");
            if (rateLimiter.tryAcquire()) {
                System.out.println("🟢 ALLOWED");
            } else {
                System.out.println("🔴 DENIED");
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
