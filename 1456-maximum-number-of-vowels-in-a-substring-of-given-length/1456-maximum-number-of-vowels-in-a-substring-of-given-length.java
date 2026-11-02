class Solution {
    public int maxVowels(String s, int k) {

        int left = 0;

        int n = s.length();

        int count = 0;
        int maxVol =0;

        for(int right =0;right<n;right++){

                if(s.charAt(right)== 'a' || s.charAt(right)== 'e'||s.charAt(right)== 'i'||s.charAt(right)== 'o'||s.charAt(right)== 'u'){
                    count++;
                }

            if(right-left+1 == k){
                maxVol=Math.max(count,maxVol);
                 // Remove the outgoing character
                if (s.charAt(left) == 'a' ||
                    s.charAt(left) == 'e' ||
                    s.charAt(left) == 'i' ||
                    s.charAt(left) == 'o' ||
                    s.charAt(left) == 'u') {

                    count--;
                }
                left++;
            }
        
        
        }   


        return maxVol; 
    }
}