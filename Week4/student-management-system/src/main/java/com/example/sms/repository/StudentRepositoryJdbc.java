package com.example.sms.repository;

import com.example.sms.model.Student;
import com.example.sms.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJdbc implements StudentRepository {

    @Override
    public Student add(Student s) {
        String sql = "INSERT INTO students(name,email,age,course) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fill(ps, s);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
            return s;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to add student: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Student s) {
        String sql = "UPDATE students SET name=?, email=?, age=?, course=? WHERE id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            fill(ps, s);
            ps.setInt(5, s.getId());
            if (ps.executeUpdate() == 0)
                throw new RuntimeException("Student ID not found: " + s.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update student: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> findAll() {
        return query("SELECT id,name,email,age,course FROM students ORDER BY id");
    }

    @Override
    public List<Student> search(String keyword) {
        String sql = """
                SELECT id,name,email,age,course FROM students
                WHERE LOWER(name) LIKE ? OR LOWER(email) LIKE ? OR LOWER(course) LIKE ?
                ORDER BY id
                """;
        String p = "%" + keyword.toLowerCase() + "%";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p);
            ps.setString(2, p);
            ps.setString(3, p);
            try (ResultSet rs = ps.executeQuery()) {
                return map(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to search students: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertBatch(List<Student> students) {
        String sql = "INSERT INTO students(name,email,age,course) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            try {
                for (Student s : students) {
                    fill(ps, s);
                    ps.addBatch();
                }
                ps.executeBatch();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("CSV import failed: " + e.getMessage(), e);
        }
    }

    private void fill(PreparedStatement ps, Student s) throws SQLException {
        ps.setString(1, s.getName());
        ps.setString(2, s.getEmail());
        ps.setInt(3, s.getAge());
        ps.setString(4, s.getCourse());
    }

    private List<Student> query(String sql) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return map(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load students: " + e.getMessage(), e);
        }
    }

    private List<Student> map(ResultSet rs) throws SQLException {
        List<Student> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("course")
            ));
        }
        return result;
    }
}
