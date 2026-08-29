package com.example.sms.repository;

import com.example.sms.model.Student;
import java.util.List;

public interface StudentRepository {
    Student add(Student student);
    void update(Student student);
    void delete(int id);
    List<Student> findAll();
    List<Student> search(String keyword);
    void insertBatch(List<Student> students);
}
