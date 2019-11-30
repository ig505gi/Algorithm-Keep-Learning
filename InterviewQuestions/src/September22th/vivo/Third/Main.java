package September22th.vivo.Third;

/**
 * @author Gao Yuan
 * @date 2019-09-22 - 16:18
 */
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Welcome to vivo !
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {

        // TODO Write your code here
        int n = input.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += input[i];
        }
        int w[] = input;
        int[][] dp = new int[n][sum / 2 + 1];
        if (w[0] <= sum / 2) dp[0][w[0]] = 1;
        for (int i = 1; i < n; i++) {
            if (w[i] <= sum / 2)
                dp[i][w[i]] = closeToSum2(1, dp[i][w[i]], sum); // 如果这个数字可以有2个合起来，没有就不更新
            for (int j = 1; j < (sum / 2 + 1); j++) { // 用i-1行去填充第 i行
                if (dp[i - 1][j] != 0 && j + w[i] < (sum / 2 + 1))
                    dp[i][j + w[i]] = dp[i - 1][j] + 1; // 由几个数字组成，多了一个数字
                dp[i][j] = closeToSum2(dp[i - 1][j], dp[i][j], sum);
            }
        }
        boolean isOdd = n % 2 == 1;
        for (int j = sum / 2; j > 0; j--) {
            for (int i = n - 1; i >= 0; i--) {
                if (isOdd) {
                    if (dp[i][j] == n / 2 || dp[i][j] == n / 2 + 1) {
                        return sum - 2 * j;
                    }
                } else {
                    if (dp[i][j] == n / 2) {
                        return sum - 2 * j;
                    }
                }
            }
        }
        return -1;
    }

    private static int closeToSum2(int i, int j, int sum) {
        if (Math.abs(sum / 2 - i) <= Math.abs(sum / 2 - j)) {
            return i;
        } else return j;
    }
}
