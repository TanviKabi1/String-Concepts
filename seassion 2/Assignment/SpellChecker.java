import java.util.*;

public class SpellChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> dictionary = Arrays.asList("apple","banana","orange","grape","mango");

        System.out.println("Enter a sentence:");
        String[] words = sc.nextLine().split(" ");

        for (String w : words) {
            if (!dictionary.contains(w.toLowerCase())) {
                System.out.println(w + " is misspelled.");
            }
        }
    }
}
