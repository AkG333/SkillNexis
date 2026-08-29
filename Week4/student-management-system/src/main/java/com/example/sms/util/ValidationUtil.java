package com.example.sms.util;

import com.example.sms.model.Student;

public final class ValidationUtil {
    private ValidationUtil() {}

    public static void validate(Student s) {
        if (s == null) throw new IllegalArgumentException("Student cannot be null.");
        if (s.getName() == null || s.getName().isBlank())
            throw new IllegalArgumentException("Name is required.");
        if (s.getEmail() == null ||
                !s.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new IllegalArgumentException("Enter a valid email.");
        if (s.getAge() < 5 || s.getAge() > 100)
            throw new IllegalArgumentException("Age must be between 5 and 100.");
        if (s.getCourse() == null || s.getCourse().isBlank())
            throw new IllegalArgumentException("Course is required.");
    }
}
