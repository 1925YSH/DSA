class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
     if (n == 0) {
            return;
        }
        k = k % n;
        int temp []= new int [k];

        //first stored value in temp array
        for(int i=0;i<k;i++){
            temp[i]=nums[n-k+i];
                }

//second shifting remaining to right

        for(int i =n-k-1;i>=0;i--){
            nums[i+k] = nums[i];
        }

 //Saving temp array elements to begining       

        for(int i =0;i<k;i++){
            nums[i]=temp[i];
        }

    }
}