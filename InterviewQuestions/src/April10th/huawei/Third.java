package April10th.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 只运行成功18%，后面应该是时间超了。。
 * 又想到一个稍微优化的办法， 但是想复杂了 ，又是next又是last的
 * 其实不用last，每次都是加就好了，不用每次更新周围的last
 * 但我觉得这样时间复杂度还是很高
 * @author GaoYuan
 *
 */

public class Third {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] map = new int[n][m];
        for (int row = 0; row < n; row++) {
        	for (int col = 0; col < m; col++) {
        		map[row][col] = in.nextInt();
        	}
        }
        int xA = in.nextInt();
        int yA = in.nextInt();
        int xB = in.nextInt();
        int yB = in.nextInt();
        in.close();
        long[][] dp = new long[n][m];
        dp[xA][yA] = 1;
        bfs(map, dp, xA, yA);
        System.out.println(dp[xB][yB]);
    }
    
    private static void bfs(int[][] map, long[][] dp, int x, int y) {
    	for (int[] xy: Third.next(map, x, y)) {
			int xn = xy[0];
			int yn = xy[1];
			long res = 0;
			for (int[] xyl: Third.last(map, xn, yn)) {
				res += dp[xyl[0]][xyl[1]];
			}
			dp[xn][yn] =  res;
		}
    	for (int[] xy: Third.next(map, x, y)) {
    		int xn = xy[0];
			int yn = xy[1];
    		bfs(map, dp, xn, yn);
    	}
		
	}

	private static List<int[]> last(int[][] map, int x, int y) {
		ArrayList<int[]> lastList = new ArrayList<int[]>(); 
    	int[] xy = new int[2];
    	xy[0] = x;
    	xy[1] = y;
    	if (x - 1 >= 0 && map[x - 1][y] < map[x][y]) {
    		xy[0] = x - 1;
    		lastList.add(Arrays.copyOf(xy, 2));
    	}
    	if (y - 1 >= 0 && map[x][y - 1] < map[x][y]) {
    		xy[1] = y - 1;
    		lastList.add(Arrays.copyOf(xy, 2));
    	}
    	if (x + 1 < map.length && map[x + 1][y] < map[x][y]) {
    		xy[0] = x + 1;
    		lastList.add(Arrays.copyOf(xy, 2));
    	}
    	if (y + 1 < map[0].length && map[x][y + 1] < map[x][y]) {
    		xy[1] = y + 1;
    		lastList.add(Arrays.copyOf(xy, 2));
    	}
    	return lastList;
	}

	private static List<int[]> next(int[][] map, int x, int y) {
    	ArrayList<int[]> nextList = new ArrayList<int[]>(); 
    	int[] xy = new int[2];
    	xy[0] = x;
    	xy[1] = y;
    	if (x - 1 >= 0 && map[x - 1][y] > map[x][y]) {
    		xy[0] = x - 1;
    		nextList.add(Arrays.copyOf(xy, 2));
    	}
    	if (y - 1 >= 0 && map[x][y - 1] > map[x][y]) {
    		xy[1] = y - 1;
    		nextList.add(Arrays.copyOf(xy, 2));
    	}
    	if (x + 1 < map.length && map[x + 1][y] > map[x][y]) {
    		xy[0] = x + 1;
    		nextList.add(Arrays.copyOf(xy, 2));
    	}
    	if (y + 1 < map[0].length && map[x][y + 1] > map[x][y]) {
    		xy[1] = y + 1;
    		nextList.add(Arrays.copyOf(xy, 2));
    	}
    	return nextList;
    }
}
