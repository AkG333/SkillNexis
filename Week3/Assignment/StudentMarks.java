import java.util.ArrayList;
import java.util.Scanner;

public class StudentMarks {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter marks for student " + (i + 1) + ": ");
            int mark = sc.nextInt();

            if (mark >= 0 && mark <= 100) {
                marks.add(mark);
            } else {
                System.out.println("Invalid marks! Enter between 0 and 100.");
                i--;
            }
        }

        System.out.println("\nStudent Marks:");

        for (int i = 0; i < marks.size(); i++) {
            System.out.println(
                "Student " + (i + 1) + ": " + marks.get(i)
            );
        }

        int highest = marks.get(0);
        int lowest = marks.get(0);

        for (int mark : marks) {

            if (mark > highest) {
                highest = mark;
            }

            if (mark < lowest) {
                lowest = mark;
            }
        }

        System.out.println("\nHighest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);

        sc.close();
    }
}