import java.util.*;

public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String,Integer> marks = new HashMap<>();

        System.out.println("Enter number of students:");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = sc.nextLine();
            System.out.println("Enter marks:");
            int m = sc.nextInt(); sc.nextLine();
            marks.put(name, m);
        }

        for (String s : marks.keySet()) {
            int m = marks.get(s);
            char grade;
            if (m >= 90) grade = 'A';
            else if (m >= 75) grade = 'B';
            else if (m >= 50) grade = 'C';
            else grade = 'F';
            System.out.println(s + " - " + grade);
        }
    }
}
