class Solution {
    public int numberOfSets(int n, int k) {

        int MOD = 1000000007;

        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];

        // f = ways where we are NOT currently drawing a segment
        // g = ways where we ARE currently drawing/extending a segment

        f[1][0] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Don't use point i-1 to extend/start a segment
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;

                // Continue the current state
                g[i][j] = g[i - 1][j];

                if (j > 0) {

                    // Start a new segment
                    g[i][j] += f[i - 1][j - 1];
                    g[i][j] %= MOD;

                    // Extend an existing segment
                    g[i][j] += g[i - 1][j - 1];
                    g[i][j] %= MOD;
                }
            }
        }

        return (f[n][k] + g[n][k]) % MOD;
    }
}