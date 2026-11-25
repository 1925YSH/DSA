
import java.util.*;

class Solution {

    static final long BASE = 100001L;
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;

    static class HashKey {
        long h1, h2;

        HashKey(long h1, long h2) {
            this.h1 = h1;
            this.h2 = h2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof HashKey)) return false;

            HashKey other = (HashKey) obj;

            return h1 == other.h1 && h2 == other.h2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(h1, h2);
        }
    }

    long[] pow1, pow2;

    // Precompute powers of BASE
    public void buildPowers(int n) {

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {

            pow1[i] = (pow1[i - 1] * BASE) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE) % MOD2;
        }
    }

    // Check whether a unique subarray of length len exists
    public boolean check(int[] nums, int len) {

        int n = nums.length;

        HashMap<HashKey, Integer> map = new HashMap<>();

        long h1 = 0;
        long h2 = 0;

        // Calculate hash of the first window
        for (int i = 0; i < len; i++) {

            h1 = (h1 * BASE + nums[i]) % MOD1;
            h2 = (h2 * BASE + nums[i]) % MOD2;
        }

        map.put(new HashKey(h1, h2), 1);

        // Calculate hashes of remaining windows
        for (int i = 1; i <= n - len; i++) {

            long outgoing = nums[i - 1];
            long incoming = nums[i + len - 1];

            h1 = (h1 - outgoing * pow1[len - 1] % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE + incoming) % MOD1;

            h2 = (h2 - outgoing * pow2[len - 1] % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE + incoming) % MOD2;

            HashKey key = new HashKey(h1, h2);

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // Check whether any hash occurs exactly once
        for (int frequency : map.values()) {

            if (frequency == 1) {
                return true;
            }
        }

        return false;
    }

    // Main function
    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        buildPowers(n);

        int low = 1;
        int high = n;

        int ans = n;

        // Binary Search on subarray length
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(nums, mid)) {

                ans = mid;
                high = mid - 1;

            } else {

                low = mid + 1;
            }
        }

        return ans;
    }
}
