
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If this ')' is not followed by another ')',
                // insert a ')' to complete the pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    ans++;
                }

                // Match the closing pair with an opening '('.
                if (open > 0) {
                    open--;
                } else {
                    // No opening '(' exists; insert one.
                    ans++;
                }
            }
        }

        // Each remaining '(' needs two closing ')'.
        ans += open * 2;

        return ans;
    }
}
