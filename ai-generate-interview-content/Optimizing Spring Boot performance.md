# Performance Bottleneck Analysis in Spring Boot

We had an endpoint that took **1.5s** to respond even though the DB query was only **50ms**.

## So what was taking time?

- 👉 **8 interceptors** firing on every request  
- 👉 Each doing extra logging, JSON parsing, and metrics collection  
- 👉 `@Transactional` wrapping the method, even though no DB writes were happening  
- 👉 Serialization overhead from converting giant DTOs back and forth  

The **business logic itself** was less than **5%** of total time.

---

## The Silent Tax of Spring Boot

Every annotation, every filter, every AOP advice has a cost.  
Individually small. Collectively massive.  

---

## Lessons Learned

a) Don’t put `@Transactional` on read-only methods. Use `@Transactional(readOnly = true)` if needed.  
b) Keep interceptors slim — log metadata, not entire payloads.  
c) Measure serialization cost; sometimes a lighter DTO or `@JsonIgnore` saves you milliseconds.  
d) Profile your app (`Java Flight Recorder`, `Micrometer`, `Spring Boot Actuator`) before blaming the DB.  

---

## Takeaway

Scalability isn’t just about sharding or caching.  
It’s also about **knowing where the real time goes in your request lifecycle**.
