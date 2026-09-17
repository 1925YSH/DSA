class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        // best[i] = shortest valid subarray
        // ending at or before index i
        int[] best = new int[n];

        int INF = n + 1;

        // Initially no valid subarray
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if (sum == target) {

                int length = right - left + 1;

                // Check if there is a valid subarray
                // completely before this one
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        length + best[left - 1]
                    );
                }

                // Store the shortest subarray seen so far
                if (right == 0) {
                    best[right] = length;
                } else {
                    best[right] = Math.min(best[right - 1], length);
                }

            } else {

                // No new valid subarray
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }

        return answer == INF ? -1 : answer;
    }
}