package September15th.zhaoshangyinhang;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-09-15 - 21:39
 */
public class Second {
    /*
    一直不过，不知道为什么
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tree = new int[n + 1];
        int[] dp = new int[n + 1];
        int[] lc = new int[n + 1];
        int[] rc = new int[n + 1];
        for (int i = 1; i < n; i++) {
            int p = sc.nextInt();
            int c = sc.nextInt();
            if (lc[p] == 0) lc[p]= c;
            else rc[p] = c;
            tree[c] = sc.nextInt();
        }
        recurse(tree, dp, lc, rc, 1);
        for (int i = 1; i < dp.length; i++) {
            System.out.print(dp[i]);
            if (i != dp.length - 1) System.out.print(" ");
        }
    }

    private static void recurse(int[] tree, int[] dp, int[] lc, int[] rc, int curP) {
        if (lc[curP] == 0 && rc[curP] == 0) return;
        int l = Integer.MIN_VALUE, r = Integer.MIN_VALUE;
        if (lc[curP] != 0) {
            recurse(tree, dp, lc, rc, lc[curP]);
            l = Math.max(tree[lc[curP]], tree[lc[curP]] + dp[lc[curP]]);
        }
        if (rc[curP] != 0) {
            recurse(tree, dp, lc, rc, rc[curP]);
            r = Math.max(tree[rc[curP]], tree[rc[curP]] + dp[rc[curP]]);
        }
        dp[curP] = Math.max(l, r);
        dp[curP] = Math.max(dp[curP], 0);
    }
}
