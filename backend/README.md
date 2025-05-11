# Blogging Extravaganza Backend

This is the backend API for **Blogging Extravaganza**, a blog-style app that supports user authentication via Google and
post
management. Built with **Spring Boot** using a **Clean Architecture** structure and secured with **JWT authentication**.

## 🧱 Tech Stack

- Java 17 + Spring Boot 3
- Clean Architecture (modular + layered)
- Spring Security with JWT (Google OAuth ID tokens)
- JPA + H2 (file-based dev DB)
- RESTful controllers
- Java Faker for dev seeding

## 🗂 Project Structure

```
src/
└── main/
└── java/
└── com.smartstore.backend/
├── domain/ # Core domain models & ports (interfaces)
├── application/ # Business logic (use cases / services)
├── infrastructure/ # DB entities, JPA repos, adapters, mappers
├── web/ # REST controllers
└── config/ # Security + startup config
```

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven or Gradle
- IDE like IntelliJ (open `/backend` folder directly)

### 🏗 Run Locally

```bash
./mvnw spring-boot:run
```

The app will start at:  
[http://localhost:8080](http://localhost:8080)

### 📦 Seeded Data

The app seeds:

- 5 static users + sample posts
- 5 additional Faker users + 10 posts each

Check out `DatabaseSeeder.java` for details.

### 🔐 Authentication

SmartStore uses **Google Sign-In (via Auth.js on the frontend)**.

- The frontend sends a `Bearer <id_token>` with each request
- Backend verifies the JWT against Google’s JWKs
- The token's audience and issuer are validated

✅ You must set your Google client ID in the custom `JwtDecoder`:

```java
JwtValidators.createDefaultWithIssuer("https://accounts.google.com");
```

---

### 🔗 API Overview

| Method | Endpoint            | Description                     |
|--------|---------------------|---------------------------------|
| GET    | `/api/user/profile` | Get current user profile        |
| GET    | `/api/posts`        | Get all posts                   |
| GET    | `/api/posts/mine`   | Get posts by authenticated user |
| POST   | `/api/posts`        | Create a new post               |
| DELETE | `/api/posts/{id}`   | Delete a post by ID             |

All protected endpoints require a valid Google ID token.

---

### 🧪 H2 Console

Dev uses a file-based H2 DB (`./data/smartstoredb.mv.db`).

Console available at:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:file:./data/smartstoredb`
- User: `sa`
- Password: (blank by default)

---

## 🧰 Environment Config

### `application.properties`

```properties
spring.datasource.url=jdbc:h2:file:./data/smartstoredb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

---

## 📚 Future Ideas

- Role-based access (e.g. admin users)
- Full user registration and profile editing
- Comment system for posts
- Switch to PostgreSQL in production
- Unit & integration test suites

---
