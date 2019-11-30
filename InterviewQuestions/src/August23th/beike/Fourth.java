package August23th.beike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 20:07
 */
public class Fourth {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] w = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            sum += w[i];
        }
        int[][] dp = new int[n][sum / 2 + 1];
        if (w[0] <= sum / 2) dp[0][w[0]] = 1;
        for (int i = 1; i < n; i++) {
            if (w[i] <= sum / 2)
                dp[i][w[i]] = Math.max(1, dp[i][w[i]]); // 如果这个数字可以有2个合起来，没有就不更新
            for (int j = 1; j < (sum / 2 + 1); j++) { // 用i-1行去填充第 i行
                if (dp[i - 1][j] != 0 && j + w[i] < (sum / 2 + 1))
                    dp[i][j + w[i]] = dp[i - 1][j] + 1; // 由几个数字组成，多了一个数字
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
            }
        }
        // 取最后一行， 从后往前取，第一个不为0的
        for (int i = sum / 2; i > 0; i--) {
            if (dp[n - 1][i] != 0) { // 已经找到
                int k = dp[n - 1][i];
                System.out.print((sum - i - i) + " " + Math.abs(n - k - k));
                break;
            }
        }
    }
}
