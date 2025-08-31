import java.util.*;

public class CaseConvert {
    public static char toUpper(char c) {
        if (c >= 'a' && c <= 'z') return (char)(c - 32);
        return c;
    }
    public static char toLower(char c) {
        if (c >= 'A' && c <= 'Z') return (char)(c + 32);
        return c;
    }
    public static String toTitle(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                newWord = true;
            } else if (newWord) {
                sb.append(toUpper(c));
                newWord = false;
            } else {
                sb.append(toLower(c));
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String upper = "", lower = "";
        for (char c : text.toCharArray()) upper += toUpper(c);
        for (char c : text.toCharArray()) lower += toLower(c);
        String title = toTitle(text);
        System.out.println("Upper: " + upper);
        System.out.println("Lower: " + lower);
        System.out.println("Title: " + title);
    }
}
