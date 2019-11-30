package September4th.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Third {

    /*
    这个问题是将数组分成m个数组，使这个和最小
     */
    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int schedule(int m,int[] array) {
        if (m >= array.length) { // 当分布式多于任务时，直接输出最大值
            int max = array[0];
            for(int i = 1; i < array.length; i++) {
                max = Math.max(max, array[i]);
            }
            return max;
        }
        int[] helpSum = new int[array.length];
        helpSum[0] = array[0]; // help数组能够在O(1)复杂度得到任意子数组的和
        for (int i = 1; i < array.length; i++) {
            helpSum[i] = helpSum[i - 1] + array[i];
        }
        return help(m, array, 0, helpSum);
    }

    private static int help(int m, int[] array, int start, int helpSum[]) {
        if (m == 1) {
            return helpSum[helpSum.length - 1] - (start == 0 ? 0 : helpSum[start - 1]);
        }
        if (start == array.length - 1) { // 只有一个值了 直接返回，不管m是不是1，都不影响结果
            return array[array.length - 1];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < array.length - start; i++) {
            int max = helpSum[start + i - 1] - (start == 0 ? 0 : helpSum[start - 1]);
            max = Math.max(max, help(m - 1, array, start + i, helpSum));
            res = Math.min(max, res);
        }
        return res;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int size  = in.nextInt();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        int res = schedule(m,array);
        System.out.println(String.valueOf(res));
    }
}

