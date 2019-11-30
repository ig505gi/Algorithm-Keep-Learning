package September14th.xiaohongshu;

/**
 * @author Gao Yuan
 * @date 2019-09-14 - 16:36
 */
import java.util.Scanner;

public class Third {

    public static void main(String[] args) {
        // 输入
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] papers = new int[n+1];
        for (int i = 1; i < n + 1; i++) {
            papers[i] = sc.nextInt();
        }
        // 动态规划
        int[][] dp = new int[n + 1][2];
        dp[1][0] = papers[1];
        dp[1][1] = 1;
        dp[2][1] = 1;
        if (papers[2] > papers[1]) dp[2][0] = papers[2];
        else dp[2][0] = dp[1][0];
        for (int i = 3; i < n + 1; i++) {
            if (papers[i] + dp[i-2][0] < dp[i-1][0]) {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1];
            } else if (papers[i] + dp[i-2][0] > dp[i-1][0]) {
                dp[i][0] = papers[i] + dp[i-2][0];
                dp[i][1] = dp[i-2][1] + 1;
            } else {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = Math.min(dp[i-2][1] + 1, dp[i-1][1]);
            }
        }
        System.out.println(dp[n][0] +" "+ dp[n][1]);
    }
}

