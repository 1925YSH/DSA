class Solution {
    public long countCommas(long n) {
        long ans = 0;

        // 1 comma: 1,000 onwards
        if (n >= 1000) {
            ans += n - 1000 + 1;
        }

        // 2 commas: 1,000,000 onwards
        if (n >= 1000000) {
            ans += n - 1000000 + 1;
        }

        // 3 commas: 1,000,000,000 onwards
        if (n >= 1000000000) {
            ans += n - 1000000000 + 1;
        }

        // 4 commas: 1,000,000,000,000 onwards
        if (n >= 1000000000000L) {
            ans += n - 1000000000000L + 1;
        }

        // 5 commas: 1,000,000,000,000,000 onwards
        if (n >= 1000000000000000L) {
            ans += n - 1000000000000000L + 1;
        }

        return ans;
    }
}