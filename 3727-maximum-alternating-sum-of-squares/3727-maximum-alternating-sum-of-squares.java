
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        // Step 1: Calculate the square of each element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort the squared values
        Arrays.sort(squares);

        // Step 3: Subtract the smaller half, add the larger half
        long sum = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                sum -= squares[i];
            } else {
                sum += squares[i];
            }
        }

        return sum;
    }
}
