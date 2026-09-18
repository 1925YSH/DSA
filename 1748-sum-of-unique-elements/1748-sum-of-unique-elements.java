class Solution {
    public int sumOfUnique(int[] nums) {
         HashMap<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int sum = 0;

        for (int num : frequency.keySet()) {
            if (frequency.get(num) == 1) {
                sum += num;
            }
        }

        return sum;
    }
}