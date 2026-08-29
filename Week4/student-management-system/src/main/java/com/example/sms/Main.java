package com.example.sms;

import com.example.sms.controller.StudentController;
import com.example.sms.repository.StudentRepository;
import com.example.sms.repository.StudentRepositoryJdbc;
import com.example.sms.service.StudentService;
import com.example.sms.util.DatabaseConnection;
import com.example.sms.util.FileHandler;
import com.example.sms.view.StudentView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseConnection.testConnection();
                StudentRepository repository = new StudentRepositoryJdbc();
                StudentService service = new StudentService(repository, new FileHandler());
                StudentView view = new StudentView();
                new StudentController(service, view);
                view.showWindow();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Could not start application:\n" + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
