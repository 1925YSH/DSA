import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int mask = 0;

        for (int bit = 30; bit >= 0; bit--) {
            mask |= (1 << bit);

            Set<Integer> prefixes = new HashSet<>();

            for (int num : nums) {
                prefixes.add(num & mask);
            }

            int candidate = max | (1 << bit);

            for (int prefix : prefixes) {
                if (prefixes.contains(prefix ^ candidate)) {
                    max = candidate;
                    break;
                }
            }
        }

        return max;
    }
}