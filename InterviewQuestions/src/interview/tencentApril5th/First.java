package interview.tencentApril5th;

import java.util.Arrays;
import java.util.Scanner;
public class First {
    /**
     * 先判断有没有1 ，如果没有1 就无解，有1 一定有解
     * 用 values[0] 到values[n-2] 去等于 values[n - 1]
     *  m - n
     */
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = in.nextInt(); 
        }
        Arrays.sort(values);
        int[] papers = new int[n];
        if (values[0] != 1) {
        	System.out.println(-1);
        } else {
        	// 需要几个轮回 
        	for (int i = 0; i < n - 1; i++) {
        		int paper = values[i + 1] / values[i];
        		papers[i] = (values[i + 1] % values[i] == 0)?paper- 1: paper; 
        	}
        	
        	int sum = 0;
        	int res = 0;
        	for (int i = 0; i < n - 1; i++) {
        		sum += papers[i] * values[i];
        		res += papers[i];
        	}
        	int leftM = m - sum;
        	int numOfN = leftM / values[n - 1];
    		res += numOfN;
        	System.out.println(res);
        }
    }

}
