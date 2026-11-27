class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
                int n = items.length;

        // Step 1: Calculate free items for every item type
        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i != j &&
                    items[j][0] % items[i][0] == 0) {

                    free[i]++;
                }
            }
        }

        // Step 2: Sort item indices by price (descending)
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(items[b][1], items[a][1])
        );

        // Step 3: DP
        int NEG = -1_000_000;
        int[] dp = new int[budget + 1];

        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int answer = 0;
        int k = 0;

        // Process items grouped by price
        while (k < n) {

            int price = items[order[k]][1];

            // all: best value using processed item types
            int[] all = dp.clone();

            // used: best value with at least one
            // item of the current price
            int[] used = new int[budget + 1];

            Arrays.fill(used, NEG);

            // Process every item having this price
            while (k < n &&
                   items[order[k]][1] == price) {

                int idx = order[k];

                int cost = items[idx][1];

                // One purchased copy + all free copies
                int value = 1 + free[idx];

                for (int b = budget; b >= cost; b--) {

                    if (all[b - cost] != NEG) {

                        used[b] = Math.max(
                            used[b],
                            all[b - cost] + value
                        );

                        all[b] = Math.max(
                            all[b],
                            all[b - cost] + value
                        );
                    }

                    if (used[b - cost] != NEG) {

                        used[b] = Math.max(
                            used[b],
                            used[b - cost] + value
                        );
                    }
                }

                k++;
            }

            // Step 4: Spend remaining budget on
            // additional copies of cheapest selected item
            for (int b = 0; b <= budget; b++) {

                if (used[b] != NEG) {

                    int extra = (budget - b) / price;

                    answer = Math.max(
                        answer,
                        used[b] + extra
                    );
                }
            }

            // Carry forward all selected combinations
            dp = all;
        }

        return answer;

    }
}