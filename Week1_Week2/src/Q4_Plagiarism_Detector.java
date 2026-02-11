import java.util.*;

public class Q4_Plagiarism_Detector {
    // Inverted Index: n-gram -> Set of Document IDs that contain it
    private Map<String, Set<String>> ngramIndex = new HashMap<>();
    // Store original document n-gram counts for similarity math
    private Map<String, Integer> docTotalNgrams = new HashMap<>();

    public Q4_Plagiarism_Detector() {
        // Mocking some existing database entries
        seedDatabase("essay_089.txt", "the quick brown fox jumps over the lazy dog");
        seedDatabase("essay_092.txt", "plagiarism is a serious academic offense that results in failure");
    }

    private void seedDatabase(String docId, String content) {
        List<String> ngrams = extractNgrams(content, 5);
        docTotalNgrams.put(docId, ngrams.size());
        for (String ngram : ngrams) {
            ngramIndex.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    // Breaks text into sequences of N words
    private List<String> extractNgrams(String text, int n) {
        String[] words = text.toLowerCase().split("\\s+");
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(words[i + j]).append(" ");
            }
            ngrams.add(sb.toString().trim());
        }
        return ngrams;
    }

    public void analyzeDocument(String fileName, String content) {
        List<String> inputNgrams = extractNgrams(content, 5);
        int totalNgrams = inputNgrams.size();
        System.out.println("analyzeDocument(\"" + fileName + "\")");
        System.out.println("→ Extracted " + totalNgrams + " n-grams");

        // Map to count matches per document: docId -> matchCount
        Map<String, Integer> matches = new HashMap<>();
        for (String ngram : inputNgrams) {
            if (ngramIndex.containsKey(ngram)) {
                for (String docId : ngramIndex.get(ngram)) {
                    matches.put(docId, matches.getOrDefault(docId, 0) + 1);
                }
            }
        }

        // Output results for each matching document
        for (Map.Entry<String, Integer> entry : matches.entrySet()) {
            String docId = entry.getKey();
            int matchCount = entry.getValue();
            double similarity = ((double) matchCount / totalNgrams) * 100;

            String status = (similarity > 50) ? "(PLAGIARISM DETECTED)" : "(suspicious)";

            System.out.println("→ Found " + matchCount + " matching n-grams with \"" + docId + "\"");
            System.out.printf("→ Similarity: %.1f%% %s\n", similarity, status);
        }
    }

    public static void main(String[] args) {
        Q4_Plagiarism_Detector detector = new Q4_Plagiarism_Detector();

        // Sample input content that overlaps with essay_092
        String studentEssay = "plagiarism is a serious academic offense that results in severe failure for students";
        detector.analyzeDocument("essay_123.txt", studentEssay);
    }
}