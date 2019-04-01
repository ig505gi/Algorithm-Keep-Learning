package leetCode.dynamicProgam;
/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time.
 The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:

Input: m = 7, n = 3
Output: 28
 * @author GaoYuan
 * 分析：对于n * m 的方格向右走一步就变成了n-1 * m 的方格，而向下走一步就变成了n * m - 1的方格 
 * F(n, m) = F(n - 1, m) + F(n, m - 1)
 * F(1, m) = 1; F(n, 1) = 1;
 * 
 * 来自评论区messfish，很有想法
 *If you mark the south move as '1' and the east move as '0'. This problem shall be equal to :
Given (m+n-2) bits. you can fill in '1' for (m-1) times and '0' for (n-1) times, what is the number of different numbers?
the result is clear that the formula shall be C(m-1)(m+n-2), where m-1 is the superscript behind C and m+n-2 is the subscript behind C.
To avoid overflow, I write the program in this manner.
 */
public class UniqePaths {
	// 递归解法 超时
	public int Solution(int m, int n) {
		return trace(m, n);
	}
	private int trace(int m, int n) {
		if (m == 1) return 1;
		if (n == 1) return 1;
		return trace(m - 1, n) + trace(m, n - 1);
	}
	
	// 迭代解法，空间换取时间
	//Runtime: 1 ms, faster than 10.13% of Java online submissions for Unique Paths.
	//Memory Usage: 31.9 MB, less than 100.00% of Java online submissions for Unique Paths.
	public int Solution2(int m, int n) {
		if (m == 1 || n == 1) return 1;
		int max = Math.max(m, n);
		int[][] steps = new int[max][max];
		// 当m = n 的时候，一直都是对称的，
		for (int i = 0; i < max; i++) {
			steps[i][0] = 1;
			steps[0][i] = 1;
		}
		
		for (int i = 1; i < max; i++) {
			for (int j = i; j < max; j++) {
				steps[i][j] = steps[i][j - 1] + steps[i - 1][j];
				steps[j][i] = steps[j - 1][i] + steps[j][i - 1];
			}
			steps[i][i] = steps[i][i - 1] + steps[i - 1][i];
		}
		return steps[m - 1][n - 1];
	}
	
	// 想象为排列组合问题，就是要填m-1次right和n-1次down
	// 一个用 m + n - 2个位置，填 m - 1中情况
	// 没想到会有位数不够溢出的问题。。
	public int Solution3(int m, int n) {
		long res = 1;
		for (int i = m + n - 2; i > m - 1; i--) {
			res = res * i;
		}
		for (int i = 2; res > 1 && i < n; i ++) {
			res = res / i;
		}
		return (int) res;
	}
	/**
	 * 看来是自己数学没学到家。。
	 * 
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
	Memory Usage: 32 MB, less than 100.00% of Java online submissions for Unique Paths.
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
        long result = 1;
        for(int i=0;i<Math.min(m-1,n-1);i++)
            result = result*(m+n-2-i)/(i+1); 
        return (int)result;
    }
	
	public static void main(String[] args) {
		UniqePaths up = new UniqePaths();
		
		System.out.println(up.Solution3(13, 23));
	}

}
