import java.util.*;

public class FindReplace {
    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length();) {
            if (i + find.length() <= text.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String find = sc.nextLine();
        String replace = sc.nextLine();
        String manual = manualReplace(text, find, replace);
        String builtin = text.replace(find, replace);
        System.out.println("Manual: " + manual);
        System.out.println("Built-in: " + builtin);
        System.out.println("Same? " + manual.equals(builtin));
    }
}
