package September12th.envision;
/**
 * @author Gao Yuan
 * @date 2019-09-12 - 19:34
 */
import java.util.Scanner;

public class First {

    public static void main(String[] args) {
        // 输入
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] size = line.split(",");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);
        int[][] boards = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = sc.nextLine();
            String[] row = line.split(",");
            for (int j = 0; j < m; j++) {
                boards[i][j] = Integer.parseInt(row[j]);
            }
        }
        // 算法主体
        int k = 1; // 表示目前的第几个光伏面板群
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (boards[i][j] == 1) { //
                    bfs(boards, i, j, ++k);
                }
            }
        }
        // boards中 从2 开始 一直到k 一共 k - 1个区域
        int[] nums = new int[k + 1]; // 从2 到 k的index表示是第几个光伏群
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (boards[i][j] > 1) {
                    nums[boards[i][j]] += 1;
                }
            }
        }
        // 找到这些光伏群的最大值
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        System.out.println(max);
    }

    private static void bfs(int[][] boards, int i, int j, int k) {
        boards[i][j] = k;
        if (i - 1 >= 0 && boards[i - 1][j] == 1) {
            bfs(boards, i - 1, j, k);
        }
        if (i + 1 < boards.length && boards[i + 1][j] == 1) {
            bfs(boards, i + 1, j, k);
        }
        if (j - 1 >= 0 && boards[i][j - 1] == 1) {
            bfs(boards, i, j - 1, k);
        }
        if (j + 1 < boards[0].length && boards[i][ j + 1] == 1) {
            bfs(boards, i, j + 1, k);
        }
    }
}
