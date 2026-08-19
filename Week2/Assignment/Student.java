
class StudentInfo {
    private String name;
    private int age;
    private String studentId;
    private String course;

    public StudentInfo(String name, int age, String studentId, String course) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("Student Information");
        System.out.println("-------------------");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Student ID: " + studentId);
        System.out.println("Course: " + course);
    }
}

public class Student {
    public static void main(String[] args) {

        StudentInfo st = new StudentInfo(
            "Aditya",
            20,
            "S001",
            "Computer Science"
        );

        st.displayInfo();
    }
}