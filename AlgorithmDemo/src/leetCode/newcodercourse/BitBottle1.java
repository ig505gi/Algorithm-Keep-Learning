package leetCode.newcodercourse;

/**
 * @author Gao Yuan
 * @date 2019-08-05 - 14:37
 *
 * 17年左右阿里的笔试题
 * 打瓶子问题，，假设2，3，5，打掉3 得分2X3X5，然后打2，5.。。这样得分最高分
 * 首先为了避免边界判断，先把两边加上1，这样就要求 f(1,N-2)
 * 对于每个递归函数，求f(l,r),当l=r的时候，只有一个，返回arr[l-1] * arr[l] * a[r+1]
 * 对于l到r种情况，每次要尝试的情况是，最后打掉i的情况加上f(l,i-1) + f(i+1, r)
 */
public class BitBottle1 {

    public int getMaxScore(int[] arr) {
        // 判断一个和0的情况 没写
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length - 1] = 1;

        for (int i = 0; i < arr.length; i++) {
            help[i + 1] = arr[i];
        }

        return f(help, 1, help.length - 2);
    }

    private int f(int[] arr, int l, int r) {
        if ( l == r) return arr[l - 1] * arr[l] * arr[r + 1];
        // 先求 最后一个打l或者r的最大值
        int max = Math.max(
                arr[l - 1] * arr[l] * arr[r + 1] + f(arr, l + 1, r),
                arr[r + 1] * arr[r] * arr[l - 1] + f(arr, l, r - 1)
        );
        for (int i = l + 1; i < r; i++) {
            max = Math.max(max,
                    arr[l - 1] * arr[i] * arr[r + 1] + f(arr, l, i - 1) + f(arr, i + 1, r));
        }
        return max;
    }

    public int dp1(int[] arr) {
        // 判断一个和0的情况 没写
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length - 1] = 1;

        for (int i = 0; i < arr.length; i++) {
            help[i + 1] = arr[i];
        }

        int[][] dp = new int[help.length][help.length];
        // 初始化对角线
        for (int i = 1; i < dp.length - 1; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        // 大循环应该是对角线，因此 应该是l和r的差值，从1开始,d表示差值
        for (int d = 1; d < help.length - 1; d++) {
            for (int l = 1; l + d < help.length - 1; l++) {
                int r = l + d; // l,r 确定下来了
                dp[l][r] = Math.max(
                        help[l - 1] * help[l] * help[r + 1] + dp[l + 1][r],
                        help[r + 1] * help[r] * help[l - 1] + dp[l][r - 1]
                );
                for (int i = l + 1; i < r; i++) {
                    dp[l][r] = Math.max(dp[l][r],
                            help[l - 1] * help[i] * help[r + 1] + dp[l][i - 1] + dp[i + 1][r]);
                }
            }
        }
        return dp[1][help.length - 2];
    }

    public static void main(String[] args) {
        BitBottle1 b = new BitBottle1();
        int[] arr = new int[]{2,3,5,3,5};
        System.out.println(b.getMaxScore(arr));
        System.out.println(b.dp1(arr));
    }
}
