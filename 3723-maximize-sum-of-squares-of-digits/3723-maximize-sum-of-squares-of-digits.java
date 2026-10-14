
class Solution {
    public String maxSumOfSquares(int num, int sum) {

        // A digit can be at most 9
        if (sum > num * 9) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                result.append('9');
                sum -= 9;
            } else {
                result.append((char) ('0' + sum));
                sum = 0;
            }
        }

        return sum == 0 ? result.toString() : "";
    }
}
