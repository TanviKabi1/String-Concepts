import java.util.*;

public class TextJustifier {
    public static void justify(String text, int width) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String w : words) {
            if (line.length() + w.length() + 1 > width) {
                System.out.println(line.toString());
                line = new StringBuilder();
            }
            if (line.length() > 0) line.append(" ");
            line.append(w);
        }
        if (line.length() > 0) System.out.println(line.toString());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int width = sc.nextInt();
        justify(text, width);
    }
}
