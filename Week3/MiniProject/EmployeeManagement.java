import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {

    static ArrayList<Employee> employees = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static String fileName = "employees.txt";

    public static void main(String[] args) {

        loadEmployees();

        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Save Employees");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        displayEmployees();
                        break;

                    case 3:
                        searchEmployee();
                        break;

                    case 4:
                        deleteEmployee();
                        break;

                    case 5:
                        saveEmployees();
                        break;

                    case 6:
                        saveEmployees();
                        System.out.println("Program exited.");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {

                System.out.println("Invalid input! Please enter a number.");

                sc.nextLine();
                choice = 0;
            }

        } while (choice != 6);

        sc.close();
    }

    // Add Employee
    public static void addEmployee() {

        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            // Check duplicate ID
            for (Employee emp : employees) {
                if (emp.getId() == id) {
                    System.out.println("Employee ID already exists!");
                    return;
                }
            }

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            if (name.trim().isEmpty()) {
                throw new Exception("Name cannot be empty.");
            }

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            if (department.trim().isEmpty()) {
                throw new Exception("Department cannot be empty.");
            }

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            if (salary < 0) {
                throw new Exception("Salary cannot be negative.");
            }

            Employee employee = new Employee(
                    id,
                    name,
                    department,
                    salary
            );

            employees.add(employee);

            System.out.println("Employee added successfully!");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            sc.nextLine();
        }
    }

    // Display Employees
    public static void displayEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n========== EMPLOYEE LIST ==========");

        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Search Employee
    public static void searchEmployee() {

        try {
            System.out.print("Enter Employee ID to search: ");
            int id = sc.nextInt();
            sc.nextLine();

            boolean found = false;

            for (Employee emp : employees) {

                if (emp.getId() == id) {

                    System.out.println("\nEmployee Found:");
                    System.out.println(emp);

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {

            System.out.println("Invalid Employee ID.");
            sc.nextLine();
        }
    }

    // Delete Employee
    public static void deleteEmployee() {

        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            boolean found = false;

            for (int i = 0; i < employees.size(); i++) {

                if (employees.get(i).getId() == id) {

                    employees.remove(i);

                    System.out.println("Employee deleted successfully.");

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {

            System.out.println("Invalid Employee ID.");
            sc.nextLine();
        }
    }

    // Save Employees to file
    public static void saveEmployees() {

        try {

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileName)
            );

            for (Employee emp : employees) {

                writer.write(emp.toFileString());
                writer.newLine();
            }

            writer.close();

            System.out.println("Employee data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving employee data.");
        }
    }

    // Load Employees from file
    public static void loadEmployees() {

        File file = new File(fileName);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(fileName)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {

                    int id = Integer.parseInt(data[0]);

                    String name = data[1];

                    String department = data[2];

                    double salary = Double.parseDouble(data[3]);

                    Employee employee = new Employee(
                            id,
                            name,
                            department,
                            salary
                    );

                    employees.add(employee);
                }
            }

            reader.close();

            System.out.println("Employee data loaded successfully.");

        } catch (IOException e) {

            System.out.println("Error loading employee data.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid data found in employees.txt.");
        }
    }
}