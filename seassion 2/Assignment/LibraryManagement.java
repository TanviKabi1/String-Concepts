import java.util.*;

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String,Boolean> books = new HashMap<>();
        books.put("Book1", true);
        books.put("Book2", true);
        books.put("Book3", true);

        while (true) {
            System.out.println("1. Borrow  2. Return  3. View  4. Exit");
            int ch = sc.nextInt(); sc.nextLine();

            if (ch == 1) {
                String b = sc.nextLine();
                if (books.containsKey(b) && books.get(b)) {
                    books.put(b,false);
                    System.out.println("Borrowed " + b);
                } else System.out.println("Not available");
            }
            else if (ch == 2) {
                String b = sc.nextLine();
                if (books.containsKey(b) && !books.get(b)) {
                    books.put(b,true);
                    System.out.println("Returned " + b);
                } else System.out.println("Invalid return");
            }
            else if (ch == 3) {
                for (String b : books.keySet())
                    System.out.println(b + " : " + (books.get(b) ? "Available" : "Borrowed"));
            }
            else break;
        }
    }
}
