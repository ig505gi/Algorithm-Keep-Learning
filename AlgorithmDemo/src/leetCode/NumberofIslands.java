package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 *  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 *  You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

 * 四连通问题
 * XXXXX boolean[][] checked来保存是否遍历过 XXXX
 * 不用这个数组，可以直接把原数组修改，把1改成0
 * 如果没有，则进行BFS，并连通的1都标记为true，
 * 并且计数+1
 * 主循环遍历grid
 * @author GaoYuan
 *
 */
public class NumberofIslands {
	/**
	 * 用了两个Queue保存 竟然比一个保存数组更慢。。
	 * Runtime: 4 ms, faster than 40.14% of Java online submissions for Number of Islands.
Memory Usage: 41.6 MB, less than 5.02% of Java online submissions for Number of Islands.
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0; 
		int num = 0;
		Queue<Integer> rowQueue = new LinkedList<Integer>();
		Queue<Integer> colQueue = new LinkedList<Integer>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == '1') {
					rowQueue.add(row);
					colQueue.add(col);
					grid[row][col] = '0';
					bfs(rowQueue, colQueue, grid);
					num++;
				}
			}
		}
		return num;
    }
	
	private void bfs(Queue<Integer> rowQueue,Queue<Integer> colQueue, char[][] grid) {
		while (!rowQueue.isEmpty()) {
			int row = rowQueue.poll();
			int col = colQueue.poll();
			if (row - 1 >= 0 && grid[row - 1][col] == '1') {
				rowQueue.add(row - 1); colQueue.add(col);
				grid[row - 1][col] = '0';
			}
			if (row + 1 < grid.length && grid[row + 1][col] == '1') {
				rowQueue.add(row + 1); colQueue.add(col);
				grid[row + 1][col] = '0';
			}
			if (col - 1 >= 0 && grid[row][col - 1] == '1') {
				rowQueue.add(row); colQueue.add(col - 1);
				grid[row][col - 1] = '0';
			}
			if (col + 1 < grid[0].length && grid[row][col + 1] == '1') {
				rowQueue.add(row); colQueue.add(col + 1);
				grid[row][col + 1] = '0';
			}
		}
	}
	
	/**
	 * 上面是迭代实现bfs，下面是递归实现bfs，代码更简单 
	 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Number of Islands.
Memory Usage: 42.1 MB, less than 5.02% of Java online submissions for Number of Islands.
	 * @param grid
	 * @return
	 */
	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0; 
		int num = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == '1') {
					bfs2(grid, row, col);
					num++;
				}
			}
		}
		return num;
    }
	
	private void bfs2(char[][] grid, int row, int col) {
		// 如果在这里验证row和col的合法性，这样代码更简单
		grid[row][col] = '0';
		if (row - 1 >= 0 && grid[row - 1][col] == '1') 
			bfs2(grid, row - 1, col);
		if (row + 1 < grid.length && grid[row + 1][col] == '1') 
			bfs2(grid, row + 1, col);
		if (col - 1 >= 0 && grid[row][col - 1] == '1') 
			bfs2(grid, row, col - 1);
		if (col + 1 < grid[0].length && grid[row][col + 1] == '1') 
			bfs2(grid, row, col + 1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
