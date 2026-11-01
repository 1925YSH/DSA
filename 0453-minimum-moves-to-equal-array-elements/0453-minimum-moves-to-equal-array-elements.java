class Solution {
    public int minMoves(int[] nums) {
           int min = Integer.MAX_VALUE;
        int sum = 0;

        // Find minimum and calculate sum
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        // Minimum moves required
        return sum - (nums.length * min);
    }
}