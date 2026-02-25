public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Original length
        System.out.println("Original length: " + sampleText.length());

        // 2. Trim and new length
        String trimmed = sampleText.trim();
        System.out.println("Trimmed: '" + trimmed + "', New length: " + trimmed.length());

        // 3. Character at index 5 of original (including spaces)
        System.out.println("Character at index 5: " + sampleText.charAt(5));

        // 4. Extract substring "Programming"
        String programming = sampleText.substring(sampleText.indexOf("Programming"), sampleText.indexOf("Programming") + "Programming".length());
        System.out.println("Extracted substring: " + programming);

        // 5. Index of "Fun"
        System.out.println("Index of 'Fun': " + sampleText.indexOf("Fun"));

        // 6. Contains "Java" (case-sensitive)
        System.out.println("Contains 'Java'? " + sampleText.contains("Java"));

        // 7. Starts with "Java" after trimming
        System.out.println("Starts with 'Java' after trim? " + trimmed.startsWith("Java"));

        // 8. Ends with '!'
        System.out.println("Ends with '!': " + trimmed.endsWith("!"));

        // 9. Uppercase
        System.out.println("Uppercase: " + sampleText.toUpperCase());

        // 10. Lowercase
        System.out.println("Lowercase: " + sampleText.toLowerCase());
    }
}
