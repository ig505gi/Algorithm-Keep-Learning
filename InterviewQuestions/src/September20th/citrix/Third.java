package September20th.citrix;

import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-09-20 - 20:34
 */
public class Third {

    public static int maxPathSum(List<List<Integer>> board, int p, int q) {
        // Write your code here
        int n = board.size();
        int m = board.get(0).size();
        int[][] arr = new int[n][m];
        int[][] arr2 = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = board.get(i).get(j);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                arr2[i][j] = board.get(n - 1 - i).get(j);
            }
        }
        return Math.max(maxSum(arr, p), maxSum(arr2, q));
    }

    private static int maxSum(int[][] arr, int q) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                int max = arr[i + 1][j];
                if (j - 1 >= 0) max = Math.max(max, arr[i + 1][j - 1]);
                if (j + 1 < m) max = Math.max(max, arr[i + 1][j + 1]);
                arr[i][j] += max;
            }
        }
        return arr[0][q];
    }
}
