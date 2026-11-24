import java.math.BigInteger;

class Solution {
    public static int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

public static int lcm(int a, int b) {
    return (a / gcd(a, b)) * b;
}
    public int maxLength(int[] nums) {

            int n = nums.length;
            int maxLength = 0;
            for(int i =0;i<n;i++){

                BigInteger  product = BigInteger.ONE;
                int gcdValue = nums[i];
                int lcmValue = nums[i];

                for(int j=i;j<n;j++){

                    gcdValue = gcd(gcdValue, nums[j]);

                    lcmValue = lcm(lcmValue, nums[j]);

                    product = product.multiply(BigInteger.valueOf(nums[j]));

                    long result = (long) gcdValue * lcmValue;
                    if (product.equals(BigInteger.valueOf(result))) {
                        maxLength = Math.max(maxLength, j - i + 1);}


                }
            }

            return maxLength;
    }
}