package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-07-24 - 17:06
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
 * Memory Usage: 36.4 MB, less than 99.17% of Java online submissions for Rotate Image.
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // nXn
        // 将每行的顺序反过来
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = n - j - 1;
                if (j >= k) break;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
            }
        }

        // 对角互换 i，j和j，i互换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }
    }

}
