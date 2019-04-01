package leetCode.dynamicProgam;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

    Each of the array element will not exceed 100.
    The array size will not exceed 200.

 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].

 * @author GaoYuan
 * 先加一遍，如果是奇数，一定不行，如果是偶数有可能
 */
public class PartitionEqualSubsetSum {
	// 很慢。。。但是巩固了递归的写法。。
	public boolean canPartition(int[] nums) {
		int sum = 0;
		// O(n)
		for (int num: nums) {
			sum += num;
		}
		if (sum%2 != 0) return false;
		// O(nlgn)
		Arrays.sort(nums);
		if (nums[nums.length - 1] > sum / 2) return false;
		// 想到一个方法 dfs, 每个元素可以取加号或减号
		// 觉得会超时。。。 果然超时了！
		return dfs(nums, 0, 0, 0, sum);
    }
	private boolean dfs(int[] nums, int index, int sum1, int sum2, int sum) {
		if (index == nums.length) {
			if (sum1 == sum2) { return true; } 
			else { return false; }
		}
		if (sum1 > sum / 2 || sum2 > sum / 2) return false;
		return dfs(nums, index + 1, sum1 + nums[index], sum2, sum) || dfs(nums, index + 1, sum1, sum2 + nums[index], sum);
	}
	/**
	 * Runtime: 10 ms, faster than 83.06% of Java online submissions for Partition Equal Subset Sum.
	   Memory Usage: 37.8 MB, less than 57.54% of Java online submissions for Partition Equal Subset Sum.
	 * @param nums
	 * @return
	 * 
	 * 动态规划的常用解法，把之前的所有结果都记录下来，用很大的空间，如果整数不大，可以直接创建一个所有结果集的boolean数组
	 */
	public boolean canPartition2(int[] nums) {
		int sum = 0;
		// O(n)
		for (int num: nums) {
			sum += num;
		}
		if (sum % 2 != 0) return false;
		int tar = sum / 2;
		/**
		 * 长度是tar + 1，本来有个答案是用的二维数组，
		 * 但是 发现 i+1行一定保留i行dp直接保留即可，更新 大于nums[i+1]的部分即可
		 * 但是，不能从 nums[i+1]开始，要倒着从tar开始，应为dp的index大的会利用小的，
		 * 如果小的已经在i+1这个循环更新，在更新大的时候，直接就用上了，是不对的
		 */
		boolean[] dp = new boolean[tar + 1];
		dp[0] = true;
		for (int i = 1; i < nums.length; i++) {
			for (int j = tar; j >= nums[i]; j--) {
				dp[j] = dp[j] || dp[j - nums[i]];
			}
		}
		return dp[tar];
	}
	
	public static void main(String[] args) {
		PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();
		int[] nums = {1, 5, 5, 11};
		System.out.println(solution.canPartition2(nums));

	}

}
