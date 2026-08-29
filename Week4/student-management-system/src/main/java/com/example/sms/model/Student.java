package com.example.sms.model;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private String email;
    private int age;
    private String course;

    public Student(int id, String name, String email, int age, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public Student(String name, String email, int age, String course) {
        this(0, name, email, age, course);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return id + " - " + name + " - " + email + " - " + age + " - " + course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return id == s.id && age == s.age &&
                Objects.equals(name, s.name) &&
                Objects.equals(email, s.email) &&
                Objects.equals(course, s.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, course);
    }
}
