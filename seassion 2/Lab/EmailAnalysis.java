import java.util.*;

public class EmailAnalysis {
    
    // Method to validate email format
    public static boolean isValidEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAtPos = email.lastIndexOf('@');
        int dotPos = email.lastIndexOf('.');
        
        // Basic checks
        if (atPos <= 0) return false; // no username
        if (atPos != lastAtPos) return false; // multiple '@'
        if (dotPos < atPos + 2) return false; // '.' must come after '@'
        if (dotPos >= email.length() - 1) return false; // '.' can't be last
        return true;
    }

    // Method to extract email parts
    public static String[] extractParts(String email) {
        String username = email.substring(0, email.indexOf('@'));
        String domain = email.substring(email.indexOf('@') + 1);
        String domainName = domain.substring(0, domain.indexOf('.'));
        String extension = domain.substring(domain.indexOf('.') + 1);
        return new String[]{username, domain, domainName, extension};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of emails:");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        
        String[] emails = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            emails[i] = sc.nextLine();
        }

        int validCount = 0, invalidCount = 0;
        HashMap<String, Integer> domainCount = new HashMap<>();
        int totalUsernameLength = 0;

        System.out.printf("%-25s %-12s %-15s %-12s %-10s %-10s\n", 
                          "Email", "Username", "Domain", "DomainName", "Extension", "Valid?");
        System.out.println("--------------------------------------------------------------------------------");

        for (String email : emails) {
            if (isValidEmail(email)) {
                String[] parts = extractParts(email);
                validCount++;
                totalUsernameLength += parts[0].length();

                // Count domain occurrences
                domainCount.put(parts[1], domainCount.getOrDefault(parts[1], 0) + 1);

                System.out.printf("%-25s %-12s %-15s %-12s %-10s %-10s\n", 
                                  email, parts[0], parts[1], parts[2], parts[3], "Yes");
            } else {
                invalidCount++;
                System.out.printf("%-25s %-12s %-15s %-12s %-10s %-10s\n", 
                                  email, "-", "-", "-", "-", "No");
            }
        }

        // Find most common domain
        String mostCommonDomain = "-";
        int maxCount = 0;
        for (String d : domainCount.keySet()) {
            if (domainCount.get(d) > maxCount) {
                maxCount = domainCount.get(d);
                mostCommonDomain = d;
            }
        }

        // Summary
        System.out.println("\nSummary:");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);
        if (validCount > 0)
            System.out.println("Average Username Length: " + (totalUsernameLength / validCount));
        System.out.println("Most Common Domain: " + mostCommonDomain);
        
        sc.close();
    }
}
