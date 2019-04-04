package April2nd.interview;

import java.util.*;

/**
 * 每个输入包含一个测试用例。
每个测试用例的第一行包含一个正整数，表示闹钟的数量N(N<=100)。
接下来的N行每行包含两个整数，表示这个闹钟响起的时间为Hi(0<=A<24)时Mi(0<=B<60)分。
接下来的一行包含一个整数，表示从起床算起他需要X(0<=X<=100)分钟到达教室。
接下来的一行包含两个整数，表示上课时间为A(0<=A<24)时B(0<=B<60)分。
数据保证至少有一个闹钟可以让牛牛及时到达教室。

输出描述:
输出两个整数表示牛牛最晚起床时间。

输入例子1:
3 
5 0 
6 0 
7 0 
59 
6 59

输出例子1:
6 0

 * @author GaoYuan
 *
 */
public class LastGetUpTime {
	
	public static void main(String[] args) {
		/**
		 * 虽然写的多，但是时间复杂度是n，不用排序
		 * 但是计算上好像还是麻烦了，用 classTime - cost和clocks比较
		 * 而不是用 classTime - clocks - cost 和0 比较这样多遍历了一次。。代码复杂了
		 */
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] clocks = new int[n];
		for (int i = 0; i < n; i++) {
			clocks[i] = in.nextInt() * 60 + in.nextInt();
		}
		int cost = in.nextInt();
		int classTime = in.nextInt() * 60 + in.nextInt();
		in.close();
		
		int[] arrivalTime = new int[n];
		for (int i = 0; i < n; i++) {
			arrivalTime[i] = classTime - clocks[i] - cost;
			System.out.println(clocks[i] + ": " + arrivalTime[i]);
		}
		int res = Integer.MAX_VALUE;
		int index = -1;
			
		// 大于0，但是最接近0的		
		for (int i = 0; i < n; i++) {
			if (arrivalTime[i] >= 0 && arrivalTime[i] < res) {
				System.out.println("res: "+ res + "  arrivalTime: " + arrivalTime[i]);
				res = arrivalTime[i];
				index = i;
				System.out.println(res+ " "+ index);
			}
		}
		System.out.println(clocks[index] / 60 + " " + clocks[index] % 60);
	}
	
	public void solution() {
		/**
		 * 学到了Arrays的二分查找，
		 * 但是排序复杂度就是nlogn了
		 * 我自己的算法复杂度是n，
		 */
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] clocks = new int[n];
		for (int i = 0; i < n; i++) {
			clocks[i] = in.nextInt() * 60 + in.nextInt();
		}
		int cost = in.nextInt();
		int classTime = in.nextInt() * 60 + in.nextInt();
		in.close();
		// 用classTime -cost 然后直接对clocks排序，然后用二分查找即可
		int tar = classTime - cost;
		// 二分查找之前必须排序
		Arrays.parallelSort(clocks);
		// 这里直接可以用Arrays.binarySearch来查找
		int index = Arrays.binarySearch(clocks, tar);
		// binnarySearch如果找到，则返回的是index，如果没找到，则返回的是-(insert pos) - 1
		// insert pos就是tar应该插入的地方，，前一个比tar小，后一个比tar大
		// 要找到离tar最近，但是比tar小的下标是 -index -2，但是比tar大的就是 -index-1
		// 所以如果index小于1，说明没有刚好的，我们必须必这个刚好的时间要小
		if (index < 0)
			index = -(index + 2);
		System.out.println(clocks[index] / 60 + " " + clocks[index] % 60);
	}

}
