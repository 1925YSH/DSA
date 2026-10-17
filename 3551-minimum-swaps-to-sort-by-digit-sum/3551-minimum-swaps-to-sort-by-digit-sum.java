
import java.util.Arrays;

class Solution {

    static class Item {
        int value;
        int originalIndex;
        int digitSum;

        Item(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
            this.digitSum = getDigitSum(value);
        }
    }

    private static int getDigitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];

        // Store each value and its original index.
        for (int i = 0; i < n; i++) {
            items[i] = new Item(nums[i], i);
        }

        // Sort by digit sum, then by actual value.
        Arrays.sort(items, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }

            return Integer.compare(a.value, b.value);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        // Count permutation cycles.
        for (int i = 0; i < n; i++) {
            if (visited[i] || items[i].originalIndex == i) {
                continue;
            }

            int cycleLength = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = items[j].originalIndex;
                cycleLength++;
            }

            swaps += cycleLength - 1;
        }

        return swaps;
    }
}
