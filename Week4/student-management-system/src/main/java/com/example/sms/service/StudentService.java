package com.example.sms.service;

import com.example.sms.model.Student;
import com.example.sms.repository.StudentRepository;
import com.example.sms.util.FileHandler;
import com.example.sms.util.ValidationUtil;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class StudentService {
    private final StudentRepository repository;
    private final FileHandler fileHandler;

    public StudentService(StudentRepository repository, FileHandler fileHandler) {
        this.repository = repository;
        this.fileHandler = fileHandler;
    }

    public Student add(Student s) {
        ValidationUtil.validate(s);
        return repository.add(s);
    }

    public void update(Student s) {
        ValidationUtil.validate(s);
        if (s.getId() <= 0) throw new IllegalArgumentException("Valid student ID is required.");
        repository.update(s);
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Valid student ID is required.");
        repository.delete(id);
    }

    public List<Student> getAll() { return repository.findAll(); }

    public List<Student> search(String keyword) {
        return keyword == null || keyword.isBlank() ? getAll() : repository.search(keyword.trim());
    }

    public List<Student> sortByName(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public void exportCsv(List<Student> students, Path path) {
        fileHandler.writeCsv(students, path);
    }

    public void importCsv(Path path) {
        repository.insertBatch(fileHandler.readCsv(path));
    }
}
