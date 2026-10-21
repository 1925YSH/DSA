
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);

            if (i > 0 && c < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        if (s.charAt(0) == maxChar &&
            s.charAt(n - 1) == minChar) {

            // If either extreme appears more than once,
            // two operations are enough.
            if (s.indexOf(maxChar) != s.lastIndexOf(maxChar) ||
                s.indexOf(minChar) != s.lastIndexOf(minChar)) {
                return 2;
            }

            return 3;
        }

        return 2;
    }
}
