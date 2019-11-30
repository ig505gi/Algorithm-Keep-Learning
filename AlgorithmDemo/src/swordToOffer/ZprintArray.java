package swordToOffer;

import java.util.ArrayList;

/**
 * @author Gao Yuan
 * @date 2019-08-22 - 12:12
 */
public class ZprintArray {
    /*
    写了半天写的是Z字型打印数组，而不是顺时针。。。
    */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0;
        int col = 0;
        boolean isAdd = true;
        while (row < n) {
            // col是一直加的，row是看标志进行加或者减的
            list.add(matrix[row][col]);
            if (isAdd) col++;
            else col--;
            if (col == n || col == -1) {
                isAdd = !isAdd; row++;
                if (isAdd) col++;
                else col--;
            }
        }
        return list;
    }
}
