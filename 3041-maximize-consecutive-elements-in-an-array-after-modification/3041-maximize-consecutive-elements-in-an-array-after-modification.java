class Solution {
    public int maxSelectedElements(int[] nums) {
         Arrays.sort(nums);

        int max = nums[nums.length - 1];

        int[] dp = new int[max + 2];

        int ans = 0;

        for (int num : nums) {

          // Choice 1: Keep num as it is
            int keep = dp[num - 1] + 1;

            // Choice 2: Increase num to num + 1
            int increase = dp[num] + 1;


            // Update both DP states
            dp[num] = Math.max(dp[num], keep);

            dp[num + 1] = Math.max(dp[num + 1], increase);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;   
    }
}