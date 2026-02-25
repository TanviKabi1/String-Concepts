import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Q2_ECommerce {
    // productId -> stockCount (Atomic for thread safety)
    private Map<String, AtomicInteger> inventory = new ConcurrentHashMap<>();

    // productId -> FIFO Queue of userIds (Waiting List)
    private Map<String, Queue<Integer>> waitingLists = new ConcurrentHashMap<>();

    // Constructor name MUST match Class name
    public Q2_ECommerce() {
        // Initializing stock: 100 units of iPhone
        inventory.put("IPHONE15_256GB", new AtomicInteger(100));
        waitingLists.put("IPHONE15_256GB", new ConcurrentLinkedQueue<>());
    }

    public String checkStock(String productId) {
        AtomicInteger stock = inventory.get(productId);
        int count = (stock != null) ? stock.get() : 0;
        return count + " units available";
    }

    public String purchaseItem(String productId, int userId) {
        AtomicInteger stock = inventory.get(productId);

        if (stock == null) return "Product not found";

        // Logic for high-concurrency purchase
        while (true) {
            int currentStock = stock.get();
            if (currentStock <= 0) {
                // Add to waiting list if stock is empty
                Queue<Integer> waitList = waitingLists.get(productId);
                if (!waitList.contains(userId)) {
                    waitList.add(userId);
                }

                // Calculate position in FIFO queue
                int position = 0;
                int count = 1;
                for (Integer id : waitList) {
                    if (id == userId) {
                        position = count;
                        break;
                    }
                    count++;
                }
                return "Added to waiting list, position #" + position;
            }

            // Compare and Set (CAS) for O(1) thread-safe decrement
            if (stock.compareAndSet(currentStock, currentStock - 1)) {
                return "Success, " + (currentStock - 1) + " units remaining";
            }
        }
    }

    public static void main(String[] args) {
        // Create instance of current class
        Q2_ECommerce system = new Q2_ECommerce();

        // Sample Input/Output Requirements
        System.out.println("checkStock(\"IPHONE15_256GB\") → " + system.checkStock("IPHONE15_256GB"));

        System.out.println("purchaseItem(\"IPHONE15_256GB\", 12345) → " + system.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println("purchaseItem(\"IPHONE15_256GB\", 67890) → " + system.purchaseItem("IPHONE15_256GB", 67890));

        // Simulate 98 more purchases to reach 100 total
        for (int i = 0; i < 98; i++) {
            system.purchaseItem("IPHONE15_256GB", i);
        }

        // Final purchase attempt when stock is 0
        System.out.println("purchaseItem(\"IPHONE15_256GB\", 99999) → " + system.purchaseItem("IPHONE15_256GB", 99999));
    }
}