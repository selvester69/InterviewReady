# Java: Design an Immutable Class
An immutable class is one whose instance cannot be modified after it's created. This is a fundamental concept in concurrent programming because immutable objects are inherently thread-safe and can be shared freely among multiple threads without synchronization.

Rules for Designing an Immutable Class
Final Class: Make the class final so it cannot be subclassed. This prevents a subclass from overriding methods and making the object mutable.

Private and Final Fields: Make all fields private to prevent direct access and final so they cannot be reassigned after initialization.

No Setters: Do not provide any setter methods or other methods that can modify the state of the object.

Deep Copy in Constructor: If the class holds references to mutable objects (like a List), you must create a defensive copy of the object in the constructor. This prevents the caller from modifying the object after it's been passed to the constructor.

Defensive Copy in Getters: Similarly, if a getter returns a reference to a mutable object, you must return a defensive copy to prevent the caller from modifying the internal state.

Example
Here is a complete, well-commented example of a properly designed immutable class that contains both a primitive field and a mutable object.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**

* A properly designed immutable class.
 */
public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // RULE 4: Create a defensive copy of the mutable list in the constructor
        if (hobbies != null) {
            this.hobbies = new ArrayList<>(hobbies);
        } else {
            this.hobbies = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        // RULE 5: Return a defensive copy of the mutable list
        // to prevent external modification
        return Collections.unmodifiableList(hobbies);
    }

    public static void main(String[] args) {
        // Create a mutable list to pass to the constructor
        List<String> userHobbies = new ArrayList<>();
        userHobbies.add("Reading");
        userHobbies.add("Hiking");

        ImmutablePerson person = new ImmutablePerson("Alice", 30, userHobbies);
        System.out.println("Initial person: " + person.getName() + ", Age: " + person.getAge() + ", Hobbies: " + person.getHobbies());

        // Attempt to modify the original list passed to the constructor
        userHobbies.add("Painting");
        System.out.println("Original list modified. Person's hobbies are unchanged: " + person.getHobbies());

        // Attempt to modify the list returned by the getter
        try {
            person.getHobbies().add("Gaming");
        } catch (UnsupportedOperationException e) {
            System.out.println("Attempt to modify hobbies list failed with: " + e.getMessage());
        }
    }
}
```

In this example, both the constructor and the getter for the mutable hobbies field use defensive copies to ensure that the internal state of the ImmutablePerson object can't be changed after creation.
