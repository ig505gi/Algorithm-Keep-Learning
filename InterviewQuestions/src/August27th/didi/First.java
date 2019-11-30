package August27th.didi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-27 - 19:47
 */
public class First {

    public static void main(String[] args) {
        // 输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int total = in.nextInt();
        int cost = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        // 算法
        // 构造一个 A 和B 是否相同的表格，从左上走到右下，
        List<Integer[]> sumList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i] == b[j]) sumList.add(new Integer[]{i+j, i, j});
            }
        }
        Collections.sort(sumList, (sum1,sum2) ->(sum1[0] - sum2[0])); // 未考虑两者相同的情况
        int i = -1, j = -1, idx = 0;
        int res = 0;
        int curCost = 0;
        while (idx < sumList.size() && i < n - 1 && j < n - 1) {
            Integer[] sum = sumList.get(idx++);
            if (sum[1] > i && sum[2] > j) {
                if (curCost + cost > total) break;
                res++;
                i = sum[1];
                j = sum[2];
                curCost += cost;
            }
        }
        if (i == n - 1 && j == n - 1) {
            System.out.println(res);
        } else if (curCost + cost > total) {
            System.out.println(res-1);
        } else {
            System.out.println(res);
        }
    }

}
