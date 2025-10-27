package wayfair;
// event id, event name 
//TODO - incomplete

import java.time.LocalDateTime;
import java.util.*;

interface IPerson {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);
}

interface IEventInfo {
    String getEventId();

    String getName();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    IPerson getOrganizer();

    int getCapacity();
    // list of registration
    // list of attendees
    // setter getter for all fields such as event name, event date, cancelled ,
    // capacity
    // two methods-> register and attend
}

interface IEventtManager {
    // list of events
    String createEvent(String name, LocalDateTime startTime, LocalDateTime endTime, IPerson organizer, int capacity);

    boolean registerPersonForEvent(String eventId, IPerson person);

    List<IPerson> getAttendeesForEvent(String eventId);

    boolean removeEvent(String eventId);

}

// person , event, eventManager

public class Main {

}
