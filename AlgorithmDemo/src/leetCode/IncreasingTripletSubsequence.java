package leetCode;

public class IncreasingTripletSubsequence {
	public boolean increasingTriplet(int[] nums) {
        boolean flag = false;
        int minOne = nums[0];
        int minTwo = Integer.MAX_VALUE;
        for (int index = 1; index < nums.length; index++) {
        	if (nums[index] > minTwo) {
        		flag = true; break;
        	} else if (nums[index] > minOne){
        		minTwo = nums[index];
        	} else {
        		minOne = nums[index];
        	}
        }
        return flag;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
