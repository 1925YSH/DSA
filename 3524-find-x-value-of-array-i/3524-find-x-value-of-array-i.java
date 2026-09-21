class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at previous
        // position whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Subarray containing only current number
            newDp[num % k]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                int newRemainder = (int)((r * (long)num) % k);

                newDp[newRemainder] += dp[r];
            }

            // Add current subarrays to final answer
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}