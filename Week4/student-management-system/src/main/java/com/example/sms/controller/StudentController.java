package com.example.sms.controller;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;
import com.example.sms.view.StudentView;

import java.nio.file.Path;
import java.util.List;

public class StudentController {
    private final StudentService service;
    private final StudentView view;
    private List<Student> current = List.of();

    public StudentController(StudentService service, StudentView view) {
        this.service = service;
        this.view = view;

        view.onAdd(this::add);
        view.onUpdate(this::update);
        view.onDelete(this::delete);
        view.onRefresh(this::refresh);
        view.onSearch(this::search);
        view.onSort(this::sort);
        view.onExport(this::export);
        view.onImport(this::importCsv);

        refresh();
    }

    private void add() {
        try {
            Student s = service.add(view.readStudent());
            view.clearForm();
            refresh();
            view.info("Student added. ID = " + s.getId());
        } catch (Exception e) { view.error(message(e)); }
    }

    private void update() {
        try {
            service.update(view.readStudent());
            view.clearForm();
            refresh();
            view.info("Student updated.");
        } catch (Exception e) { view.error(message(e)); }
    }

    private void delete() {
        try {
            Student s = view.readStudent();
            if (!view.confirm("Delete student ID " + s.getId() + "?")) return;
            service.delete(s.getId());
            view.clearForm();
            refresh();
            view.info("Student deleted.");
        } catch (Exception e) { view.error(message(e)); }
    }

    private void refresh() {
        try {
            current = service.getAll();
            view.setStudents(current);
        } catch (Exception e) { view.error(message(e)); }
    }

    private void search() {
        try {
            current = service.search(view.getSearchText());
            view.setStudents(current);
        } catch (Exception e) { view.error(message(e)); }
    }

    private void sort() {
        current = service.sortByName(current);
        view.setStudents(current);
    }

    private void export() {
        try {
            Path p = view.chooseExportPath();
            if (p == null) return;
            service.exportCsv(current, p);
            view.info("CSV exported successfully.");
        } catch (Exception e) { view.error(message(e)); }
    }

    private void importCsv() {
        try {
            Path p = view.chooseImportPath();
            if (p == null) return;
            service.importCsv(p);
            refresh();
            view.info("CSV imported successfully.");
        } catch (Exception e) { view.error(message(e)); }
    }

    private String message(Exception e) {
        Throwable t = e;
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() == null ? e.toString() : t.getMessage();
    }
}
