## Spring Transaction Management & Propagation

Spring's declarative transaction management simplifies the handling of database transactions. By using the `@Transactional` annotation, you can define a method as a single, atomic unit of work.

### How Transactions Work in Spring

When a method with the `@Transactional` annotation is called, Spring's AOP (Aspect-Oriented Programming) proxy intercepts the call. The proxy then:

1. **Begins** a new transaction.
2. **Executes** the method's business logic.
3. **Commits** the transaction if the method completes successfully.
4. **Rolls back** the transaction if an unchecked exception is thrown.

This abstracts away the need for explicit `try-catch-finally` blocks with `connection.commit()` and `connection.rollback()`.

---

### What is Propagation?

**Transaction propagation** defines the transactional behavior of a method when it's called from another transactional method. It answers the question: "Should this method join the existing transaction or start a new one?"

The most common propagation levels are:

* `REQUIRED` (default): If a transaction is already in progress, the method joins it. If not, it creates a new one. This is the most common and sensible choice.
* `REQUIRES_NEW`: Always starts a new, independent transaction. If an existing transaction is in progress, it is suspended until the new transaction completes.
* `SUPPORTS`: Joins an existing transaction if one exists, but runs non-transactionally if one does not.
* `NOT_SUPPORTED`: Always runs non-transactionally. If a transaction is in progress, it is suspended.
* `NEVER`: Throws an exception if a transaction is in progress.
* `MANDATORY`: Throws an exception if a transaction is not in progress.
* `NESTED`: Runs within an existing transaction but can be rolled back independently via a savepoint.

---

### Expanded Examples: Scenarios for Different Propagation Levels

Here's an expanded example to illustrate different scenarios and the need for different propagation levels. Imagine a service for user registration and notification.

```java
@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private NotificationService notificationService;

    // SCENARIO 1: Basic user creation (REQUIRED propagation)
    @Transactional(propagation = Propagation.REQUIRED)
    public void registerUser(User user) {
        userRepository.save(user); // Joins this transaction
        notificationService.sendWelcomeEmail(user); // Joins this transaction
    }
}

@Service
public class NotificationService {
    // SCENARIO 2: Sending a welcome email
    // This method will join the transaction of the caller.
    // If an email sending failure causes a rollback, the user creation will also be rolled back.
    @Transactional(propagation = Propagation.REQUIRED)
    public void sendWelcomeEmail(User user) {
        // ... logic to send email
        System.out.println("Sending welcome email to " + user.getName());
    }

    // SCENARIO 3: Creating an audit log with an independent transaction
    // This method will *always* start a new transaction.
    // Even if the caller's transaction fails and rolls back, the audit log will be committed.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logUserAction(String action) {
        // ... logic to save action to a separate audit table
        System.out.println("Logged user action: " + action);
    }
}

// Example usage in another service
@Service
public class RegistrationFlowService {
    @Autowired private UserService userService;
    @Autowired private NotificationService notificationService;

    // This method orchestrates the registration, with a nested transactional call.
    @Transactional(propagation = Propagation.REQUIRED)
    public void completeRegistrationWithAudit(User user) {
        userService.registerUser(user);
        
        // This call will create its own independent transaction.
        // If an exception happens in a subsequent method in completeRegistrationWithAudit,
        // the audit log will still be committed.
        notificationService.logUserAction("Registration successful for " + user.getName());
        
        // Example of an issue in the main flow that would cause a rollback
        // if (someConditionFails) {
        //     throw new RuntimeException("Registration flow failed.");
        // }
    }
}
