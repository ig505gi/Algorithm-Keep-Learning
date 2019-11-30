package August29th.shunfeng;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-29 - 19:00
 */
public class Second {

    public static void main(String[] args) {
        // 输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // 求最长单调子序列，单调可以相等，不严格单调
        int[] stack = new int[n];
        stack[0] = arr[0];
        int top = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] >= stack[top]) stack[++top] = arr[i];
            else {
                // 二分查找
                int low = 0, high = top, mid;
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (arr[i] >= stack[mid]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                stack[low] = arr[i];
            }
        }
        System.out.println(top + 1);
    }
}
