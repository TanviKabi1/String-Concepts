public class PalindromeCheckerApp {

    public static void main(String[] args) {
        PalindromeService service = new PalindromeService();
        String testInput = "radar";
        boolean result = service.checkPalindrome(testInput);
        System.out.println("Is '" + testInput + "' a palindrome? " + result);
    }
}

class PalindromeService {

    public boolean checkPalindrome(String input) {
        if (input == null) {
            return false;
        }

        int start = 0;
        int end = input.length() - 1;

        while (start < end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
