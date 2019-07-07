package April14th.bytedance.fourth;

import java.util.Scanner;
/**
 * 给个矩阵车票价格，从北京出发再回北京，所有地方都要去，最低价格
 * @author GaoYuan
 *
 */
public class Main {
	
	static int[][] costs = {{0,3,6,7},{5,0,2,3},{6,4,0,2},{3,7,5,0}};
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);  
//		int n = scanner.nextInt();
//		for (int i = 0; i < n; i++) {
//			
//		}
		// costs[i][j],从j到i花费的钱
		
		int N = costs.length;
		
		int code = (int) Math.pow(2, N - 1) - 1;
		
		System.out.println(cost(0, code));

	}
	private static int cost(int i, int code) {
		if (code == 0) {
			System.out.printf("(%d, %d): %d\n", i, code, costs[i][0]);
			return costs[i][0];
		}
		int res = Integer.MAX_VALUE;
		// 一个进行位运算的临时变量
		int temp = code;
		for (int j = 1; j < costs.length; j++) { // 遍历所有城市
			if ((temp & 1) == 1) { // 保证在集合中
				int c = costs[i][j] + cost(j, (int)(code - Math.pow(2, j - 1)));
				if (c < res) res = c;
			}
			temp >>= 1; // 每次比较的都是第一位
		}
		System.out.printf("(%d, %d): %d\n", i, code, res);
		return res;
	}

}
