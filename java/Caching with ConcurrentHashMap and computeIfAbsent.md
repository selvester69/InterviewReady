# Java: Generic Thread-Safe Cache with `ConcurrentHashMap` and `computeIfAbsent()`

---

## Implementation

```java
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SimpleCache<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Function<K, V> mappingFunction;

    public SimpleCache(Function<K, V> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    public V get(K key) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

    public static void main(String[] args) throws InterruptedException {
        // Simulate a time-consuming operation
        Function<Integer, String> expensiveOperation = key -> {
            System.out.println("Performing expensive operation for key: " + key);
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result for " + key;
        };

        SimpleCache<Integer, String> cache = new SimpleCache<>(expensiveOperation);

        // Multiple threads trying to access the same key concurrently
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                String result = cache.get(1);
                System.out.println(Thread.currentThread().getName() + " got: " + result);
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        // Accessing a different key
        System.out.println("\nAccessing a different key:");
        System.out.println("Main thread got for key 2: " + cache.get(2));
        System.out.println("Main thread got for key 2 again: " + cache.get(2)); // Should be from cache
    }
}
````

---

## Explanation

### 1. `SimpleCache<K, V>` Class

* **`cache`:** A `ConcurrentHashMap` is used as the underlying storage for the cached values.
  It is thread-safe and efficient for concurrent read/write operations.
* **`mappingFunction`:** A `Function<K, V>` provided at construction time, which defines how to compute a value for a key if it is not already cached.

---

### 2. `get(K key)` Method

* Core of the caching mechanism:

```java
cache.computeIfAbsent(key, mappingFunction);
```

* Atomically:

  1. Checks if the `key` is already present.
  2. If present → returns the existing value (**cache hit**).
  3. If absent → computes the value with `mappingFunction`, inserts it into the cache, and returns it (**cache miss**).
* Ensures the computation happens **only once per key**, even under concurrent access.

---

### 3. `main` Method (Example Usage)

* **Expensive Operation:** A `Function` that simulates a long-running computation (`Thread.sleep(1000)`).
* **Concurrent Access:** Three threads request the value for key `1`.

  * The expensive operation runs only once, thanks to `computeIfAbsent()`.
  * Subsequent calls retrieve the cached result immediately.
* **Different Key:** The main thread then requests key `2`.

  * The computation is performed once for `2`, then cached for subsequent accesses.

---

## Why `ConcurrentHashMap` + `computeIfAbsent()` Works Well for Caching

* **Thread Safety:** `ConcurrentHashMap` allows safe concurrent access without explicit synchronization.
* **Atomicity:** `computeIfAbsent()` ensures "check, compute, insert" happens as a single atomic step.
* **Efficiency:** Once cached, future lookups are instant without recomputation.
* **Simplicity:** Eliminates the need for manual synchronization or lock handling.

---

✅ This approach is a **simple, generic, thread-safe in-memory cache** implementation that can be adapted to any expensive operation (e.g., database calls, API requests, heavy computations).

```