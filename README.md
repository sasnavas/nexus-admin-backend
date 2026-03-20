# 🛡️ NexusAdmin - Backend Architecture

> Enterprise-grade backend service built with Spring Boot, featuring a robust, stateless security architecture using JSON Web Tokens (JWT) and Role-Based Access Control (RBAC).

## 🚀 Tech Stack
* **Framework:** Spring Boot 3 (Java)
* **Security:** Spring Security 6
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA / Hibernate
* **Authentication:** JWT (io.jsonwebtoken)

## ✨ Key Architecture Features
* **Stateless Authentication:** Fully RESTful API design relying on JWT for session management, completely eliminating server-side session state.
* **Role-Based Access Control (RBAC):** Granular endpoint protection using `@PreAuthorize` to differentiate between `ROLE_ADMIN` and `ROLE_USER` permissions.
* **Secure Password Storage:** Implementation of `BCryptPasswordEncoder` to safely hash user credentials before database persistence.
* **CORS Configuration:** Strategically configured Cross-Origin Resource Sharing to securely communicate with the Angular frontend on port 4200.

## 🛠️ Prerequisites
* Java 17 or higher
* Maven
* PostgreSQL running locally on port `5432`

## ⚙️ Getting Started

1. **Database Setup:** Create a PostgreSQL database named `nexus_admin`.
   
2. **Environment Configuration:**
   Verify your database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nexus_admin
   spring.datasource.username=your_username
   spring.datasource.password=your_password