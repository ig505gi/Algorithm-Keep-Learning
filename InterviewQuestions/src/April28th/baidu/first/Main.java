package April28th.baidu.first;


/**
 * 珐达采下个月要去鸥洲各国考察一趟，采购流通神秘石并从中搞点油水。

珐达采会按顺序依次经过序号分别为1, 2, 3, …, n的鸥洲国家，在第i个国家神秘石的流通价格为Ai鸥。因为行程紧张，在每个国家的停留时间有限，所以他只能花费Ai鸥买入一块神秘石，或者卖出一块手中的神秘石获得Ai鸥，或者什么都不做，而且因为神秘石的保存需要极其先进的高级材料容器，其材料稀有且制作困难，珐达采只有一份容器，故无论何时珐达采手里最多只能拥有一块神秘石。

珐达采想知道最终能从中获利最大多少鸥。因为交易需要手续费，所以珐达采还想知道在获利最大收益的同时，最少需要交易多少次。因为珐达采是大财阀，所以你可以认为他一开始金钱无限。

输入
第一行一个数n。（1≤n≤100000）

第二行n个数，第i个数表示Ai。（1≤Ai≤1e9）

输出
共一行，两个数，分别代表最大收益和对应的最少交易次数。


样例输入
5
9 7 10 1 5
样例输出
7 4

提示
在#2买入，#3卖出，#4买入，#5卖出，-7+10-1+5=7
 * @author GaoYuan
 *
 */
import java.util.Scanner;
public class Main {
	/*
	 * 贪婪，如果下一天贵，今天就不买，直接跳，如果下一天贵，就买
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		long[] nums = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		sc.close();
		long buy = 0;
		int times = 0;
		long pri = 0;
		for (int i = 0; i < n; i++) {
			if (buy == 0) {
				if (i+1 < n && nums[i+1] > nums[i]) {
					buy = nums[i];
					times++;
				}
			} else {
				if (i+1 < n && nums[i+1] < nums[i]) {
					pri += nums[i] - buy;
					buy = 0;
					times++;
				} else if (i + 1 == n) {
					pri += nums[i] - buy;
					times++;
				}
			}
		}
		
		System.out.print(pri + " " + times);

	}

}
