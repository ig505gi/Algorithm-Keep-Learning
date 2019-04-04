package April2nd.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 输入描述:
每个输入包含一个测试用例。
每个测试用例的第一行包含一个正整数，表示转方向的次数N(N<=1000)。
接下来的一行包含一个长度为N的字符串，由L和R组成，L表示向左转，R表示向右转。

输出描述:
输出牛牛最后面向的方向，N表示北，S表示南，E表示东，W表示西。

输入例子1:
3
LRR

输出例子1:
E
 * @author GaoYuan
 *
 */
public class FindDirection {
	public static void main(String[] args) {
		InputStreamReader sr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(sr);
		try {
			int n = Integer.parseInt(br.readLine());
			String steps = br.readLine();
			if (steps.length() != n) throw new IllegalArgumentException("输入不规范");	
			int direcToN = 0;
			for (int i = 0; i < n; i++) {
				if (steps.charAt(i) == 'R') {
					direcToN = (direcToN + 1) % 4;
				}
				if (steps.charAt(i) == 'L') {
					// 不用 -1，防止出现负数
					direcToN = (direcToN + 3) % 4;
				}
			}
			char[] res = {'N', 'E', 'S', 'W'};
			System.out.println(res[direcToN]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
