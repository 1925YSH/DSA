import java.util.*;

class Solution {
    public List<String> commonChars(String[] words) {

        List<String> result = new ArrayList<>();

        // Initially set frequency to a large value
        int[] minFreq = new int[26];
        Arrays.fill(minFreq, Integer.MAX_VALUE);

        for (String word : words) {

            int[] freq = new int[26];

            // Count characters of current word
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            // Keep minimum frequency across all words
            for (int i = 0; i < 26; i++) {
                minFreq[i] = Math.min(minFreq[i], freq[i]);
            }
        }

        // Build result using minimum frequencies
        for (int i = 0; i < 26; i++) {

            while (minFreq[i] > 0) {
                result.add(String.valueOf((char) ('a' + i)));
                minFreq[i]--;
            }
        }

        return result;
    }
}