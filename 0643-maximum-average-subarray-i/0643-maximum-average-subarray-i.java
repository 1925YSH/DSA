class Solution {
    public double findMaxAverage(int[] nums, int k) {

            int left = 0;
            int n = nums.length;

            int sum =0;
            int maxSum =Integer.MIN_VALUE;

            for(int right = 0;right<n;right++){

                sum +=nums[right];

                if(right - left +1 == k){

                    maxSum = Math.max(maxSum, sum);

                sum -= nums[left];
                left++;
                }


            }

        double maxAvg = (double)maxSum/k;
            return maxAvg;
    }
}