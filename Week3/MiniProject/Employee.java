public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Get ID
    public int getId() {
        return id;
    }

    // Get Name
    public String getName() {
        return name;
    }

    // Get Department
    public String getDepartment() {
        return department;
    }

    // Get Salary
    public double getSalary() {
        return salary;
    }

    // Convert employee details into a string
    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Department: " + department +
                ", Salary: " + salary;
    }

    // Convert employee to file format
    public String toFileString() {
        return id + "," + name + "," + department + "," + salary;
    }
}