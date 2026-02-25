public class StringPerformanceComparison {

    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test string concatenation with regular String (inefficient)
        long startTime = System.nanoTime();
        String result1 = concatenateWithString(1000);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        // Test string concatenation with StringBuilder (efficient, not thread-safe)
        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        // Test string concatenation with StringBuffer (efficient, thread-safe)
        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        System.out.println();

        demonstrateStringBuilderMethods();

        System.out.println();

        demonstrateThreadSafety();

        System.out.println();

        compareStringComparisonMethods();

        System.out.println();

        demonstrateMemoryEfficiency();
    }

    // Inefficient concatenation using String
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    // Efficient concatenation using StringBuilder (not thread-safe)
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Efficient concatenation using StringBuffer (thread-safe)
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Demonstrate basic StringBuilder methods
    public static void demonstrateStringBuilderMethods() {
        System.out.println("=== StringBuilder Methods Demo ===");
        StringBuilder sb = new StringBuilder("Hello World");

        sb.append("!"); // append
        System.out.println("After append: " + sb);

        sb.insert(5, ","); // insert
        System.out.println("After insert: " + sb);

        sb.delete(5, 6); // delete
        System.out.println("After delete: " + sb);

        sb.deleteCharAt(sb.length() - 1); // deleteCharAt
        System.out.println("After deleteCharAt: " + sb);

        sb.reverse(); // reverse
        System.out.println("After reverse: " + sb);

        sb.replace(0, 5, "Hi"); // replace
        System.out.println("After replace: " + sb);

        sb.setCharAt(0, 'h'); // setCharAt
        System.out.println("After setCharAt: " + sb);

        System.out.println("Current capacity: " + sb.capacity()); // capacity

        sb.ensureCapacity(50); // ensureCapacity
        System.out.println("Capacity after ensureCapacity(50): " + sb.capacity());

        sb.trimToSize(); // trimToSize
        System.out.println("Capacity after trimToSize(): " + sb.capacity());
    }

    // Demonstrate thread safety of StringBuffer with simple multi-thread example
    public static void demonstrateThreadSafety() {
        System.out.println("=== StringBuffer Thread Safety Demo ===");
        StringBuffer sb = new StringBuffer();

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                sb.append("x");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("Expected length: 200, Actual length: " + sb.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Compare string comparison methods
    public static void compareStringComparisonMethods() {
        System.out.println("=== String Comparison Methods Demo ===");
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("str1 == str2: " + (str1 == str2)); // true because both refer to same literal
        System.out.println("str1 == str3: " + (str1 == str3)); // false, different objects
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true, content equal
        System.out.println("str1.equalsIgnoreCase(\"hello\"): " + str1.equalsIgnoreCase("hello")); // true
        System.out.println("str1.compareTo(\"World\"): " + str1.compareTo("World")); // negative
        System.out.println("str1.compareToIgnoreCase(\"hello\"): " + str1.compareToIgnoreCase("hello")); // 0
    }

    // Rough demonstration of memory efficiency
    public static void demonstrateMemoryEfficiency() {
        System.out.println("=== Memory Efficiency Demo ===");
        Runtime runtime = Runtime.getRuntime();

        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used by String concatenation: " + (afterMemory - beforeMemory) + " bytes");

        beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
        }
        afterMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used by StringBuilder: " + (afterMemory - beforeMemory) + " bytes");

        System.out.println("(Note: Memory values are approximate and JVM dependent.)");
    }
}
