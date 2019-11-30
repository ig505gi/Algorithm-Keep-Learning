package overexercise;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 12:53
 */
public class MergeSort {

    public void mergeSortLoop(int[] arr) {
        int k = 2;
        while (k < arr.length << 1) {
            for (int i = 0; i < arr.length; i = i + k) {
                merge(arr, i, k);
            }
            k <<= 1;
        }
    }

    private void merge(int[] arr, int i, int k) {
        if (i + k / 2 >= arr.length) return; // 剩余只有一个区间
        int i1 = i;
        int i2 = i1 + k / 2 - 1;
        int j1 = i2 + 1; // 一定在arr的index范围内
        int j2 = Math.min(arr.length - 1, i1 + k - 1);
        int[] helpArr = new int[j2 - i1 + 1];
        int j = 0;
        for (; j < helpArr.length; j++) {
            if (j1 > j2 || (i1 <= i2 && arr[i1] <= arr[j1])) helpArr[j] = arr[i1++];
            else helpArr[j] = arr[j1++];
        }
        // 赋值给原数组
        for (j = 0; j < helpArr.length; j++) {
            arr[i++] = helpArr[j];
        }
    }

    public void mergeSortRecursion(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int i, int j) {
        if (i == j) return;
        int mid = (i + j) / 2;
        mergeSort(arr, i, mid);
        mergeSort(arr, mid + 1, j);
        merge(arr, i, mid, j);
    }

    private void merge(int[] arr, int i, int mid, int j) {
        int[] helpArr = new int[j - i + 1];
        int index = 0;
        int i1 = i;
        int i2 = mid + 1;
        for (; index < helpArr.length; index++) {
            if (i2 > j || (i1 <= mid && arr[i1] <= arr[i2])) {
                helpArr[index] = arr[i1++];
            } else {
                helpArr[index] = arr[i2++];
            }
        }
        for (index = 0; index < helpArr.length; index++) {
            arr[i++] =helpArr[index];
        }
    }

    /**
     * 第1000次排序：排序结果是否正确：true loop：180ms  >recur: 156ms
     * 总时长对比89s : 77s
     * @param args
     */
    public static void main(String[] args) {
        MergeSort solution = new MergeSort();
        Random random = new Random();
        int count = 1000;
        long loopTime = 0;
        long recurTime = 0;
        while (count-- > 0) {
            int n = random.nextInt(1000000);
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(1000000);
            }
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            long startTime =  System.currentTimeMillis();
            solution.mergeSortLoop(arr);
            long endTime = System.currentTimeMillis();
            long time1 = endTime - startTime;
            startTime = System.currentTimeMillis();
            solution.mergeSortRecursion(arr2);
            endTime = System.currentTimeMillis();
            long time2 = endTime - startTime;
            System.out.printf("第%d次排序：排序结果是否正确：%s ", 1000 - count, Arrays.equals(arr, arr2));
            System.out.println("loop："+ time1 + "ms  " +
                    (time1 < time2 ? "<" : (time1 == time2 ? "=" : ">")) + "recur: " + time2 + "ms");
            loopTime += time1;
            recurTime += time2;
        }
        System.out.println("总时长对比" + loopTime / 1000 + "s : " + recurTime  / 1000 + "s");

    }
}
