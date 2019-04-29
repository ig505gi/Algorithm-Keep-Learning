package April28th.baidu.third;

import java.util.HashMap;
/**
 * 每个整数都希望可以整除很多数字，特别是它自身包含的数字，我们将整数分为三类：

1.    数字可以整除它包含的一部分数字，比如72，由，7和2两种数字组成，72可以整除2，我们称这个数字是开心的，用”H”表示。

2.    数字不能整除它包含的任何数字，比如73，由，7和3两种数字组成，73不能整除任何数，我们称这个数字是沮丧的，用”S”表示。

3.    数字可以整除它包含的所有数字，比如12，既可以整除1又可以整除2，我们称它是一个非常棒的数，用“G”表示。

(0可以被任何数整除。)

输入
输入第一行包含一个整数T，表示数据组数(1<=T<=100).

接下来T行，每行包含一个正整数n(1<=n<=10^12)，表示需要你判断的数字。

输出
对于每组测试数据输出一行，“H”，“S”或“G”。表示整数种类。


样例输入
3
72
73
12
样例输出
H
S
G
 */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		char[] res = new char[n];
		for (int i = 0; i < n; i++) {
			String line = sc.nextLine();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int key = 1; key < 10; key++) {
				map.put(key, 0);
			}
			int numMod = 0;
			long num = Long.parseLong(line);
			for (int j = 0; j < line.length(); j++) {
				int m = Integer.parseInt("" + line.charAt(j));
				if (map.get(m) == 0) {
					if (m == 0 || num % m == 0) {
						map.replace(m, 1);
						num = num / m;
					}
				} else {
					map.replace(m, map.get(m) + 1);
					num = num / m;
				}
//				if (num % m == 0) {
//					numMod++;		
//					if (numMod <= j) {
//						res[i] = 'H'; break;
//					}
//				} else {
//					if (numMod > 0) {
//						res[i] = 'H'; break;
//					}

			}
			for (int key = 1; key < 10; key++) {
				numMod += map.get(key);
			}
			if (numMod == 0)
				res[i] = 'S';
			else if (numMod == line.length())
				res[i] = 'G';
			else
				res[i] = 'H';
		}
		sc.close();

		for (char r : res) {
			System.out.println(r);
		}

	}

}
