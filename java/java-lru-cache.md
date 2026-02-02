# Java: LRU Cache Implementation

## Using LinkedHashMap

- using linkedhashmap the implementation becomes easy as we can easily remove element from start

```java
public class LRUCache {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // passing true will ensure put and get operation will move the key to the end of linked hashMap
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        map.put(key, value);
        if (map.size() > capacity) {
            map.remove(map.keySet().iterator().next());
        }
    }
}
```

## Using doubly linkedList and hashmap

```java
import java.util.HashMap;

/**
 * An implementation of a Least Recently Used (LRU) Cache.
 *
 * This implementation uses a HashMap for O(1) key lookups and a DoublyLinkedList
 * to maintain the order of recently used elements. The head of the list is the
 * most recently used, and the tail is the least recently used.
 *
 * This approach does not rely on the LinkedHashMap's removeEldestEntry() method.
 */
public class LRUCache<K, V> {

    // Doubly linked list node
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final HashMap<K, Node> cache;
    private final int capacity;
    private Node head; // Most recently used
    private Node tail; // Least recently used

    /**
     * Constructs an LRU cache with the specified capacity.
     * @param capacity the maximum number of items the cache can hold.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    /**
     * Gets an item from the cache.
     * @param key the key of the item to retrieve.
     * @return the value associated with the key, or null if not found.
     */
    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        Node node = cache.get(key);
        // Move the accessed node to the head (most recently used)
        moveToHead(node);
        return node.value;
    }

    /**
     * Puts a key-value pair into the cache.
     * @param key the key to store.
     * @param value the value to store.
     */
    public void put(K key, V value) {
        Node node;
        if (cache.containsKey(key)) {
            // Update the value if the key already exists
            node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // New entry
            node = new Node(key, value);
            cache.put(key, node);
            addFirst(node);
            
            // Check capacity and evict if necessary
            if (cache.size() > capacity) {
                removeLast();
            }
        }
    }

    /**
     * Removes a node from its current position in the linked list.
     * @param node the node to remove.
     */
    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // This is the head
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // This is the tail
            tail = node.prev;
        }
    }

    /**
     * Adds a new node to the front (head) of the linked list.
     * @param node the node to add.
     */
    private void addFirst(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    /**
     * Moves an existing node to the front (head) of the linked list.
     * @param node the node to move.
     */
    private void moveToHead(Node node) {
        if (node == head) {
            return;
        }
        removeNode(node);
        addFirst(node);
    }

    /**
     * Removes the last node (least recently used) from the linked list.
     */
    private void removeLast() {
        if (tail != null) {
            cache.remove(tail.key);
            removeNode(tail);
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        System.out.println("Capacity is 3. Adding items...");
        
        cache.put(1, "Item A");
        cache.put(2, "Item B");
        cache.put(3, "Item C");
        System.out.println("Cache contents: " + cache.cache.keySet()); // [1, 2, 3]

        System.out.println("Accessing key 2: " + cache.get(2));
        System.out.println("Cache contents after access: " + cache.cache.keySet()); // [1, 3, 2] - 2 is now most recently used

        System.out.println("Adding new item 4, which will evict the least recently used (key 1).");
        cache.put(4, "Item D");
        System.out.println("Cache contents after put: " + cache.cache.keySet()); // [4, 3, 2] - key 1 is gone
        
        System.out.println("Accessing key 1: " + cache.get(1)); // Should be null
        
        System.out.println("Final cache contents: " + cache.cache.keySet()); // [4, 3, 2]
    }
}
```

## Build an In-Memory Cache (eviction strategies, TTL) java

```java
public class CacheEntry<V> {
    private final V value;
    private final long expirationTime;

    public CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.expirationTime = System.currentTimeMillis() + ttlMillis;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }

    public V getValue() {
        return value;
    }
}
```

```java
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CustomCache<K, V> {
    private final Map<K, CacheEntry<V>> cacheMap;
    private final int capacity;
    // Daemon thread for passive background cleanup of expired entries
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public CustomCache(int capacity, long cleanupIntervalSeconds) {
        this.capacity = capacity;
        this.cacheMap = Collections.synchronizedMap(new LinkedHashMap<K, CacheEntry<V>>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
                // Evict the least recently used entry when capacity is reached
                return size() > CustomCache.this.capacity;
            }
        });
        
        // Schedule periodic cleanup of expired entries
        cleaner.scheduleAtFixedRate(this::cleanupExpiredEntries, 0, cleanupIntervalSeconds, TimeUnit.SECONDS);
    }

    public void put(K key, V value, long ttlMillis) {
        if (value == null) {
            cacheMap.remove(key);
            return;
        }
        cacheMap.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public V get(K key) {
        CacheEntry<V> entry = cacheMap.get(key);
        if (entry == null) {
            return null; // Cache miss
        }
        if (entry.isExpired()) {
            cacheMap.remove(key); // Remove expired entry
            return null;
        }
        return entry.getValue();
    }

    // Active cleanup method to be run by the scheduler
    private void cleanupExpiredEntries() {
        synchronized (cacheMap) {
            cacheMap.entrySet().removeIf(entry -> entry.getValue().isExpired());
        }
    }
}
```
