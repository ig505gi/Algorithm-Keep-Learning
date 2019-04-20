package leetCode;
/**
 * Runtime: 2 ms, faster than 98.19% of Java online submissions for Container With Most Water.
Memory Usage: 40.9 MB, less than 5.05% of Java online submissions for Container With Most Water.
来自评论区：
i.e. if a10 > a21, we should move pointer at a21 to a20, as we hope. Why a10 >a21? Because if a21>a10, then area of a10 and a20 must be less than area of a10 and a21. 
Because the area of a10 and a21 is at least height[a10] * (21-10) while the area of a10 and a20 is at most height[a10] * (20-10). 
So there is a contradiction of assumption a10 and a20 has the max area. So, a10 must be greater than a21, then next move a21 has to be move to a20. 
The max cases must be reached.
 * 
 * @author GaoYuan
 *
 */
public class ContainerWithMostWater {
	
	public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
	    int maxArea = 0;

	    while (left < right) {
		    maxArea = Math.max(maxArea, Math.min(height[left], height[right])
			    	* (right - left));
		    if (height[left] < height[right])
			    left++;
		    else
			    right--;
	    }
	    return maxArea;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
