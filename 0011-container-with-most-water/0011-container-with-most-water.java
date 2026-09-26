class Solution {
    public int maxArea(int[] height) {
        
        int maxWater = 0;

        int lp =0;
        int rp = height.length-1;

        while(lp<rp){

            int w = rp-lp;
            int h = Math.min(height[lp],height[rp]);

            int area = w*h;

            maxWater = Math.max(area,maxWater);

            if(height[lp]<height[rp]){
                lp++;
            }else{
                rp--;
            }

            
        }

        return maxWater;
    }
}