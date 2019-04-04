package April2nd.interview;

import java.util.Scanner;

/**
 * 输入描述:
输入包括两行
第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。

输出描述:
输出一个正整数, 表示牛牛一共有多少种零食放法。

输入例子1:
3 10
1 2 4

输出例子1:
8

例子说明1:
三种零食总体积小于10,于是每种零食有放入和不放入两种情况，一共有2*2*2 = 8种情况。
 * @author GaoYuan
 *
 */
public class PutSnackIntoBag {

	public static void main(String[] args) {
		// 读取数据
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long w = in.nextInt();
		long[] v = new long[n];
		for (int i = 0; i < n; i++) {
			v[i] = in.nextInt();
		}
		in.close();
		// 求和
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += v[i];
		}
		// 维持一个从index开始后面所有元素的和的数组
		long[] sumFromIndex = new long[n];
		for (int i = 0; i < n; i++) {
			sumFromIndex[i] = sum;
			sum -= v[i];
		}
		// 维持一个计算 2的次方的数组，可以直接从里面读取2的几次方
		long[] qua = new long[n + 1];
		qua[0] = 1; 
		for (int i = 1; i < n + 1; i++) {
			qua[i] = qua[i-1] * 2;
			// System.out.print(qua[i] + " ");
		}
		// 用递归求结果
		long res = dp(n, w, v, sumFromIndex, qua);
		System.out.println(res);
	}

	private static long dp(int n, long w, long[] v, long[] sumFromIndex, long[] qua) {
		long res = 0;
		// 还有n个元素需要选择，则目前遍历的index
		int index = v.length - n;
		// 如果 n个元素的和 比w小，直接是 2的n次方结果
		if (sumFromIndex[index] <= w) res = qua[n];
		// 如果到了最后一个元素
		else if (n == 1) {
			// 如果最后一个元素大于w，不能放进去
			if (v[index] > w) res = 1;
			// 如果不大于，可放可不放
			else res = 2;
		} else {
			// 需要递归
			res += dp(n - 1, w - v[index], v, sumFromIndex, qua);
			res += dp(n - 1, w, v, sumFromIndex, qua);
		}
		return res;
	}
}
