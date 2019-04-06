package interview.tencentApril5th;

import java.util.Scanner;
public class Third {
    
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] d= new long[n];
        for (int i = 0; i < n; i++) {
        	d[i] = in.nextLong();
        }
        
        int dSum = 0;
        int cost = 0;
        // int[] p= new int[n];
        for (int i = 0; i < n; i++) {
        	int p = in.nextInt();
        	if (d[i] > dSum ) {
        		cost += p;
        		dSum += d[i];
        	}
        }
        System.out.print(cost);
    }
      
}

