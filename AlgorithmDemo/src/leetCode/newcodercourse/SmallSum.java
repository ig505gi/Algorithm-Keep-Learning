package leetCode.newcodercourse;

import java.util.Arrays;

/**
 * @author Gao Yuan
 * @date 2019-08-03 - 12:06
 */
public class SmallSum {
    /*
    小和问题
    概念见笔记，思路，利用mergesort
     */
    public int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return help(arr, 0, arr.length - 1);
    }

    private int help(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        return help(arr, left, mid) + help(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] auxArr = new int[right - left + 1];
        int lo = left;
        int hi = mid + 1;
        int sum = 0;
        int idx = 0;
        while (lo <= mid && hi <= right) {
            if (arr[lo] <= arr[hi]) {
                sum += arr[lo] * (right - hi + 1);
                auxArr[idx++] = arr[lo++];
            } else {
                auxArr[idx++] = arr[hi++];
            }
        }
        if (lo <= mid) {
            while (idx < auxArr.length) auxArr[idx++] = arr[lo++];
        }
        if (hi <= right) {
            while (idx < auxArr.length) auxArr[idx++] = arr[hi++];
        }

        for (int i = 0; i < auxArr.length; i++) {
            arr[left++] = auxArr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        SmallSum solution = new SmallSum();
        int[] arr = {1, 3, 5, 2, 4, 6};
        System.out.println(solution.getSmallSum(arr));
    }

}
