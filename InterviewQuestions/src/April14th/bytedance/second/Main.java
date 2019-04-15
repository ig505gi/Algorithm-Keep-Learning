package April14th.bytedance.second;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * 读取帧数，找出最大的连续帧。。也通过了12.5
 * @author GaoYuan
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);  
		int n = scanner.nextInt();
		// 保存结果的数组
		int[] res = new int[n];
		// n个测试用例
		for (int i = 0; i < n; i++) {
			int max = 1;
			//输入每个测试用例
			int m = scanner.nextInt();
			// 读取帧数
			// ArrayList<ArrayList<Integer[]>> list = new ArrayList<ArrayList<Integer[]>>();
			HashMap<Vector, Integer> freq = new HashMap<Vector, Integer>();
			for (int j = 0; j < m; j++) {
				// 读取有多少帧
				int numOfFrame = scanner.nextInt();
				ArrayList<Vector> newframes = new ArrayList<Vector>();
				// 读取特征
				for (int num = 0; num < numOfFrame; num++) {
					Vector v = new Vector(scanner.nextInt(), scanner.nextInt());
					if (freq.containsKey(v)) {
						freq.replace(v, freq.get(v) + 1);
					} else {
						newframes.add(v);
					}
				}
				if (j == 0) continue;
				for (Vector v: freq.keySet()) {
					if (freq.get(v) == 1) {
						freq.remove(v, 1);
					}
				}
				for (Vector v: newframes) {
					freq.put(v, 1);
				}
				
				for (Vector v: freq.keySet()) {
					if (freq.get(v) > max) {
						max = freq.get(v);
					}
				}
				
			}
			
			// 保存第i个用例的结果
			res[i] = max;
		}
		
		// 输出结果
		for (int i = 0; i < n; i++) {
			System.out.println(res[i]);	
		}
		

	}

}

class Vector {
	int x;
	int y;
	Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Vector that) {
		if (this.x == that.x && this.y == that.y) return true;
		return false;
	}
	
}

