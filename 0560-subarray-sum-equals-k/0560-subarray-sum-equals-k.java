class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            currentSum += nums[i];

            int requiredSum = currentSum - k;

            if (map.containsKey(requiredSum)) {
                count += map.get(requiredSum);
            }

            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }

        return count;

    }
}