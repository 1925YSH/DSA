class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
           
        for (int i = 1; i < prices.length; i++) {

            // Calculate profit if we sell today
            int profit = prices[i] - minPrice;

            // Update maximum profit
            maxProfit = Math.max(maxProfit, profit);

            // Update cheapest buying price
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}