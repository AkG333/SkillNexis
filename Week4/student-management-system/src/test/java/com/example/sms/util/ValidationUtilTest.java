package com.example.sms.util;

import com.example.sms.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    @Test
    void acceptsValidStudent() {
        assertDoesNotThrow(() ->
                ValidationUtil.validate(new Student("Amit", "amit@example.com", 20, "CS")));
    }

    @Test
    void rejectsInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                ValidationUtil.validate(new Student("Amit", "wrong-email", 20, "CS")));
    }

    @Test
    void rejectsInvalidAge() {
        assertThrows(IllegalArgumentException.class, () ->
                ValidationUtil.validate(new Student("Amit", "amit@example.com", 2, "CS")));
    }
}
