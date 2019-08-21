package August15th.com360;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-15 - 19:48
 */
public class First {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] A = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                A[row][col] = in.nextInt();
            }
        }
        int area = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) { // 从第一个开始遍历
                area += 2 + 4 * A[row][col]; // 不算前后左右遮挡
                if (row != 0) { // 不是第一行
                    area -= Math.min(A[row - 1][col], A[row][col]) * 2; // 左边覆盖掉最小的值
                }
                if (col != 0) { // 不是第一列
                    area -= Math.min(A[row][col - 1], A[row][col]) * 2; //上边覆盖掉
                }
            }
        }
        System.out.println(area);

    }
}
