class Solution {
    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            int sumDigit = 0;

            while (num > 0) {
                sumDigit += num % 10;
                num /= 10;
            }

            if (sumDigit == i) {
                return i;
            }
        }

        return -1;
    }
}