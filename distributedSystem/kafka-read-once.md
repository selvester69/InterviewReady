# Exactly-Once Semantics in Kafka — and What Breaks When You Talk to the Outside World

**Author:** Abhijeet Srivastava
**Read Time:** 4 min

Exactly-once delivery sounds like the kind of guarantee you'd expect in any serious data pipeline, but in distributed systems, it is notoriously difficult to achieve. Apache Kafka offers a remarkably robust form of exactly-once semantics — at least within the boundaries of Kafka itself. When you produce a message into Kafka using a transactional producer, the broker ensures that messages appear atomically across multiple partitions and only once to consumers configured in "read_committed" mode. This mechanism is carefully coordinated using producer IDs, sequence numbers, and transactional markers to ensure that retries don't result in duplicate data and that consumers don't see uncommitted intermediate states. But this guarantee has a clear boundary: it stops at Kafka's edge.

## Kafka's Internal Exactly-Once Mechanism

```mermaid
graph TB
    subgraph "Kafka Cluster"
        Producer["Transactional Producer<br/>- Producer ID<br/>- Sequence Numbers"]
        Broker1["Broker 1<br/>Partition A"]
        Broker2["Broker 2<br/>Partition B"]
        Broker3["Broker 3<br/>Partition C"]
        Consumer["Consumer<br/>read_committed mode"]
        
        Producer -->|Atomic Write| Broker1
        Producer -->|Atomic Write| Broker2
        Producer -->|Atomic Write| Broker3
        
        Broker1 --> Consumer
        Broker2 --> Consumer
        Broker3 --> Consumer
    end
    
    style Producer fill:#e1f5fe
    style Consumer fill:#e8f5e8
    style Broker1 fill:#fff3e0
    style Broker2 fill:#fff3e0
    style Broker3 fill:#fff3e0
```

## The Problem: External System Coordination

Once your application consumes a Kafka message and interacts with any external system — be it a database, an external API, a payment service, or even a search index — you are now operating outside Kafka's transactional domain. And this is where many real-world systems run into subtle, sometimes devastating issues. Suppose your consumer reads an order_placed event, processes a payment by updating a database, and finally commits the Kafka offset to indicate the message has been handled. Now, imagine a failure happens between the database write and the offset commit. On recovery, Kafka replays the same message, and your application reprocesses it—resulting in a duplicated payment or some other external side effect. Kafka, having no insight into your database, cannot prevent this. It did its job. The problem lies in coordinating with systems that do not participate in the transaction.

### The Failure Scenario

```mermaid
sequenceDiagram
    participant K as Kafka
    participant A as Application
    participant DB as Database
    participant P as Payment Service
    
    K->>A: order_placed event
    A->>DB: Update order status
    DB-->>A: Success
    A->>P: Process payment
    P-->>A: Success
    Note over A: ❌ CRASH HERE
    A-xK: Offset commit (never happens)
    
    Note over K: Message replay on recovery
    K->>A: order_placed event (DUPLICATE)
    A->>DB: Update order status (again)
    A->>P: Process payment (DUPLICATE!)
    P-->>A: Duplicate payment processed
    A->>K: Offset commit
```

## Solution Patterns

To bridge this boundary, developers have devised several architectural patterns.

### 1. The Outbox Pattern

The most canonical is the Outbox Pattern, which takes advantage of the transactional guarantees of relational databases. Instead of writing directly to Kafka and your domain tables in two separate steps, your application performs a single database transaction that writes the business change (say, updating the orders table) and simultaneously inserts a pending event into a separate outbox table. A background process—either embedded in the service or decoupled as a poller—then reads this outbox table and publishes the messages to Kafka. Since both the domain write and the event insert occur in the same DB transaction, the system is guaranteed to reflect only committed states to the outside world. The message reaches Kafka only if the database transaction succeeds. This avoids the "write succeeded but event was never published" and the "event published but write didn't happen" scenarios. However, it comes with operational complexity: maintaining an outbox poller, ensuring deduplication in the face of crashes, and dealing with message ordering under parallel execution.

```mermaid
graph TB
    subgraph "Application Service"
        App["Application Logic"]
        Poller["Outbox Poller"]
    end
    
    subgraph "Database"
        DomainTable["Orders Table"]
        OutboxTable["Outbox Table"]
    end
    
    subgraph "Kafka"
        Topic["order_events Topic"]
    end
    
    App -->|Single Transaction| DomainTable
    App -->|Single Transaction| OutboxTable
    Poller -->|Poll & Read| OutboxTable
    Poller -->|Publish| Topic
    
    style App fill:#e1f5fe
    style Poller fill:#e8f5e8
    style DomainTable fill:#fff3e0
    style OutboxTable fill:#ffebee
```

### 2. Change Data Capture (CDC)

An improvement over this manual polling mechanism, especially when using databases like PostgreSQL or MySQL, is to leverage Change Data Capture (CDC) tools like Debezium. With CDC, your application simply writes to the database. Debezium tails the write-ahead log (WAL) and streams changes to Kafka. This removes the need to publish to Kafka directly. Instead, Kafka receives the event through a durable replication-like mechanism, and your services downstream can consume it with the usual guarantees. However, CDC isn't free: it introduces latency, requires careful schema evolution practices, and may leak sensitive internal schema details if not properly designed. Nevertheless, it creates a decoupled, robust model for syncing application state with Kafka.

