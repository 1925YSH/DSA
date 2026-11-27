class Solution {

    public static boolean isPalindrome(String str){

        int start= 0;
        int end = str.length()-1;

        while(start<end){

            if(str.charAt(start)!=str.charAt(end)){
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
    public int countSubstrings(String s) {

            int count =0;
            int n = s.length();
            for(int i =0;i<n;i++){
                String result ="";
                for(int j = i;j<n;j++){
                    result+=s.charAt(j);

                    if(isPalindrome(result)){
                        count++;
                    }
                }
            }

            return count;
    }
}