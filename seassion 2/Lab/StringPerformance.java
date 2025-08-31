import java.util.*;

public class StringPerformance {
    public static long testString(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "a";
        long end = System.currentTimeMillis();
        System.out.println("String length: " + s.length());
        return end - start;
    }
    public static long testBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        System.out.println("Builder length: " + sb.length());
        return end - start;
    }
    public static long testBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        System.out.println("Buffer length: " + sb.length());
        return end - start;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("String time: " + testString(n) + " ms");
        System.out.println("StringBuilder time: " + testBuilder(n) + " ms");
        System.out.println("StringBuffer time: " + testBuffer(n) + " ms");
    }
}
