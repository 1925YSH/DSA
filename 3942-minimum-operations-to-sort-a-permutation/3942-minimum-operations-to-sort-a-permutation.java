
class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length;
        int answer = Integer.MAX_VALUE;

        // Case 1: Can rotations alone sort the array?
        int k = rotationToAscending(nums);

        if (k != -1) {
            // Either rotate left k times, or use
            // two reversals to achieve the opposite rotation.
            answer = Math.min(answer, Math.min(k, n - k + 2));
        }

        // Case 2: Can rotations + one reversal sort it?
        int r = rotationToDescending(nums);

        if (r != -1) {
            // Reverse after r rotations, or reverse first
            // and then rotate in the opposite direction.
            answer = Math.min(answer,
                    Math.min(r + 1, n - r + 1));
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // Returns k if rotating left k times sorts nums.
    private int rotationToAscending(int[] nums) {
        int n = nums.length;
        int k = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                k = i;
                break;
            }
        }

        if (k == -1) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (nums[(k + i) % n] != i) {
                return -1;
            }
        }

        return k;
    }

    // Returns k if rotating left k times produces
    // the reverse-sorted array.
    private int rotationToDescending(int[] nums) {
        int n = nums.length;
        int k = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == n - 1) {
                k = i;
                break;
            }
        }

        if (k == -1) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (nums[(k + i) % n] != n - 1 - i) {
                return -1;
            }
        }

        return k;
    }
}
