# wayfair second round

```java
import java.util.*;
import java.time.LocalDateTime;

// Interfaces are pre-defined by the platform
interface IPerson {
    String getName();
    String getEmail();
    void setName(String name);
    void setEmail(String email);
}

interface IEventInfo {
    String getTitle();
    String getLocation();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    void setTitle(String title);
    void setLocation(String location);
}

interface IEventManager {
    void createEvent(IEventInfo event);
    void registerPersonForEvent(IPerson person, IEventInfo event);
    List<IEventInfo> getEventsForPerson(IPerson person);
    List<IPerson> getAttendeesForEvent(IEventInfo event);
}

public class Person implements IPerson {
    private String name;
    private String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void updateInfo(String newName, String newEmail) {
        this.name = newName;
        this.email = newEmail;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }
}
import java.time.LocalDateTime;
import java.util.UUID;

public class EventInfo implements IEventInfo {
    private final String eventId;
    private String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final IPerson organizer;
    private int capacity;

    public EventInfo(String name, LocalDateTime startTime, LocalDateTime endTime, IPerson organizer, int capacity) {
        this.eventId = UUID.randomUUID().toString(); // Generates a unique ID
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.capacity = capacity;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public IPerson getOrganizer() {
        return organizer;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements IEventManager {
    private final Map<String, IEventInfo> events;
    private final Map<String, List<IPerson>> attendees;

    public EventManager() {
        this.events = new HashMap<>();
        this.attendees = new HashMap<>();
    }

    @Override
    public String createEvent(String name, LocalDateTime startTime, LocalDateTime endTime, IPerson organizer, int capacity) {
        IEventInfo newEvent = new EventInfo(name, startTime, endTime, organizer, capacity);
        events.put(newEvent.getEventId(), newEvent);
        attendees.put(newEvent.getEventId(), new ArrayList<>());
        return newEvent.getEventId();
    }

    @Override
    public boolean registerPersonForEvent(String eventId, IPerson person) {
        if (!events.containsKey(eventId)) {
            System.out.println("Event not found.");
            return false;
        }

        List<IPerson> eventAttendees = attendees.get(eventId);
        IEventInfo event = events.get(eventId);

        if (eventAttendees.size() >= event.getCapacity()) {
            System.out.println("Event is at full capacity.");
            return false;
        }

        if (eventAttendees.contains(person)) {
            System.out.println("Person is already registered for this event.");
            return false;
        }
        
        eventAttendees.add(person);
        return true;
    }

    @Override
    public List<IPerson> getAttendeesForEvent(String eventId) {
        if (!attendees.containsKey(eventId)) {
            return new ArrayList<>(); // Return an empty list if event not found
        }
        return attendees.get(eventId);
    }

    @Override
    public boolean removeEvent(String eventId) {
        if (events.containsKey(eventId)) {
            events.remove(eventId);
            attendees.remove(eventId);
            return true;
        }
        return false;
    }
}
```

```java 
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the event manager
        IEventManager eventManager = new EventManager();

        // Create people
        IPerson organizer = new Person("Alice", "alice@example.com");
        IPerson participant1 = new Person("Bob", "bob@example.com");
        IPerson participant2 = new Person("Charlie", "charlie@example.com");
        IPerson participant3 = new Person("David", "david@example.com");

        // Create an event
        LocalDateTime startTime = LocalDateTime.of(2025, 11, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 11, 1, 12, 0);
        String eventId = eventManager.createEvent("Tech Conference", startTime, endTime, organizer, 2);

        System.out.println("Created event with ID: " + eventId);

        // Register people for the event
        eventManager.registerPersonForEvent(eventId, participant1);
        eventManager.registerPersonForEvent(eventId, participant2);
        eventManager.registerPersonForEvent(eventId, participant3); // This registration should fail

        // Get attendees for the event
        System.out.println("\nAttendees for event " + eventId + ":");
        List<IPerson> eventAttendees = eventManager.getAttendeesForEvent(eventId);
        for (IPerson p : eventAttendees) {
            System.out.println(p);
        }

        // Try to register the same person again
        eventManager.registerPersonForEvent(eventId, participant1);

        // Remove the event
        System.out.println("\nRemoving event with ID: " + eventId);
        eventManager.removeEvent(eventId);
        
        System.out.println("Event exists after removal? " + (eventManager.getAttendeesForEvent(eventId).size() > 0));
    }
}
```
