package algorithm1.week4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class HeapSort {

	public HeapSort() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public static void sort(int[] a) {
		//
		int N = a.length;
		//1. 先变成priority queue
		for (int k = N/2; k >= 1; k--) {
			sink(a, k, N);
			StdOut.println(Arrays.toString(a));
		}
		
		//2. 把最顶的放在最后，N-1,sink(1)
		while (N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
			StdOut.println(Arrays.toString(a));
		}
	}
	
	private static void sink(int[]a, int k, int N) {
		// 把孩子中最大的挑出来，变成父亲，直到没有孩子或者孩子都比父亲小
		while (k * 2 <= N) {
			int j = k * 2;
			if (j + 1 <= N && less(a, j, j + 1)) j++;
			if (less(a, j, k)) break;
			exch(a, j, k);
			k = j;
		}
	}
	
	private static void exch(int[] a, int i, int j) {
		int temp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = temp;
	}
	
	private static boolean less(int[] a, int i, int j) {
		return a[i-1] - a[j-1] < 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {114,2451,236,151,2462,141,23462,1341,152,141,11,151,125};
		HeapSort.sort(a);
	}

}
