# MedicoRe — Hospital Management Backend

A production-ready backend system for managing hospital operations — patients, doctors, and appointments — built with Spring Boot and secured with JWT authentication.

---

## What is MedicoRe?

MedicoRe is a healthcare backend that handles the operational complexity of a hospital environment. The focus was on building something practical: clean API design, real security, and a scheduling algorithm that actually solves a problem rather than just demonstrating CRUD.

The auto-scheduler is the core of this project. Instead of leaving doctor assignment to the frontend or the user, the system calculates workload distribution in real time and assigns the least-loaded available doctor — automatically.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | MySQL |
| ORM | Hibernate / JPA |
| API Testing | Postman |

---

## Features

### Authentication & Authorization
- JWT-based stateless authentication (no server-side sessions)
- Role-based access control — `ADMIN` and `RECEPTIONIST` roles
- Token refresh support

### REST API Design
- Full CRUD for Patients, Doctors, and Appointments
- Standardized responses via a custom `ApiResponse` wrapper
- DTO layer to decouple internal entities from API contracts
- Input validation with `@NotNull`, `@NotBlank` annotations

### Auto-Scheduler
The most interesting part of this project. When a patient books an appointment, instead of manually picking a doctor:

1. The system fetches all doctors matching the required specialization
2. Calculates each doctor's appointment count for the day
3. Assigns the one with the least load
```
Assign → doctor where appointments_today = MIN(all matching doctors)
```

This keeps workload balanced across staff without any manual intervention.

### Scalability & Reliability
- Pagination via `Pageable` for large datasets
- Global exception handling with `@ControllerAdvice`
- Custom exception classes (`ResourceNotFoundException`, etc.) for clean error responses

---

## Architecture
```
Client → Controller → Service → Repository → MySQL
              ↓
         JWT Filter (validates token on every request)
```

The project follows a strict layered architecture. Controllers handle routing, services own the business logic, and repositories talk to the database. Nothing bleeds between layers.

---

## API Reference

### Auth
```
POST  /auth/login
POST  /auth/refresh
```

### Patients
```
GET    /api/patients
POST   /api/patients
DELETE /api/patients/{id}
```

### Doctors
```
GET   /api/doctors
POST  /api/doctors
```

### Appointments
```
POST  /api/appointments           # Manual booking
POST  /api/appointments/auto      # Auto-scheduler
```

---

## Getting Started

**1. Clone the repo**
```bash
git clone https://github.com/Srinayan-96/medicore.git
cd medicore
```

**2. Configure your database**

In `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mediflow
spring.datasource.username=root
spring.datasource.password=yourpassword
```

**3. Run**
```bash
mvn spring-boot:run
```

Server starts at `http://localhost:8080`

---

## Testing with Postman

1. Hit `POST /auth/login` with your credentials
2. Copy the JWT from the response
3. Add it to the `Authorization` header on every subsequent request:
```
Authorization: Bearer <your_token>
```



---

## Author

**Srinayan N**
[GitHub](https://github.com/Srinayan-96) · [LinkedIn](https://www.linkedin.com/in/srinayan-n)
