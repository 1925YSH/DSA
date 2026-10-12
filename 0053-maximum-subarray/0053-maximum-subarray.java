class Solution {
    public int maxSubArray(int[] nums) {
        
        int currentSum =nums[0];
        int maxSum = nums[0];
        int n = nums.length;
        for(int i=1;i<n;i++){
         currentSum = Math.max(currentSum+nums[i],nums[i]);
         maxSum=Math.max(currentSum,maxSum);
            
        }

        return maxSum;
    }
}