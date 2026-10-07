import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            } 
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        Set<String> result = new HashSet<>();

        dfs(s, 0, 0, leftRemove, rightRemove, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void dfs(
        String s,
        int index,
        int balance,
        int leftRemove,
        int rightRemove,
        StringBuilder current,
        Set<String> result
    ) {

        // Invalid prefix
        if (balance < 0) {
            return;
        }

        // End of string
        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // Case 1: Remove current parenthesis
        if (ch == '(' && leftRemove > 0) {

            dfs(
                s,
                index + 1,
                balance,
                leftRemove - 1,
                rightRemove,
                current,
                result
            );
        }

        if (ch == ')' && rightRemove > 0) {

            dfs(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove - 1,
                current,
                result
            );
        }

        // Case 2: Keep current character
        current.append(ch);

        if (ch == '(') {

            dfs(
                s,
                index + 1,
                balance + 1,
                leftRemove,
                rightRemove,
                current,
                result
            );

        } else if (ch == ')') {

            dfs(
                s,
                index + 1,
                balance - 1,
                leftRemove,
                rightRemove,
                current,
                result
            );

        } else {

            // Letter
            dfs(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove,
                current,
                result
            );
        }

        // Backtrack
        current.deleteCharAt(current.length() - 1);
    }
}