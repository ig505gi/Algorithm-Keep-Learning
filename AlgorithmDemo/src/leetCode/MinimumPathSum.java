package leetCode;

import java.util.Map;

/**
 * @author Gao Yuan
 * @date 2019-07-26 - 10:48
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * Runtime: 2 ms, faster than 89.96% of Java online submissions for Minimum Path Sum.
 * Memory Usage: 42.8 MB, less than 56.95% of Java online submissions for Minimum Path Sum.
 */
public class MinimumPathSum {
    /*
       应该是 dp，创建一个 dp[m][n] 保存的是从左上角到每个点的最小值，最后填充到右下角
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 && col == 0) {continue;}
                dp[row][col] = Math.min(row==0 ? Integer.MAX_VALUE :dp[row - 1][col], col==0? Integer.MAX_VALUE : dp[row][col - 1])
                        + grid[row][col];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinimumPathSum solution = new MinimumPathSum();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid));
    }
}
