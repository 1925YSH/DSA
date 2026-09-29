class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Total number of cells must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // +1 to safely store all possible balances
        boolean[][][] dp = new boolean[m][n][m + n + 1];

        // First cell
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change;

                if (grid[i][j] == '(') {
                    change = 1;
                } else {
                    change = -1;
                }

                for (int balance = 0; balance <= m + n; balance++) {

                    int previousBalance = balance - change;

                    if (previousBalance < 0 ||
                        previousBalance > m + n) {
                        continue;
                    }

                    // Come from top
                    if (i > 0 && dp[i - 1][j][previousBalance]) {
                        dp[i][j][balance] = true;
                    }

                    // Come from left
                    if (j > 0 && dp[i][j - 1][previousBalance]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        // Final balance must be 0
        return dp[m - 1][n - 1][0];
    }
}