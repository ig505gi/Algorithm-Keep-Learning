package April23th.meituan.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	/*
	 * 如果是黑的节点，看他与多少个白的相连接，
	 * 如果只连接一个白的，不计数，如果多个，就可以去相乘
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] father = new int[n];
		Map<Integer, List<Integer>> children = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i < n ; i++) {
			int p = in.nextInt();
			father[i] = p;
			if (!children.containsKey(p)) {
				children.put(p, new ArrayList<Integer>());
			}
			children.get(p).add(i);
		}
		boolean[] isWhite = new boolean[n];
		for (int i = 0; i < n ; i++) {
			 isWhite[i] = in.nextInt() == 0 ? true: false;
		}
		long re = 1;
		for (int p: children.keySet()) {
			if (!isWhite[p]) {
				int count = 0;
				for (int ch: children.get(p)) {
					if (isWhite[ch]) count++;
				}
				if (p != 0 && isWhite[father[p]]) count++;
				if (count > 1) {
					re *= count;
					// System.out.println("连接白色大于1的节点是：" + p + ", 白色数：" + count);
				}
			}
		}
		System.out.println(re);
	}

}
