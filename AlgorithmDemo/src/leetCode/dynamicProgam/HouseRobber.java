package leetCode.dynamicProgam;
/**
 * Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

 * @author GaoYuan
 *  题目很简单，就是不能投相邻的两家
 *  如果只有一家 f1 = n1;
 *  两家 f2 = max(0 + n2, f1);
 *  三家 f3 = max(f1 + n3, f2);
 *  n家 fn = max(fn-2 + fn, fn-1);
 *  
 *  Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
Memory Usage: 35.5 MB, less than 92.14% of Java online submissions for House Robber.
 */
public class HouseRobber {
	public int houseRobber(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		if (nums.length == 2) return Math.max(nums[0], nums[1]);
		int[] maxSum = new int[nums.length];
		maxSum[0] = nums[0];
		maxSum[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			maxSum[i] = Math.max(maxSum[i - 2] + nums[i], maxSum[i - 1]);
		}
		return maxSum[maxSum.length - 1];
	}
	public static void main(String[] args) {
		HouseRobber hr = new HouseRobber();
		int[] nums = {2,7,9,3,1};
		System.out.println(hr.houseRobber(nums));
	}

}
