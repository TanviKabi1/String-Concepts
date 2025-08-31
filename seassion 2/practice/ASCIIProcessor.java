import java.util.Scanner;

public class ASCIIProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // For each character: show details
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println("Character: " + ch + " | ASCII: " + ascii + " | Type: " + classifyCharacter(ch));
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("  Uppercase: " + upper + " (ASCII: " + (int)upper + ")"
                                 + " | Lowercase: " + lower + " (ASCII: " + (int)lower + ")");
                System.out.println("  ASCII diff: " + Math.abs((int)upper - (int)lower));
            }
        }

        // ASCII Art Example
        System.out.println("\nASCII Art: ");
        for (int i = 0; i < input.length(); i++) {
            System.out.print((char)((int)input.charAt(i)) + " ");
        }
        System.out.println();

        // Example Caesar cipher
        System.out.print("\nEnter shift value for Caesar Cipher: ");
        int shift = scanner.nextInt();
        String ciphered = caesarCipher(input, shift);
        System.out.println("Caesar Cipher result: " + ciphered);

        // ASCII Table demo
        System.out.println("\nASCII Table for 65 to 90:");
        displayASCIITable(65, 90);

        // String to ASCII and back
        int[] asciiArr = stringToASCII(input);
        System.out.print("\nASCII Array: ");
        for (int val : asciiArr) System.out.print(val + " ");
        System.out.println();
        System.out.println("Back to String: " + asciiToString(asciiArr));

        scanner.close();
    }

    // Classify character
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        else if (Character.isLowerCase(ch)) return "Lowercase Letter";
        else if (Character.isDigit(ch)) return "Digit";
        else return "Special Character";
    }

    // Toggle case using ASCII logic
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char)(ch + 32);
        else if (Character.isLowerCase(ch)) return (char)(ch - 32);
        else return ch;
    }

    // Simple Caesar cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                sb.append((char)((ch - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                sb.append((char)((ch - 'a' + shift) % 26 + 'a'));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // Display ASCII table for range
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " : " + (char)i);
        }
    }

    // String to ASCII array
    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int)text.charAt(i);
        }
        return arr;
    }

    // ASCII array back to String
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char)val);
        }
        return sb.toString();
    }
}
