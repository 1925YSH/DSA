
class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        // Initial position: (0,0)
        // Robot can move right or down.

        right[0][1] = 1;
        down[1][0] = 1;

        // Traverse the grid
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Destination
                if (i == m - 1 && j == n - 1) {
                    return (int) (
                        (right[i][j] + down[i][j]) % MOD
                    );
                }

                // Case 1: Mirror
                if (grid[i][j] == 1) {

                    // Entered mirror while moving right.
                    // Reflection changes direction to down.

                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;
                    }

                    // Entered mirror while moving down.
                    // Reflection changes direction to right.

                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                    }

                } 
                // Case 2: Empty cell
                else {

                    long ways =
                        (right[i][j] + down[i][j]) % MOD;

                    // Move right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;
                    }

                    // Move down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}
