class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Build valid intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            for (int i = start; i <= end; i++) {

                int ch = s.charAt(i) - 'a';

                // Character has an occurrence before start
                if (first[ch] < start) {
                    valid = false;
                    break;
                }

                // Need to expand interval
                end = Math.max(end, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Step 3: Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        // Step 4: Greedy selection
        List<String> result = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {

                result.add(s.substring(start, end + 1));

                previousEnd = end;
            }
        }

        return result;
    }
}