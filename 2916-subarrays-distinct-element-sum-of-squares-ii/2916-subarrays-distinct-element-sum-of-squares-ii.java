
import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] squareSum;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int previous = last[nums[i]];

            // Increase distinct counts for affected subarrays.
            update(1, 0, n - 1, previous + 1, i);

            last[nums[i]] = i;

            // All subarrays ending at i are represented
            // by the prefix of starting indices [0, i].
            answer = (answer + query(1, 0, n - 1, 0, i))
                    % MOD;
        }

        return (int) answer;
    }

    private void apply(int node, int left, int right, long delta) {
        long len = right - left + 1L;

        squareSum[node] = (
                squareSum[node]
                + 2L * delta % MOD * sum[node] % MOD
                + delta * delta % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void pull(int node) {
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] = (
                squareSum[node * 2] + squareSum[node * 2 + 1]
        ) % MOD;
    }

    private void update(
            int node, int left, int right,
            int ql, int qr) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        pull(node);
    }

    private long query(
            int node, int left, int right,
            int ql, int qr) {

        if (ql > right || qr < left) {
            return 0;
        }

        if (ql <= left && right <= qr) {
            return squareSum[node];
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        long a = query(node * 2, left, mid, ql, qr);
        long b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return (a + b) % MOD;
    }
}
