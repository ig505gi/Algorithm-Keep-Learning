package leetCode;

import java.util.HashSet;
import java.util.Set;

/*
 * 验证数独有效性,
 * 用空间换时间：
 * boolean[][] rowNum
 * boolean[][] colNum
 * boolean[][] subNum
 * 用三个 9X9的boolean数组保存该处是否有1, 2...9
 * row和col好说，直接对应一个index就是行列信息
 * sub的话就难搞点
 * 
 * Runtime: 2 ms, faster than 97.71% of Java online submissions for Valid Sudoku.
Memory Usage: 44.5 MB, less than 70.65% of Java online submissions for Valid Sudoku.
 */
public class ValidSudoku {
	
	public boolean isValidSudoku(char[][] board) {
		boolean[][] rowNum = new boolean[9][9];
		boolean[][] colNum = new boolean[9][9];
		boolean[][] subNum = new boolean[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				char c = board[row][col];
				if (c == '.') continue;
				// c一定为数字
				int num = c - '0';
				int sub = row / 3 * 3 + col / 3;
				if (rowNum[row][num - 1] || colNum[col][num - 1] || subNum[sub][num - 1]) {
					// 有一个存在 就返回 false
					return false;
				} else {
					// 都不存在，全都变为true
					rowNum[row][num - 1] = true;
					colNum[col][num - 1] = true;
					subNum[sub][num - 1] = true;
				}
			}
		}
		return true;
    }
	/**
	 * 来自评论区，思路是一样的
	 * 但是保存方法这更优雅，并且判断是否存在是用Set的特性，如果add成功返回true
	 * 代码很优雅，速度应该没有直接用数组快
	 * Runtime: 5 ms, faster than 80.72% of Java online submissions for Valid Sudoku.
Memory Usage: 45.3 MB, less than 68.66% of Java online submissions for Valid Sudoku.
	 * @param board
	 * @return
	 */
	public boolean isValidSudoku2(char[][] board) {
	    Set seen = new HashSet();
	    for (int i=0; i<9; ++i) {
	        for (int j=0; j<9; ++j) {
	            char number = board[i][j];
	            if (number != '.')
	                if (!seen.add(number + " in row " + i) ||
	                    !seen.add(number + " in column " + j) ||
	                    !seen.add(number + " in block " + i/3 + "-" + j/3))
	                    return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
