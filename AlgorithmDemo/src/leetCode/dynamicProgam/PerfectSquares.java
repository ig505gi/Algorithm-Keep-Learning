package leetCode.dynamicProgam;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 * @author GaoYuan
 *看了讨论区
 *dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
 */
public class PerfectSquares {
	/*
	 * Runtime: 20 ms, faster than 80.41% of Java online submissions for Perfect Squares.
	Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Perfect Squares.
	 */
	public int numSquares(int n) {
        int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
		for (int j = 1; j < n + 1; j++) {
			for(int i = 1; j - i * i >= 0; i++) {
				dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
			}
		}
		return dp[n];
    }
	public static void main(String[] args) {
		PerfectSquares solution = new PerfectSquares();
		System.out.println(solution.numSquares(120));

	}

}
