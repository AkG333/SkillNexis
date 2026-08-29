# Student Management System

A complete Java OOP capstone project implementing MVC, Collections, File Handling, JDBC + MySQL, Swing GUI, validation, testing, and documentation.

## Requirements
- Java 17+
- MySQL 8+
- Maven 3.8+

## Database setup
Run `sql/schema.sql` in MySQL.

Configure:
- `DB_URL` (default: `jdbc:mysql://localhost:3306/student_management`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: empty)

## Run

```bash
mvn clean test
mvn exec:java
```

Or run `com.example.sms.Main` from your IDE.

## Features
- Add, update, delete, view students
- Search by name/email/course
- Sort A-Z
- Export to CSV
- Import from CSV
- Persistent MySQL storage
- JDBC prepared statements
- Input validation
- Transactional CSV import

## MVC
- `model` — Student domain object
- `repository` — JDBC data access
- `service` — business rules and collections
- `view` — Swing GUI
- `controller` — connects view and service
- `util` — DB connection, CSV handling, validation
