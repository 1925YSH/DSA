
class Solution {
    public int trap(int[] height) {

        int n = height.length;

        int lp=0;
        int rp=n-1;

        int leftMax=0;
        int rightMax=0;

        
        int water =0;

        while(lp<rp){

            leftMax =Math.max(leftMax,height[lp]);
            rightMax= Math.max(rightMax,height[rp]);

            if(leftMax < rightMax){

                water+=(leftMax-height[lp]);
                lp++;
            }else{
                water+= (rightMax-height[rp]);
                rp--;
            }
        }

        return water;
    }
}
