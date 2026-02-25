import java.util.*;

public class Q1_Social_Media { // Class name is Q1_Social_Media
    static class Entry {
        String key;
        Integer value;
        Entry next;

        Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] buckets;
    private Set<String> registeredUsers;
    private int capacity = 1000;

    // FIX: Constructor name now matches the class name exactly
    public Q1_Social_Media() {
        buckets = new Entry[capacity];
        registeredUsers = new HashSet<>();
        registeredUsers.add("john_doe");
        registeredUsers.add("admin");
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public boolean checkAvailability(String username) {
        trackAttempt(username);
        return !registeredUsers.contains(username.toLowerCase());
    }

    private void trackAttempt(String username) {
        int index = hash(username);
        Entry head = buckets[index];

        while (head != null) {
            if (head.key.equals(username)) {
                head.value++;
                return;
            }
            head = head.next;
        }

        Entry newEntry = new Entry(username, 1);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int i = 1;
        while (suggestions.size() < 3) {
            String candidate = username + i;
            if (!registeredUsers.contains(candidate)) {
                suggestions.add(candidate);
            }
            i++;
        }
        return suggestions;
    }

    public String getMostAttempted() {
        String mostAttempted = null;
        int max = 0;
        for (Entry bucket : buckets) {
            Entry current = bucket;
            while (current != null) {
                if (current.value > max) {
                    max = current.value;
                    mostAttempted = current.key;
                }
                current = current.next;
            }
        }
        return (mostAttempted != null) ? mostAttempted + " (" + max + " attempts)" : "None";
    }

    public static void main(String[] args) {
        // FIX: Instantiate the correct class name
        Q1_Social_Media manager = new Q1_Social_Media();

        System.out.println("Is 'john_doe' available? " + manager.checkAvailability("john_doe"));
        System.out.println("Suggestions: " + manager.suggestAlternatives("john_doe"));

        manager.checkAvailability("admin");
        manager.checkAvailability("admin");
        System.out.println("Most popular attempt: " + manager.getMostAttempted());
    }
}