package march2019.interview;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 360春招面试题，第一题比较简单，就不放了
 * 这是第二题，只能找到星星，不能找到包含k个星星的矩形
 * 没写完，也不会了
 * @author admin
 *
 */
class Star {

	public Star() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// int k = sc.nextInt();
		boolean[][] matrix = new boolean[n][m];
		for (int i=0; i<n; i++) {
			String line = sc.nextLine();
			for (int j=0; j<m; j++) {
				if (line.charAt(j) == '1') {
					matrix[i][j] = true;
				}
			}
		}
		List<Position> stars = new ArrayList<Position>();
		for (int i=1; i<n-1; i++) {
			for (int j=1; j<m-1; j++) {
				if (isStar(matrix, i, j)) {
					Position p = new Position(i, j);
					stars.add(p);
				}
			}
		}
		
		sc.close();
		
		
	}
	
	private static boolean isStar(boolean[][] a, int i, int j) {
		if (a[i][j] && a[i-1][j] && a[i+1][j] && a[i][j-1] && a[i][j+1]) return true;
		if (!a[i][j] && !a[i-1][j] && !a[i+1][j] && !a[i][j-1] && !a[i][j+1]) return true;
		return false;
	}

}

class Position {
	private int i;
	private int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
