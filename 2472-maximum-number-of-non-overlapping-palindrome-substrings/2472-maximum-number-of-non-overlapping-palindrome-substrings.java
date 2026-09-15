class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        // palindrome[i][j] tells whether s[i...j] is palindrome
        boolean[][] palindrome = new boolean[n][n];

        // Build palindrome DP
        for (int i = n - 1; i >= 0; i--) {

            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || palindrome[i + 1][j - 1])) {

                    palindrome[i][j] = true;
                }
            }
        }

        // dp[i] = maximum number of palindromes
        // we can select from first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            // Don't use a palindrome ending at i-1
            dp[i] = dp[i - 1];

            // Try every possible starting point
            for (int j = 0; j < i; j++) {

                // Check length
                if (i - j >= k && palindrome[j][i - 1]) {

                    dp[i] = Math.max(
                        dp[i],
                        dp[j] + 1
                    );
                }
            }
        }

        return dp[n];
    }
}