package April14th.bytedance.first;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * 暴力求解。。只过了12.5%
 * 觉得应该用dfs来写，但是思路是每遇到一个2就dfs，这样效率感觉很低，就没有写
 * @author GaoYuan
 * 
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);  
		ArrayList<String> lines = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}
		scanner.close();
		String[] rowArray = lines.get(0).split(" ");
		int col = rowArray.length;
		int row = lines.size();
		int[][] board = new int[row][col];
		int numOfOne = 0;
		for (int i = 0; i < row; i++) {
			rowArray = lines.get(i).split(" ");
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(rowArray[j]);
				if (board[i][j] == 1) {
					numOfOne++;
				}
			}
		}
		int[][] newboard = board.clone();
		int time = 0;
		while (numOfOne > 0) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (board[i][j] == 2) {
						if (i - 1 >= 0 && board[i - 1][j] == 1) {
							// 如果new中是1 才能保证这是第一次把1 变成2
							if (newboard[i - 1][j] == 1) numOfOne--;
							newboard[i - 1][j] = 2;
						}
						if (j - 1 >= 0 && board[i][j - 1] == 1) {
							// 如果new中是1 才能保证这是第一次把1 变成2
							if (newboard[i][j - 1] == 1) numOfOne--;
							newboard[i][j - 1] = 2;
						}
						if (i + 1 < row && board[i + 1][j] == 1) {
							// 如果new中是1 才能保证这是第一次把1 变成2
							if (newboard[i + 1][j] == 1) numOfOne--;
							newboard[i + 1][j] = 2;
						}
						if (j + 1 < col && board[i][j + 1] == 1) {
							// 如果new中是1 才能保证这是第一次把1 变成2
							if (newboard[i][j + 1] == 1) numOfOne--;
							newboard[i][j + 1] = 2;
						}
					}
				}
			}
			board = newboard.clone();
			time++;
		}		
		System.out.println(time);
	}

}
