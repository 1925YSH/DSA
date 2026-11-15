class Solution {
    public int numOfSubarrays(int[] arr) {
     int MOD = 1_000_000_007;

        int evenCount = 1;
        int oddCount = 0;

        int answer = 0;
        int prefixParity = 0;

        for (int i = 0; i < arr.length; i++) {

            prefixParity = (prefixParity + arr[i]) % 2;

            if (prefixParity == 1) {

                answer = (answer + evenCount) % MOD;
                oddCount++;

            } else {

                answer = (answer + oddCount) % MOD;
                evenCount++;
            }
        }

        return answer;
    }
}