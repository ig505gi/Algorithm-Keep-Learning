package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-21 - 15:56
 */
public class SetMatrixZeroes {

    /*
    Runtime: 1 ms, faster than 100.00% of Java online submissions for Set Matrix Zeroes.
Memory Usage: 48.2 MB, less than 41.43% of Java online submissions for Set Matrix Zeroes.
这题主要优化空间复杂度
可以用数组的第一列和第一行来保存，还需要用两个标志第一行和第一列是否为0
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0) return;
        boolean[] zeroRows = new boolean[matrix.length]; // 记录已经哪些行是 0
        boolean[] zeroCols = new boolean[matrix[0].length]; // 记录哪些列是 0
        for (int r = 0; r < zeroRows.length; r++) {
            for (int c = 0; c < zeroCols.length; c++) {
                if (matrix[r][c] == 0) {
                    zeroRows[r] = true;
                    zeroCols[c] = true;
                }
            }
        }
        for (int r = 0; r < zeroRows.length; r++) {
            if (zeroRows[r]) {
                matrix[r] = new int[zeroCols.length];
            }
        }
        for (int c = 0; c < zeroCols.length; c++) {
            if (zeroCols[c]) {
                for (int rr = 0; rr < zeroRows.length; rr++) {
                    matrix[rr][c] = 0;
                }
            }
        }
    }
}
