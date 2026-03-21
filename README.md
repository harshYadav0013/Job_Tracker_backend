# JobTracker — Backend

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-darkgreen?style=flat-square)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-green?style=flat-square)

A RESTful API backend for JobTracker — a personal job application tracking system. Built with Java Spring Boot, Spring Security, Spring Data JPA, and MySQL. Consumed by a separate React frontend.

> Frontend repo: [Job_Tracker_Frontend](https://github.com/harshYadav0013/Job_Tracker_frontend)

---

## Features

- User registration and session-based authentication via Spring Security
- Full CRUD for job applications — create, read, update, delete
- Filter applications by status (Applied, Interview, Offer, Rejected)
- Analytics endpoint returning real-time application statistics
- CORS configured for React frontend running on a separate origin
- Auto-generated API documentation via Swagger UI (OpenAPI 3.0)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security 6 (session-based) |
| ORM | Spring Data JPA + Hibernate |
| Database | MySQL 8 |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Build Tool | Maven |

---

## Project Structure

```
src/main/java/com/Harsh/jpaTutorial/
│
├── controller/
│   ├── AuthController.java       # /register, /login endpoints
│   └── JobController.java        # /api/jobs REST endpoints
│
├── service/
│   ├── UserService.java
│   ├── JobService.java
│   └── CustomUserDetailsService.java
│
├── repository/
│   ├── UserRepository.java
│   └── JobRepository.java
│
├── model/
│   ├── User.java
│   ├── JobApplication.java
│   └── Status.java               # Enum: APPLIED, INTERVIEW, OFFER, REJECTED
│
└── config/
    └── SecurityConfig.java       # CORS + CSRF + Auth config
```

---

## API Endpoints

Full interactive documentation available at `http://localhost:8080/swagger-ui/index.html` when running locally.

### Auth

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/register` | Register a new user (JSON) |
| POST | `/login` | Login with email + password |
| POST | `/logout` | Logout current session |

### Job Applications

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/jobs` | Get all jobs for logged-in user |
| GET | `/api/jobs?status=APPLIED` | Filter jobs by status |
| GET | `/api/jobs/{id}` | Get a single job by ID |
| POST | `/api/jobs` | Add a new job application |
| PUT | `/api/jobs/{id}` | Update an existing job |
| DELETE | `/api/jobs/{id}` | Delete a job application |
| GET | `/api/jobs/stats` | Get dashboard statistics |

### Stats Response Example

```json
{
  "total": 10,
  "applied": 5,
  "interview": 3,
  "offer": 1,
  "rejected": 1
}
```

---

## Getting Started

### Prerequisites

- Java 17+
- MySQL 8+
- Maven

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/harshYadav0013/Job_Tracker_backend.git
cd Job_Tracker_backend
```

**2. Create MySQL database**
```sql
CREATE DATABASE jobtracker;
```

**3. Configure `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtracker
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Run the application**
```bash
mvn spring-boot:run
```

The server starts at `http://localhost:8080`

**5. Open Swagger UI**
```
http://localhost:8080/swagger-ui/index.html
```

---

## Database Schema

```
users
├── id (PK)
├── name
├── email (unique)
└── password (BCrypt encoded)

job_applications
├── id (PK)
├── company_name
├── role
├── location
├── applied_date
├── status (APPLIED | INTERVIEW | OFFER | REJECTED)
├── notes
└── user_id (FK → users.id)
```

---

## Security

- Passwords are encoded using BCrypt
- Sessions managed by Spring Security
- CSRF disabled for `/api/**` routes (consumed by React frontend)
- CORS configured to allow requests from `http://localhost:5173`
- Unauthenticated API requests return `401 JSON` instead of redirecting to login page

---

## Frontend

The React frontend for this project is available at:
[https://github.com/harshYadav0013/Job_Tracker_frontend](https://github.com/harshYadav0013/Job_Tracker_frontend)

---

## Author

**Harsh Yadav**  
[GitHub](https://github.com/harshYadav0013)
