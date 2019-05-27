package May15th.niukemoni.second;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] a = new int[3];
		int[] b = new int[3];
		int sumA = 0;
		int sumB = 0;
		int[] NumOfLeftCards = new int[14];
		Arrays.fill(NumOfLeftCards, 4);
		for (int i = 0; i < 3; i++) {
			a[i] = sc.nextInt();
			sumA += a[i];
			NumOfLeftCards[a[i]]--;
		}
		for (int i = 0; i < 3; i++) {
			b[i] = sc.nextInt();
			sumB += b[i];
			NumOfLeftCards[b[i]]--;
		}
		// 一共有多少种情况,52- 6 = 46, 46 * 45 , 也就剩这么多情况
		int[] moreNum = new int[14];
		moreNum[0] = 46;
		for (int i = 1; i < 14; i++) {
			moreNum[i] = moreNum[i - 1] - NumOfLeftCards[i];
		}
		int winNum = 0;
		for (int i = 1; i < 14; i++) { 
			if (NumOfLeftCards[i] > 0) {
				int sumBB = sumB + i;
				int limit = sumBB - sumA;
				if (limit < 0) { limit = 0; }
				if (limit > 13) { limit = 13; }
				int num = i > limit? moreNum[limit] - 1:moreNum[limit];
				winNum += NumOfLeftCards[i] * num;
			}
		}
		System.out.printf("%.4f", winNum / 46.0 / 45);
	}

}
