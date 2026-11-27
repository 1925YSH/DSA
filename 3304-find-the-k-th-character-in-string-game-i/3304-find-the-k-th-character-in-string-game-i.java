
class Solution {

    public static char addChar(String str, int k) {

        StringBuilder word = new StringBuilder();
        word.append(str);

        while (word.length() < k) {

            int n = word.length();

            for (int i = 0; i < n; i++) {

                char ch = word.charAt(i);
                char next = (char)(ch + 1);

                word.append(next);
            }
        }

        return word.charAt(k - 1);
    }

    public char kthCharacter(int k) {

        String s = "a";

        if (s.length() >= k) {
            return s.charAt(k - 1);
        }

        return addChar(s, k);
    }
}
