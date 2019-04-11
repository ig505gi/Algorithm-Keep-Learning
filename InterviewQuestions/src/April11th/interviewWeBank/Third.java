package April11th.interviewWeBank;

import java.util.Arrays;
import java.util.Scanner;
/**
 * n 能跳 3n+1和n/2
 * 给定t次游戏 <=100
 * 再给定初始的格子 <= 1000
 * 输出 要跳几次到1
 * 比如输入：
 * 3
 * 1 2 3
 * 输出：
 * 0
 * 1
 * 7 (3-10-5-16-8-4-2-1)
 * 
 * 从1开始bfs，但是没有限制 比如 输入999 虽然不大于1000
 * 但是可以跳到 2997。。
 * @author GaoYuan
 *
 */
public class Third {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[] n = new int[t];
		int max = 0;
		for (int i = 0; i < t; i++) {
			n[i] = sc.nextInt();
			if (n[i] > max) max = n[i];
		}
		sc.close();
		int[] dp = new int[1001];
		Arrays.fill(dp, -1);
		dp[1] = 0;
        bfs(dp, 1);
		for (int s: n) {
			System.out.println(dp[s]);
		}
        

	}

	private static void bfs(int[] dp, int tar) {
		if (tar * 2 < dp.length && dp[tar * 2] == -1) {
			dp[tar * 2] = dp[tar] + 1;
			bfs(dp, tar * 2);
		}
		int i = (tar - 1) / 3;
		if (i < dp.length && tar > 3 && (tar - 1) % 3 == 0 && dp[i] == -1) {			
			dp[i] = dp[tar] + 1;
			bfs(dp, i);
		}
		
	}

}
