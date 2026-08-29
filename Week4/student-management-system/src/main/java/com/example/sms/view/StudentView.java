package com.example.sms.view;

import com.example.sms.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.file.Path;
import java.util.List;

public class StudentView extends JFrame {
    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField ageField = new JTextField();
    private final JTextField courseField = new JTextField();
    private final JTextField searchField = new JTextField();

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Name", "Email", "Age", "Course"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);

    private Runnable addAction, updateAction, deleteAction, refreshAction;
    private Runnable searchAction, sortAction, exportAction, importAction;

    public StudentView() {
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        JPanel form = new JPanel(new GridLayout(2, 5, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Student Details"));
        form.add(field("ID", idField));
        form.add(field("Name", nameField));
        form.add(field("Email", emailField));
        form.add(field("Age", ageField));
        form.add(field("Course", courseField));

        JButton add = button("Add", e -> addAction.run());
        JButton update = button("Update", e -> updateAction.run());
        JButton delete = button("Delete", e -> deleteAction.run());
        JButton refresh = button("Refresh", e -> refreshAction.run());
        form.add(add); form.add(update); form.add(delete); form.add(refresh);
        form.add(new JLabel());

        JPanel toolbar = new JPanel(new BorderLayout(8, 8));
        JPanel search = new JPanel(new BorderLayout(5, 0));
        search.add(new JLabel("Search:"), BorderLayout.WEST);
        search.add(searchField, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(button("Search", e -> searchAction.run()));
        actions.add(button("Sort A-Z", e -> sortAction.run()));
        actions.add(button("Export CSV", e -> exportAction.run()));
        actions.add(button("Import CSV", e -> importAction.run()));
        toolbar.add(search, BorderLayout.CENTER);
        toolbar.add(actions, BorderLayout.EAST);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                int r = table.getSelectedRow();
                idField.setText(String.valueOf(model.getValueAt(r, 0)));
                nameField.setText(String.valueOf(model.getValueAt(r, 1)));
                emailField.setText(String.valueOf(model.getValueAt(r, 2)));
                ageField.setText(String.valueOf(model.getValueAt(r, 3)));
                courseField.setText(String.valueOf(model.getValueAt(r, 4)));
            }
        });

        JPanel top = new JPanel(new BorderLayout(0, 8));
        top.add(form, BorderLayout.NORTH);
        top.add(toolbar, BorderLayout.SOUTH);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        root.add(top, BorderLayout.NORTH);
        root.add(new JScrollPane(table), BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel field(String label, JTextField f) {
        JPanel p = new JPanel(new BorderLayout(3, 3));
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(f, BorderLayout.CENTER);
        return p;
    }

    private JButton button(String text, java.awt.event.ActionListener a) {
        JButton b = new JButton(text);
        b.addActionListener(a);
        return b;
    }

    public void showWindow() { setVisible(true); }

    public Student readStudent() {
        int id = idField.getText().isBlank() ? 0 : Integer.parseInt(idField.getText().trim());
        int age = Integer.parseInt(ageField.getText().trim());
        return new Student(id, nameField.getText().trim(), emailField.getText().trim(),
                age, courseField.getText().trim());
    }

    public void setStudents(List<Student> students) {
        model.setRowCount(0);
        for (Student s : students)
            model.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getAge(), s.getCourse()});
    }

    public String getSearchText() { return searchField.getText().trim(); }

    public void clearForm() {
        idField.setText(""); nameField.setText(""); emailField.setText("");
        ageField.setText(""); courseField.setText("");
    }

    public void info(String message) {
        JOptionPane.showMessageDialog(this, message, "Student Management",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void error(String message) {
        JOptionPane.showMessageDialog(this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirm(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public Path chooseExportPath() {
        JFileChooser c = new JFileChooser();
        c.setDialogTitle("Export students to CSV");
        return c.showSaveDialog(this) == JFileChooser.APPROVE_OPTION
                ? c.getSelectedFile().toPath() : null;
    }

    public Path chooseImportPath() {
        JFileChooser c = new JFileChooser();
        c.setDialogTitle("Import students from CSV");
        return c.showOpenDialog(this) == JFileChooser.APPROVE_OPTION
                ? c.getSelectedFile().toPath() : null;
    }

    public void onAdd(Runnable r) { addAction = r; }
    public void onUpdate(Runnable r) { updateAction = r; }
    public void onDelete(Runnable r) { deleteAction = r; }
    public void onRefresh(Runnable r) { refreshAction = r; }
    public void onSearch(Runnable r) { searchAction = r; }
    public void onSort(Runnable r) { sortAction = r; }
    public void onExport(Runnable r) { exportAction = r; }
    public void onImport(Runnable r) { importAction = r; }
}
