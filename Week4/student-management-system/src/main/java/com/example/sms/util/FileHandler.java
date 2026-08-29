package com.example.sms.util;

import com.example.sms.model.Student;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public void writeCsv(List<Student> students, Path path) {
        try (BufferedWriter w = Files.newBufferedWriter(path)) {
            w.write("name,email,age,course");
            w.newLine();
            for (Student s : students) {
                w.write(csv(s.getName()) + "," + csv(s.getEmail()) + "," +
                        s.getAge() + "," + csv(s.getCourse()));
                w.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not export CSV: " + e.getMessage(), e);
        }
    }

    public List<Student> readCsv(Path path) {
        List<Student> result = new ArrayList<>();
        try (BufferedReader r = Files.newBufferedReader(path)) {
            String line = r.readLine();
            if (line == null) return result;

            while ((line = r.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                if (p.length != 4)
                    throw new IllegalArgumentException("Invalid CSV row: " + line);

                Student s = new Student(
                        unquote(p[0].trim()),
                        unquote(p[1].trim()),
                        Integer.parseInt(p[2].trim()),
                        unquote(p[3].trim())
                );
                ValidationUtil.validate(s);
                result.add(s);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Could not read CSV: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CSV contains an invalid age.", e);
        }
    }

    private String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    private String unquote(String value) {
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\""))
            return value.substring(1, value.length() - 1).replace("\"\"", "\"");
        return value;
    }
}
