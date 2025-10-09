# Session-Based Authentication VS JWT-Based Authentication

[1] Session-Based Authentication:

➥ Authentication Flow:

- When a user logs in (username/password), the server authenticates the credentials.
- If correct, the server creates a session object (an entry in a server-side session store, often a database, in-memory store like Redis, or a server’s local memory).
- The session object typically contains the user’s ID, roles, permissions, and any other necessary info.

➥Session ID & Cookies:

- The server generates a unique session ID (random string).
- This session ID is sent to the client, almost always as an HTTP-only, secure cookie.
- The session data itself never leaves the server. The client only stores the session ID.

➥ Request Lifecycle:

- For each new HTTP request, the client’s browser automatically sends the session ID cookie.
- The server uses this ID to fetch user data from the session store and authorize the request.

➥ Session Invalidation/Logout:

- To log out a user (or force logout, like after password change), the server simply deletes the session object.
- Any further requests with that session ID are now invalid.

➥ Pros:

- Centralized control. Server can forcibly log out users, immediately revoke sessions, and tightly manage access.
- Session data is never exposed to the client, so no risk of leaking sensitive user info through tokens.

➥ Cons:

- Doesn’t scale well for distributed/microservices architectures unless sessions are stored in a shared data store (e.g., Redis, Memcached).
- Each request requires a lookup in the session store, which can add overhead at massive scale.
- More complex to implement with stateless REST APIs or across many domains/services.

[2] JWT-Based Authentication: Architecture, Security, and Tradeoffs

➥ Authentication Flow:

- User logs in, credentials are verified, and instead of creating a session, the server creates a JWT (JSON Web Token).

➥ The JWT has 3 parts:

- Header: specifies the algorithm (e.g., HS256), and type (JWT).
- Payload: contains claims (like `user_id`, `exp` for expiration, `role`, or custom claims). Note: The payload is base64 encoded, but NOT encrypted, anyone with the token can decode and view it.

➥ Signature: cryptographic hash (using secret or private key) that ensures the token hasn’t been tampered with.

➥ Stateless Design:

- The JWT is sent to the client (typically as a cookie, but sometimes as an Authorization header for APIs).
- The server does not store tokens or track sessions, stateless authentication.
- On each request, the client sends back the JWT. The server verifies the signature and checks claims (including expiration).

➥ Logout, Revocation, and Expiry:

- Since the server doesn’t keep track of JWTs, invalidation is tricky. Logging out is usually just deleting the token on the client.
- To forcibly expire a token (e.g., after password reset), you need a blacklist/deny-list on the server, or use short-lived JWTs with frequent refresh.

[3] Choosing Between Sessions and JWTs: Practical Scenarios

➥ When to Use Sessions:

- Apps needing strong, immediate control over user sessions, banking, admin panels, or apps with sensitive operations.

- Monolithic or small-scale apps, where storing sessions in memory or a database is manageable.

- Any situation where forced logouts (across all devices, instantly) are a hard requirement.

➥ When to Use JWTs:

- Large-scale, distributed, or microservices architectures where statelessness simplifies development and deployment.

- REST APIs or mobile clients, where each service should independently verify users.

- When horizontal scaling (multiple servers) is important, and you want to avoid complex, shared session stores.

- For third-party integrations or SSO (Single Sign-On), JWTs are commonly used for federated authentication.
