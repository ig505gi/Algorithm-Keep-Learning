package April13th.JD.first;

import java.util.*;
/**
 * 先找到与1相连的所有子树
 * 叶子节点到达
 * @author GaoYuan
 *思路又错了，找出子树的思路对了，但是只需要统计子树的个数就行，每秒出一个，有几个就几秒
原版这个，如果不按照从上到下的顺序连接树  就会出错
 */

public class Main {
	
	/*
	 * 正确思路，但是没被验证
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] father = new int[n + 1];
		
		// 记录每棵树的father
		for (int i = 1; i < n; i++) {
			int c = sc.nextInt();
			int f = sc.nextInt();
			father[c] = f;
		}
		sc.close();
		// 创建子树的时间map，key是子树的根index，val是子树大小
		HashMap<Integer, Integer> times = new HashMap<Integer, Integer>();
		for (int i = 2; i < n + 1; i++) {
			if (father[i] == 1) {
				times.put(i, 1);
			}
		}
		
		for (int i = 2; i < n + 1; i++) {
			if (father[i] != 1) {
				int ancestor = father[i];
				while (!times.containsKey(ancestor)) {
					ancestor = father[ancestor];
				}
				times.replace(ancestor, times.get(ancestor) + 1);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int sub: times.keySet()) {
			int time = times.get(sub);
			max = Math.max(time, max);
		}
		System.out.println(max);
	}
	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
//		int[] father = new int[n + 1];
//		int[] depthToOne = new int[n + 1];
//		int[] numOfChild = new int[n + 1];
//		Arrays.fill(depthToOne, Integer.MAX_VALUE);
//		depthToOne[1] = -1;
//		for (int i = 1; i < n; i++) {
//			int f = sc.nextInt();
//			int c = sc.nextInt();
//			if (depthToOne[f] > depthToOne[c]) {
//				int temp = f;
//				f = c;
//				c = temp;
//			}
//			father[c] = f;
//			numOfChild[f]++;
//			depthToOne[c] = depthToOne[f] + 1;
//		}
//		sc.close();
//		HashMap<Integer, Integer> times = new HashMap<Integer, Integer>();
//		for (int i = 2; i < n + 1; i++) {
//			if (father[i] == 1) {
//				times.put(i, 1);
//			}
//		}
//		
//		for (int i = 2; i < n + 1; i++) {
//			if (numOfChild[i] == 0 && father[i] != 1) {
//				int ancestor = father[i];
//				while (!times.containsKey(ancestor)) {
//					ancestor = father[ancestor];
//				}
//				times.replace(ancestor, times.get(ancestor) + depthToOne[i]);
//			}
//		}
//		int max = Integer.MIN_VALUE;
//		for (int sub: times.keySet()) {
//			int time = times.get(sub);
//			max = Math.max(time, max);
//		}
//		System.out.println(max);
//	}
}