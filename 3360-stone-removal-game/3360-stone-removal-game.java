class Solution {
    public boolean canAliceWin(int n) {
        if(n<10) return false;

        int take =10;
        int step =0;

        while(n>=take){

            n-=take;
            take-=1;
            step++;
        }

        if(step %2 != 0){
            return true;
        }

        return false;
    }
}