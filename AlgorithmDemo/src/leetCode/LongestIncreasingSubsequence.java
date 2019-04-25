package leetCode;


/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,10       1,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:

    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

[2, 2] 输出2，因此 相等时不算的
 * @author GaoYuan
 * 
 */
public class LongestIncreasingSubsequence {
	
	public int lengthOfLIS(int[] nums) {
		// 当循环的第n个数字使，保存的是
		int[] lengthToMax = new int[nums.length + 1];
		// Arrays.fill(lengthToMax, Integer.MIN_VALUE);
		int currentMaxLen = 0;
		lengthToMax[currentMaxLen] = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > lengthToMax[currentMaxLen]) {
				lengthToMax[++currentMaxLen] = nums[i];
			} else if (nums[i] > lengthToMax[currentMaxLen]) {
				// 如果相等 就相当于没这个数
				continue;
			} else {
				// 进入这里证明比当前最长序列的最大值小，但是可能会更新序列中某一个值
				for (int j = currentMaxLen - 1; j >= 0; j--) {
					if (nums[i] > lengthToMax[j]) {
						lengthToMax[j + 1] = nums[i];
						break;
					}
				}
			}
		}
		return currentMaxLen;
    }

	public static void main(String[] args) {
		LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
		// int ans = solution.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18, 6, 7, 10});
		int ans = solution.lengthOfLIS(new int[] {2, 2, 2});
		System.out.println(ans);
	}

}
