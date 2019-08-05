package leetCode.newcodercourse;

import java.util.Stack;

/**
 * @author Gao Yuan
 * @date 2019-08-03 - 19:11
 * 整型矩阵中全是1 0，求最大的全是1的区域
 */
public class MaxOnlyOneMatrix {
    /*
    第一步：转化为一行一行的数组问题，求以从1--n-1每一行为底的最大全是1区域，每次遍历一行更新能够连接的1，然后求该数组中的最大矩阵。
第二步：怎么求这个数组中的最大矩阵，上图中的222，如果没有重复值，可以用单调栈，重复值需要改进单调栈，1.栈中保存index，计算大小的时候直接arr[index]来计算。2.如果遇到相同的值，直接结算，并压入新的值，如果有重复的就重新结算，最后得到的一定是max
这个题 矩阵压缩+数组累加+单调栈变形

     */
    public int getMaxMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] height = new int[m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + 1;
            }
            max = Math.max(max, getMaxArr(height));
        }
        return max;
    }

    private int getMaxArr(int[] height) {
        if (height == null || height.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.empty()) {
                int pkIdx = stack.peek();
                if (height[i] > height[pkIdx]) {
                    break; // 单调栈直接压入新值，
                } else {
                    stack.pop(); // 弹出重复的值的idx
                    int temp =  (i - (stack.empty() ? -1 : stack.peek()) - 1) * height[pkIdx];
                    max = Math.max(max, temp);
                    if (height[i] == height[pkIdx]) break;
                }
            }
            stack.push(i);
        }
        while (!stack.empty()) { // 还在栈中的 有边界都是 n
            int pkIdx = stack.pop();
            int temp =  (n - (stack.empty() ? -1 : stack.peek()) - 1) * height[pkIdx];
            max = Math.max(max, temp);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxOnlyOneMatrix solution = new MaxOnlyOneMatrix();
        int[][] matrix = {{1,0,1,1,1},
                          {1,1,1,1,1},
                          {1,1,1,1,0}};
        System.out.println(solution.getMaxMatrix(matrix));
    }
}
