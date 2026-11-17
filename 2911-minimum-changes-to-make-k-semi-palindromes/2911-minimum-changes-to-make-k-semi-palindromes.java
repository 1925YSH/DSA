
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Try all proper divisors of the substring length
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Each group contains positions with the same
                    // remainder modulo d.
                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = i + r + ((len - 1 - r) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        // dp[p][i] = minimum changes to partition
        // first i characters into exactly p substrings
        int INF = 1_000_000_000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
