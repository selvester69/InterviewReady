## Java: Thread-Safe Singleton

```java
/**
 * A thread-safe Singleton implementation using the static inner class pattern.
 *
 * This is one of the most widely recommended and robust approaches. It combines
 * the advantages of lazy initialization with thread safety without requiring
 * explicit synchronization.
 *
 * How it works:
 * 1. The `Singleton` class is a normal class.
 * 2. The `SingletonHelper` class is a static inner class. It is not loaded
 * into memory until the `getInstance()` method is called for the first time.
 * 3. When `SingletonHelper` is loaded, the static final instance `INSTANCE` is
 * created. The JVM guarantees that static initialization is thread-safe.
 * 4. Subsequent calls to `getInstance()` will simply return the existing
 * `INSTANCE` without any synchronization overhead.
 */
public class Singleton {

    // The constructor is private to prevent direct instantiation
    private Singleton() {
        System.out.println("Singleton instance created.");
    }

    // The static inner class that holds the singleton instance
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    // The public method to get the singleton instance
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Example method to show it's working
    public void showMessage() {
        System.out.println("Hello from the singleton!");
    }

    // Main method to test the singleton
    public static void main(String[] args) {
        // Get the first instance
        Singleton instance1 = Singleton.getInstance();
        instance1.showMessage();

        // Get the second instance (it should be the same object)
        Singleton instance2 = Singleton.getInstance();
        instance2.showMessage();

        // Check if both references point to the same object
        System.out.println("Are instance1 and instance2 the same object? " + (instance1 == instance2));
    }
}
