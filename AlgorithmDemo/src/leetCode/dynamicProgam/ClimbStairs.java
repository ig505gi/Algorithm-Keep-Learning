package leetCode.dynamicProgam;
/**
 * You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


 * @author GaoYuan
 *
 *
 *f(1)=1;f(2)=2;
 *f3有可能从f2 + 1层得到，也有可能是f1+1层得到 所有 f3 = f1 + f2
 *f4是 f3+f2
 */
public class ClimbStairs {
	/**
	 * 时间100%，空间100%
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;
		// 这里都大于等于3
		int[] nums = new int[n];
		nums[0] = 1;
		nums[1] = 2;
		for (int i = 3; i < n; i++) {
			nums[i] = nums[i-1] + nums[i-2];
		}
		return nums[n-1];
	}
	/**
	 * 递归解法  时间超出限制
	 * @param args
	 */
	public int climbStairsRecursion(int n) {
		return steps(n);
	}
	
	private int steps(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;
		return steps(n-1) + steps(n-2);
	}
	public static void main(String[] args) {
		ClimbStairs c = new ClimbStairs();
		System.out.println(c.climbStairsRecursion(37));
	}

}
