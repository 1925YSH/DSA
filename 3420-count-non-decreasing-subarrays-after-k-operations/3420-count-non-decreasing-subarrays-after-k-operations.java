class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        long ans = 0;
        long cost = 0;

        // Each pair stores {value, count}
        Deque<int[]> deque = new ArrayDeque<>();

        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];
            int count = 1;

            // Merge smaller values into the current value
            while (!deque.isEmpty() && deque.peekLast()[0] < num) {
                int[] last = deque.pollLast();

                int value = last[0];
                int frequency = last[1];

                count += frequency;

                cost += (long) (num - value) * frequency;
            }

            deque.offerLast(new int[]{num, count});

            // Shrink the window if operations exceed k
            while (cost > k) {
                int[] first = deque.pollFirst();

                int value = first[0];
                int frequency = first[1];

                cost -= (long) value - nums[right];
                right--;

                // Keep the remaining elements in this group
                if (frequency > 1) {
                    deque.offerFirst(new int[]{value, frequency - 1});
                }
            }

            // All subarrays starting from left to right are valid
            ans += right - left + 1;
        }

        return ans;
    }
}