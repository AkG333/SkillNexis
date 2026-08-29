# Architecture

```text
Swing View
    |
    v
Controller
    |
    v
Service / business rules + Collections
    | \
    |  \
    v   v
JDBC   FileHandler
  |       |
  v       v
MySQL    CSV
```

## OOP
- Encapsulation: private Student fields with methods.
- Abstraction: `StudentRepository` interface.
- Polymorphism: `StudentRepositoryJdbc` implements the interface.
- Composition: Controller owns Service; Service owns repository and file handler.
- Separation of concerns: UI, business logic, DB, and files are separated.

## Collections
`ArrayList` stores JDBC query results and CSV rows. Streams are used for name sorting.

## JDBC
The repository demonstrates `Connection`, `PreparedStatement`, `ResultSet`, generated keys, and transactions.

## File handling
`BufferedReader` and `BufferedWriter` are used through `java.nio.file.Files`.
