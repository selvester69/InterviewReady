
# Summary: Exactly-Once Semantics in Kafka — and What Breaks Outside

Kafka provides **exactly-once semantics (EOS)** but only **within Kafka itself**—covering producer writes, broker coordination, and consumer offset management. This prevents duplicates and uncommitted states.

However, the guarantee **breaks at Kafka’s boundary**. Once consumers interact with external systems (DBs, APIs, payment services, search indexes), failures between side-effects and offset commits can cause **duplicate actions** (e.g., double payments, duplicate emails, wrong analytics).

## Why It Happens

* Kafka can’t coordinate with non-transactional external systems.
* Failures during side-effect execution create **replays and duplicates**.

## Common Solution Patterns

1. **Outbox Pattern** – Write domain changes and events in one DB transaction, then publish events via a poller. Reliable but adds operational overhead.
2. **Change Data Capture (CDC)** – Tools like Debezium stream DB changes into Kafka from the WAL. Decoupled and durable, but adds latency and schema management challenges.
3. **Idempotent External Writes** – Make external actions safe to retry (idempotency keys, upserts). Simple, but depends heavily on external system behavior.

## Key Takeaways

* **Kafka’s EOS is local** — once outside, you own coordination.
* Failures lead to duplicated side effects and inconsistent state.
* **Best practices**: design for idempotency, separate delivery from processing, make retries explicit, and add observability.
* **Pattern choice trade-offs**:

  * Idempotent Writes → simple, low latency, medium reliability
  * Outbox → balanced, strong consistency
  * CDC → robust, best for DB-driven apps

👉 The illusion of global exactly-once is impossible; real-world reliability comes from **containing side effects and simplifying coordination**.
