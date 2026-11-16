class Solution {
    public String digitSum(String s, int k) {


          while (s.length() > k) {

            StringBuilder result = new StringBuilder();

            // Divide string into groups of size k
            for (int i = 0; i < s.length(); i += k) {

                int sum = 0;

                // Calculate sum of digits in current group
                for (int j = i; j < Math.min(i + k, s.length()); j++) {

                    int digit = s.charAt(j) - '0';
                    sum += digit;
                }

                // Append group sum to result
                result.append(sum);
            }

            // Update string for the next round
            s = result.toString();
        }

        return s; 
    }
}