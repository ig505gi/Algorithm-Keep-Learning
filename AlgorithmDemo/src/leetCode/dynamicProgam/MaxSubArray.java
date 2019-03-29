package leetCode.dynamicProgam;
/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 * @author GaoYuan
 *
 *
 *动态规划
 *Runtime: 1 ms, faster than 99.45% of Java online submissions for Maximum Subarray.
Memory Usage: 38.7 MB, less than 89.98% of Java online submissions for Maximum Subarray.

分治：
Runtime: 1 ms, faster than 99.45% of Java online submissions for Maximum Subarray.
Memory Usage: 38.5 MB, less than 91.57% of Java online submissions for Maximum Subarray.
 */
public class MaxSubArray {
	// dp
	public int maxSubArray(int[] nums) {
		int[] maxSubTo = new int[nums.length];
		maxSubTo[0] = nums[0];
		int max = maxSubTo[0];
		for (int i = 1; i < nums.length; i++) {
			maxSubTo[i] = Math.max(maxSubTo[i - 1], 0) + nums[i];
			max = Math.max(max, maxSubTo[i]);
		}
		return max;
    }
	
	// divide and conquer approach
	// 肯定没有动态规划快，重复计算了很多次
	// 吃惊，没有比动态规划慢，内存还少！
	public int maxSubArrayDivide(int[] nums) {
		
		return max(nums, 0, nums.length - 1);
	}
	
	private int max(int[] nums, int l, int r) {
		if (l > r) {
			return Integer.MIN_VALUE;
		}
		int m = l + (r - l) / 2;
		int maxl = max(nums, l, m - 1);
		int maxr = max(nums, m + 1, r);
		// 以nums[m-1]为尾最大的和，和以nums[m+1]为首，最大的和
		int ml = 0, mr = 0;
		// 得到以nums[m-1]为尾的最大的和
		for (int i = m - 1, sum = 0; i >= l; i--) {
			sum += nums[i];
			ml = Math.max(sum, ml);
		}
		// 得到以nums[m+1]为首，最大的和
		for (int i = m + 1, sum = 0; i <= r; i++) {
			sum += nums[i];
			mr = Math.max(sum, mr);
		}
		return Math.max(Math.max(maxr, maxl), ml + mr + nums[m]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
