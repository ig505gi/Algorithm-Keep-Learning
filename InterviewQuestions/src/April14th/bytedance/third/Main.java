package April14th.bytedance.third;

import java.util.Scanner;
/**
 * 跳高塔的，找到了规律 2e - h(i+1) >= 0
 * @author GaoYuan
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);  
		int n = scanner.nextInt();
		 
//		int sum = 0;
//		int qu = 1;
//		for (int i = 1; i <= n; i++) {
//			qu *= 2;
//			sum *= 2;
//			sum += scanner.nextInt();
//		}
//		scanner.close();
//		System.out.println(sum / qu + 1);
		int[]h = new int[n];
		for (int i = 0; i < n; i++) {
			h[i] = scanner.nextInt();
		}
		// 2x - h >= t
		int t = 0;
		for (int i = n - 1; i >= 0 ; i--) {
			int mod = (h[i] + t) % 2;
			int shang = (h[i] + t) / 2; 
			t = mod==0?shang:shang + 1;
			// System.out.println(mod + ", " + shang + ", " + t);
		}
		System.out.println(t);
	}

}
