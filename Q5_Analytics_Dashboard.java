import java.util.*;

public class Q5_Analytics_Dashboard{
    // pageUrl -> visitCount
    private Map<String, Integer> pageViews = new HashMap<>();
    // pageUrl -> Set of unique userIds
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    // source -> visitCount
    private Map<String, Integer> sourceCounts = new HashMap<>();

    private int totalViews = 0;

    public void processEvent(String url, String userId, String source) {
        totalViews++;

        // 1. Update Page Views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // 2. Update Unique Visitors (O(1) average lookup)
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);

        // 3. Update Traffic Sources
        sourceCounts.put(source, sourceCounts.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {
        System.out.println("\ngetDashboard() →");
        System.out.println("Top Pages:");

        // Use a PriorityQueue (Min-Heap) to find Top N efficiently
        PriorityQueue<Map.Entry<String, Integer>> topPages = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        for (Map.Entry<String, Integer> entry : pageViews.entrySet()) {
            topPages.offer(entry);
            if (topPages.size() > 10) topPages.poll(); // Keep only top 10
        }

        // Convert heap to list and reverse for display
        List<Map.Entry<String, Integer>> sortedTop = new ArrayList<>(topPages);
        sortedTop.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedTop) {
            String url = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(url).size();
            System.out.printf("%d. %s - %,d views (%,d unique)\n", rank++, url, views, unique);
        }

        System.out.println("\nTraffic Sources:");
        for (Map.Entry<String, Integer> entry : sourceCounts.entrySet()) {
            double percentage = (double) entry.getValue() / totalViews * 100;
            System.out.printf("%s: %.0f%% ", entry.getKey(), percentage);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Q5_Analytics_Dashboard system = new Q5_Analytics_Dashboard();

        // Simulating incoming stream
        system.processEvent("/article/breaking-news", "user_123", "Google");
        system.processEvent("/article/breaking-news", "user_456", "Facebook");

        // Mocking high volume data
        for(int i=0; i<15421; i++) system.processEvent("/article/breaking-news", "u"+i, "Google");
        for(int i=0; i<12091; i++) system.processEvent("/sports/championship", "s"+i, "Direct");

        system.getDashboard();
    }
}