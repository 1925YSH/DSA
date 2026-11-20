class Solution {
    public int maxSelectedElements(int[] nums) {
         Arrays.sort(nums);

        int max = nums[nums.length - 1];

        int[] dp = new int[max + 2];

        int ans = 0;

        for (int num : nums) {

            // Choice 1: Increase num to num + 1
            int increase = dp[num] + 1;

            // Choice 2: Keep num as it is
            int keep = dp[num - 1] + 1;

            // Update both DP states
            dp[num + 1] = Math.max(dp[num + 1], increase);
            dp[num] = Math.max(dp[num], keep);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;   
    }
}