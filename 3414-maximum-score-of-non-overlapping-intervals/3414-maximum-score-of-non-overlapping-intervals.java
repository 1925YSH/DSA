import java.util.*;

class Solution {

    class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        // Store original index
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort according to start
        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }

            return Integer.compare(a.end, b.end);
        });

        // Find next compatible interval
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, arr[i].end);
        }

        /*
         * dp[i][k]
         *
         * Best answer from interval i onwards
         * when we can select at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // Base case:
        // No intervals left -> score = 0
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(
                0,
                new ArrayList<>()
            );
        }

        // If k = 0 -> cannot select anything
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(
                0,
                new ArrayList<>()
            );
        }

        // Fill DP backwards
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // -----------------------
                // OPTION 1: SKIP
                // -----------------------
                State skip = dp[i + 1][k];

                // -----------------------
                // OPTION 2: TAKE
                // -----------------------

                State nextState = dp[next[i]][k - 1];

                List<Integer> takeIndices =
                    new ArrayList<>(nextState.indices);

                takeIndices.add(arr[i].index);

                // We need sorted original indices
                Collections.sort(takeIndices);

                State take = new State(
                    arr[i].weight + nextState.score,
                    takeIndices
                );

                // Pick the better option
                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    // Find first interval with start > currentEnd
    private int findNext(Interval[] arr, int currentEnd) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].start > currentEnd) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Choose better state
    private State better(State a, State b) {

        // Higher score wins
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score -> lexicographically smaller indices
        if (compareLexicographically(a.indices, b.indices) <= 0) {
            return a;
        }

        return b;
    }

    private int compareLexicographically(
        List<Integer> a,
        List<Integer> b
    ) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}