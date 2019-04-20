package leetCode;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. 
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

 * @author GaoYuan
 *
 */
public class GameOfLife {
	/**
	 * 之前慕课网的题目一样
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
Memory Usage: 37.1 MB, less than 91.21% of Java online submissions for Game of Life.
	 * @param board
	 */
	public void gameOfLife(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
        	for (int col = 0; col < board[0].length; col++) {
        		newBoard[row][col] = change(board, row, col);
        	}
        }
        // 数组不应该是对象的管理者吗为什么board = newboard还是指向原来board
        for (int row = 0; row < board.length; row++) {
        	for (int col = 0; col < board[0].length; col++) {
        		board[row][col] = newBoard[row][col];
        	}
        }
    }
	private int change(int[][] board, int row, int col) {
		int num = numOfneighbor(board, row, col);
		if (board[row][col] == 1) {
			if (num == 2 || num == 3) { return 1; } 
			else { return 0; }
		} else {
			if (num == 3) { return 1; }
			else { return 0; }
		}
	}
	private int numOfneighbor(int[][] board, int row, int col) {
		int num = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (!(i == row && j == col) && i >= 0 && j >= 0 
					&& i < board.length && j < board[0].length) {
					if (board[i][j] == 1) num++; 
				}
			}
		}
		return num;
	}
	public static void main(String[] args) {
		GameOfLife gl = new GameOfLife();
		int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
		gl.gameOfLife(board);
		System.out.println(board);
	}

}
