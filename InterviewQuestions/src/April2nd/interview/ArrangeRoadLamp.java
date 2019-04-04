package April2nd.interview;
/**
 * 

时间限制：1秒
空间限制：32768K
小Q正在给一条长度为n的道路设计路灯安置方案。
为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要照亮的障碍物格子用'X'表示。
小Q现在要在道路上设置一些路灯, 对于安置在pos位置的路灯, 这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。
小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需要多少盏路灯。

输入描述:
输入的第一行包含一个正整数t(1 <= t <= 1000), 表示测试用例数
接下来每两行一个测试数据, 第一行一个正整数n(1 <= n <= 1000),表示道路的长度。
第二行一个字符串s表示道路的构造,只包含'.'和'X'。

输出描述:
对于每个测试用例, 输出一个正整数表示最少需要多少盏路灯。
输入例子1:

2
3
.X.
11
...XX....XX

输出例子1:
1
3
 * @author GaoYuan
 *分析： 一个X没用，可以合并两边的点，只有遇到两个以上X才算新的一个集合，求路灯数，相加
 *有 k个点需要照亮，所需要的路灯数是 (k - 1)/3 + 1
 * !!!!!!!!!!!!!!!!!!!!
 * 思路貌似是错误的。。。。。。真的有点难
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ArrangeRoadLamp {

	public static void main(String[] args) throws Exception {
		InputStreamReader sr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(sr); 
		// Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(br.readLine());
		// 保存t次测试结果
		int[] res = new int[t];
		for (int i = 0; i < t; i++) {
			// 第t次测试 循环
			int n = Integer.parseInt(br.readLine());
			String road = br.readLine();
			// 求出第t次测试的结果
			int numberToLight = (road.charAt(0) == 'X')?0:1; 
			for (int j = 1; j < n; j++) {
				if (road.charAt(j) == 'X') {
					if (j + 1 >= n) {
						//.X空和XX空
						res[i] += (numberToLight == 0)?0:(numberToLight - 1) / 3 + 1;
					} else if (road.charAt(j + 1) == 'X') {
						// .XX和XXX 
						continue;
					} else if (road.charAt(j + 1) == '.') {
						if (road.charAt(j - 1) == '.') {
							// .X.
							numberToLight++;
						} else {
							// XX.
							res[i] += (numberToLight == 0)?0:(numberToLight - 1) / 3 + 1;
							numberToLight = 0;
						}
					}
				} else {
					numberToLight++;
					if (j + 1 >= n) {
						res[i] += (numberToLight == 0)?0:(numberToLight - 1) / 3 + 1;
					}
				}
			}	
		}		
		for (int num: res) {
			System.out.println(num);
		}
	}

}
