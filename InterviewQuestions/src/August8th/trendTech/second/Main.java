package August8th.trendTech.second;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-08 - 18:54
 * 花钱的背包问题，1 5 10 20 50 100
 * 各有 6 5 4 3 2 1 张
 * 能组成num2的种类
 */
public class Main {

    private static String process(String num1, String num2)
    {
        //Please write your code here
        int[] val = {1, 5, 10, 20, 50, 100};
        String[] nums =num1.split(" ");
        int[] pages = new int[nums.length];
        int tar = Integer.parseInt(num2);
        for (int i = 0; i < pages.length; i++) {
            pages[i] = Integer.parseInt(nums[i]);
        }
        int[][] dp = new int[val.length][tar+1];
        for (int i = 0; i < val.length; i++) { // 初始化第一列
            dp[i][0] = 1;
        }
        for (int j = 0; j <= pages[0]; j++) { // 初始化只用1能达到的tar
            dp[0][j] = 1;
        }
        for (int i = 1; i < val.length; i++) {
            for (int j = 1; j < tar + 1; j++) {
                for (int k = 0; k <= pages[i]; k++) { //张数
                    int newTar = j - k * val[i];
                    if (newTar >= 0) {
                        dp[i][j] += dp[i - 1][newTar];
                    }
                }
            }
        }
        return String.valueOf(dp[val.length - 1][tar]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String strValueSequences = sc.nextLine();
        String strChargeNum = sc.nextLine();

        String sum = process(strValueSequences, strChargeNum);
        System.out.println(sum);
    }
}
