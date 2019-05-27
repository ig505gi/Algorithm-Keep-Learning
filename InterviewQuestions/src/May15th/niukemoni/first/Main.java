package May15th.niukemoni.first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		//ArrayList<Integer>[] xIndx = (ArrayList<Integer>[]) new Object[1001];
		// 输入
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (!map.containsKey(x))  map.put(x, new ArrayList<Integer>());
			map.get(x).add(y);
		}
		for (int key: map.keySet()) {
			Collections.sort(map.get(key));
		}
		// x为index，排好序的y,直接用二分查找就能找到上下界
		int m = sc.nextInt();
		int[] res = new int[m];
		for (int i = 0; i < m; i++) {
			int sum = 0;
			// 得到a和b
			int xmin = sc.nextInt();
			int ymin = sc.nextInt();
			int xmax = sc.nextInt();
			int ymax = sc.nextInt();
			// 计算sum
			for (int x = xmin; x <= xmax; x++) {
				if (map.containsKey(x)) {
					int minIndx = Collections.binarySearch(map.get(x), ymin);
					if (minIndx < 0) minIndx = 1 - minIndx;
					int maxIndx = Collections.binarySearch(map.get(x), ymax);
					if (maxIndx < 0) maxIndx = -maxIndx;
					sum += (maxIndx - minIndx + 1);
				}
			}
			//保存
			res[i] = sum;
		}
		for (int i = 0; i < m; i++) {
			System.out.println(res[i]);
		}
	}

}
