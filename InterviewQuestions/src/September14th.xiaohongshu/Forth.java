package September14th.xiaohongshu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-09-14 - 16:09
 */
public class Forth {
    static class Val implements Comparable<Val> {
        int x;
        int h;

        Val(int x, int h) {
            this.x = x;
            this.h = h;
        }
        @Override
        public int compareTo(Val that) {
            if (this.x != that.x) {
                return this.x - that.x;
            } else {
                return this.h - that.h;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Val[] xh = new Val[n];
        for (int i = 0; i < n; i++) {
            xh[i] = new Val(sc.nextInt(), sc.nextInt());
        }
        // 把x排序，相同情况下，比较h
//        Arrays.sort(xh, (xh1, xh2)->{
//            if (xh1[0] != xh2[0]) {
//                return xh1[0] - xh2[0];
//            } else {
//                return xh1[1] - xh2[1];
//            }
//        });
        Arrays.sort(xh);
        // x已经按照从小到大排序 ，找到 h的最长非递减子序列
        int dp[] = new int[xh.length];
        dp[0] = 1;
        for(int i = 1; i < xh.length; i++){
            int count = 1;
            for(int j = i - 1; j >= 0; j--){
                if(xh[j].h <= xh[i].h){
                    count = Math.max(count, dp[j] + 1) ;
                }
            }
            dp[i] = count;
        }
        int max = 0;
        for(int i = 0; i < xh.length; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