```mermaid
graph TB
    subgraph "Application Service"
        App["Application Logic"]
    end
    
    subgraph "Database"
        DB["PostgreSQL/MySQL"]
        WAL["Write-Ahead Log"]
    end
    
    subgraph "CDC System"
        Debezium["Debezium Connector"]
    end
    
    subgraph "Kafka"
        Topic["Database Changes Topic"]
    end
    
    App -->|Write| DB
    DB -->|Generate| WAL
    Debezium -->|Tail| WAL
    Debezium -->|Stream Changes| Topic
    
    style App fill:#e1f5fe
    style DB fill:#fff3e0
    style Debezium fill:#e8f5e8
    style WAL fill:#ffebee
```

### 3. Idempotent External Writes

Finally, the simplest but riskiest pattern is to consume from Kafka, perform the external write, and then commit the offset — hoping nothing crashes in between. To make this safe, your write must be idempotent. This could mean using an external idempotency key, inserting with ON CONFLICT DO NOTHING, or ensuring that duplicate writes don't change business outcomes. This strategy is easy to implement but fragile. It requires careful handling of edge cases, retries, partial failures, and observability. It also depends heavily on the external system's behavior: not all systems handle idempotent writes cleanly.

```mermaid
graph TB
    subgraph "Safe Idempotent Pattern"
        K1[Kafka Consumer]
        App1[Application]
        Ext1[External System<br/>with Idempotency]
        
        K1 -->|Consume| App1
        App1 -->|Idempotent Write<br/>with Key| Ext1
        App1 -->|Commit Offset| K1
    end
    
    subgraph "Risky Non-Idempotent Pattern"
        K2[Kafka Consumer]
        App2[Application]
        Ext2[External System<br/>No Idempotency]
        
        K2 -->|Consume| App2
        App2 -->|Write| Ext2
        App2 -->|❌ May Fail| K2
    end
    
    style App1 fill:#e8f5e8
    style Ext1 fill:#e8f5e8
    style App2 fill:#ffebee
    style Ext2 fill:#ffebee
```

## The Coordination Challenge

```mermaid
graph TB
    subgraph "Kafka's Guarantee Boundary"
        Producer["Producer"]
        Kafka["Kafka Cluster"]
        Consumer["Consumer"]
        
        Producer -->|Exactly-Once| Kafka
        Kafka -->|Exactly-Once| Consumer
    end
    
    subgraph "External World - No Guarantees"
        DB["Database"]
        API["External API"]
        Payment["Payment Service"]
        Search["Search Index"]
    end
    
    Consumer -.->|❌ Coordination Gap| DB
    Consumer -.->|❌ Coordination Gap| API
    Consumer -.->|❌ Coordination Gap| Payment
    Consumer -.->|❌ Coordination Gap| Search
    
    style Producer fill:#e8f5e8
    style Kafka fill:#e8f5e8
    style Consumer fill:#e8f5e8
    style DB fill:#ffebee
    style API fill:#ffebee
    style Payment fill:#ffebee
    style Search fill:#ffebee
```

## **Key Takeaways** 💡

### **The Hard Truth**
**Kafka's exactly-once semantics are LOCAL**. They hold within:
• Kafka's **write path**
• Consumer **offset management**
• **Internal transactional coordination**

But once your system interacts with components **Kafka can't coordinate with** — systems without shared transactions or atomic visibility — **you must take responsibility** for the coordination.

### **Common Real-World Failures** 🚨
You've likely faced these challenges, sometimes realizing them **too late**:
• **💰 Duplicated refunds** - financial impact
• **📧 Emails sent twice** - customer experience degradation  
• **📊 Analytics double-counting** - incorrect business metrics
• **🔄 Repeated API calls** - external service overload
• **⚠️ Inconsistent state** - data integrity issues

### **The Solution Philosophy** 🛠️

**Don't make the system more clever** - instead:

• **📦 Contain side effects** - limit blast radius of failures
• **🔁 Structure retries explicitly** - make retry logic first-class
• **⚡ Separate delivery and processing** - treat as two distinct problems
• **🎯 Design for idempotency** - assume duplicates will happen
• **🔍 Embrace observability** - monitor and alert on coordination failures

### **Pattern Selection Guide** 🗺️

| Pattern | Complexity | Reliability | Latency | Best For |
|---------|------------|-------------|---------|----------|
| **Idempotent Writes** | Low | Medium | Low | Simple services, good external system support |
| **Outbox Pattern** | Medium | High | Medium | Strong consistency needs, complex workflows |
| **CDC (Debezium)** | High | High | Medium-High | Database-heavy applications, event sourcing |

**Remember**: There's **no free lunch**. The illusion of exactly-once across distributed boundaries is only achievable through patterns that carefully isolate effects, design for retries, and make failure part of the normal path.

Often, the fix lies not in making the system more clever, but in **simplifying the coordination model**: contain side effects, structure retries explicitly, and treat delivery and processing as two separate problems.
