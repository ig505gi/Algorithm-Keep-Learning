package August30th.alibaba;

/**
 * @author Gao Yuan
 * @date 2019-08-30 - 19:50
 * 第一行，输入一个大于等于6，小于等于30的正整数n
 * 随后输入n行，每行有n个使用逗号分隔的数字，分别代表方格机关每一行对应的方格中的数字
 * 输出:
 * 从最上边开始，最快时间通过区域到达对边，所需要的时间
 * 输入范例:
 * 8
 * 35,92,98,68,35,65,26,72
 * 29,78,83,16,5,89,92,28
 * 48,51,37,79,65,74,50,71
 * 98,78,99,57,1,30,22,16
 * 72,88,55,33,56,58,28,49
 * 4,28,29,20,18,61,11,73
 * 61,19,47,34,85,32,77,89
 * 29,49,10,81,52,5,63,25
 * 输出范例:
 * 76
 */
import java.util.Scanner;

public class First {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        int[][] area = new int[n][n];

        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            String[] split = line.split(",");
            if (split.length != n) {
                throw new IllegalArgumentException("错误输入");
            }
            int j = 0;
            for (String num : split) {
                area[i][j++] = Integer.parseInt(num);
            }
        }

        int minimumTimeCost = getMinimumTimeCost(n,area);
        System.out.println(minimumTimeCost);
    }

    /** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    private static int getMinimumTimeCost(int n, int[][] area) {
        // 从右下角开始dp
        // 最后一行消耗时间都是0
        int[][] dp = new int[n][n];
        for (int row = n - 2; row >= 0 ; row--) {
            for (int col = n - 1; col >= 0 ; col--) {
                int val1 = Integer.MAX_VALUE;
                if (col + 2 < n) {
                    val1 = dp[row][col + 2] + area[row][col + 1];
                }
                int val2 = area[row + 1][col]; // 下一行等待的时间
                if (row + 2 < n) {
                    val2 += dp[row + 2][col];
                }
                dp[row][col] = Math.min(val1, val2);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[0][j]);
        }
        return min;
    }
}
