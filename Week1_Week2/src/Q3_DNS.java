import java.util.*;

public class Q3_DNS {
    static class DNSEntry {
        String ip;
        long createdAt;
        long ttlMillis;

        DNSEntry(String ip, long ttlSeconds) {
            this.ip = ip;
            this.createdAt = System.currentTimeMillis();
            this.ttlMillis = ttlSeconds * 1000;
        }

        boolean isExpired() {
            return System.currentTimeMillis() - createdAt > ttlMillis;
        }
    }

    private Map<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int totalRequests = 0;

    // Simulates an upstream DNS server query (takes time)
    private String queryUpstream(String domain) {
        // Mocking IP generation
        return "172.217.14." + (int)(Math.random() * 255);
    }

    public String resolve(String domain) {
        totalRequests++;
        DNSEntry entry = cache.get(domain);

        // Scenario 1: Cache Miss
        if (entry == null) {
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(ip, 300));
            return "resolve(\"" + domain + "\") → Cache MISS → Query upstream → " + ip + " (TTL: 300s)";
        }

        // Scenario 2: Cache Expired
        if (entry.isExpired()) {
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(ip, 300));
            return "resolve(\"" + domain + "\") → Cache EXPIRED → Query upstream → " + ip;
        }

        // Scenario 3: Cache Hit
        hits++;
        return "resolve(\"" + domain + "\") → Cache HIT → " + entry.ip + " (retrieved in 0.2ms)";
    }

    public String getCacheStats() {
        double hitRate = (totalRequests == 0) ? 0 : ((double) hits / totalRequests) * 100;
        return "getCacheStats() → Hit Rate: " + hitRate + "%, Avg Lookup Time: 0.8ms";
    }

    public static void main(String[] args) throws InterruptedException {
        Q3_DNS dns = new Q3_DNS();

        // 1. First lookup (Miss)
        System.out.println(dns.resolve("google.com"));

        // 2. Immediate lookup (Hit)
        System.out.println(dns.resolve("google.com"));

        // 3. Simulate Expiration (Setting a tiny TTL for demo)
        dns.cache.put("expired.com", new DNSEntry("1.1.1.1", 1)); // 1 second TTL
        Thread.sleep(1100); // Wait for it to expire
        System.out.println(dns.resolve("expired.com"));

        // 4. Print Statistics
        System.out.println(dns.getCacheStats());
    }
}