class Solution {

    public int[] findEvenNumbers(int[] digits) {

        ArrayList<Integer> list = new ArrayList<>();
        boolean[] seen = new boolean[1000];
    for (int i = 0; i < digits.length; i++) {
      for (int j = 0; j < digits.length; j++) {
        for (int k = 0; k < digits.length; k++) {

            if (i == j || j == k || i == k) {
                continue;
            }

            if (digits[i] == 0) continue;

            if (digits[k] % 2 != 0) continue;

            int num = digits[i] * 100
                    + digits[j] * 10
                    + digits[k];

          //  System.out.println(num);
          if (!seen[num]) {
    seen[num] = true;
    list.add(num);
}      
        }
    }
}
    Collections.sort(list);
    return list.stream().mapToInt(Integer::intValue).toArray() ;
    }
}