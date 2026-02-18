//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class u2app {
    public static void main(String[] args) {
        String original = "madam";
        String reverse = "";
        int length = original.length();

        for(int i = length - 1; i >= 0; --i) {
            reverse = reverse + original.charAt(i);
        }

        if (original.equalsIgnoreCase(reverse)) {
            System.out.println("The string '" + original + "' is a palindrome.");
        } else {
            System.out.println("The string '" + original + "' is NOT a palindrome.");
        }

    }
}
