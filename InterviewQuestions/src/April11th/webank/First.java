package April11th.webank;

import java.util.*;
/**
 * 自己用数组写。。感觉用一个循环链表会好很多。。可能复杂度高
 * 最后通过率只有18%
 * @author GaoYuan
 *
 */
public class First {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();
		int[] cir = new int[n];
		for (int i = 0; i < n; i++) {
			cir[i] = i + 1;
		}
		//
		int nextIndex = 0;
		for (int pos = 0; pos < n - 1; pos++) {
			int livenum = n - pos;
			int right = n - nextIndex;
			int leftIndex;
			if (right > m) {
				leftIndex = pos + m - 1;
			} else {
				int mod = (m - right) % livenum;
				mod = mod == 0? livenum: mod;
				leftIndex = pos + mod - 1;
			}
			nextIndex = leftIndex == n - 1 ? pos:leftIndex + 1;
			System.out.println(pos +" "+leftIndex +" "+ nextIndex);
			if (leftIndex == pos) {
				continue;
			}
			int[] newcir = new int[n];
			for (int i = 0; i < pos; i++) {
				newcir[i] = cir[i];
			}
			newcir[pos] = cir[leftIndex];
			for (int i = pos + 1; i < n; i++) {
				if (i <= leftIndex) {
					newcir[i] = cir[i - 1];
				} else {
					newcir[i] = cir[i];
				}
			}
			cir = newcir;
		}
		for (int i = 0; i < n - 1; i++) {
			System.out.print(cir[i]);
            if (i != n - 2) System.out.print(" ");
		}
		System.out.println();
		System.out.print(cir[n - 1]);
	}

}
