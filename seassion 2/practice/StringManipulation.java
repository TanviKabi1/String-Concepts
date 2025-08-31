import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        // 1. Remove extra spaces (trim)
        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);

        // 2. Replace all spaces with underscores
        String replacedSpaces = trimmed.replace(' ', '_');
        System.out.println("Spaces replaced with underscores: " + replacedSpaces);

        // 3. Remove all digits using regex
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Removed all digits: " + noDigits);

        // 4. Split sentence into words array
        String[] words = trimmed.split("\\s+");
        System.out.println("Words array: " + Arrays.toString(words));

        // 5. Rejoin words with " | " separator
        String rejoined = String.join(" | ", words);
        System.out.println("Rejoined with | : " + rejoined);

        // Additional methods usage
        System.out.println("Without punctuation: " + removePunctuation(trimmed));
        System.out.println("Capitalized words: " + capitalizeWords(trimmed));
        System.out.println("Reversed word order: " + reverseWordOrder(trimmed));
        countWordFrequency(trimmed);

        scanner.close();
    }

    // Remove all punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    // Capitalize first letter of each word
    public static String capitalizeWords(String text) {
        String[] words = text.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0)
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }

    // Reverse the order of words
    public static String reverseWordOrder(String text) {
        String[] words = text.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        }
        return sb.toString();
    }

    // Count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.trim().toLowerCase().split("\\s+");
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word Frequencies:");
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
