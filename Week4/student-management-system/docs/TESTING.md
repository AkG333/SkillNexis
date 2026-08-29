# Testing

Run:

```bash
mvn test
```

Automated tests cover valid student data, invalid email, and invalid age.

Manual integration checklist:
1. Run `sql/schema.sql`.
2. Configure MySQL credentials.
3. Start the application.
4. Add a student.
5. Select and update it.
6. Delete it.
7. Search by name/email/course.
8. Sort A-Z.
9. Export CSV.
10. Import the CSV.
11. Verify persistence after Refresh.

Error cases:
- Blank name/course
- Invalid email
- Invalid age
- Invalid ID
- Malformed CSV
- Duplicate email
- Database unavailable
