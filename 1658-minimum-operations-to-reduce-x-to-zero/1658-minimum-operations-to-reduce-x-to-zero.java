class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        // Step 1: Find total sum
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        // We need to keep a subarray of this sum
        int target = total - x;

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        // Step 2: Sliding window
        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Shrink window if sum becomes too large
            while (left <= right && sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with target sum
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLen == -1) {
            return -1;
        }

        // Minimum removals
        return n - maxLen;  
    }
}