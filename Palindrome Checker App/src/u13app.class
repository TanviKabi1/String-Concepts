import java.util.Stack;

public class PalindromeCheckerApp {
    public static void main(String[] args) {
        PalindromeStrategy strategy = new StackStrategy();
        String testInput = "racecar";

        boolean isPalindrome = strategy.check(testInput);
        System.out.println("Result: " + isPalindrome);
    }
}

interface PalindromeStrategy {
    boolean check(String input);
}

class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        if (input == null) return false;

        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}
