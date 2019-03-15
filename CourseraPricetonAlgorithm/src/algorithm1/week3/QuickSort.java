package algorithm1.week3;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
	
	public QuickSort(int[] a) {
		
	}
	
	public static void sort(int[] a) {
		// 打乱是必要的，能保证性能
		StdRandom.shuffle(a);
		// 从partition返回的是 partition element的坐标
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(int[] a, int lo, int hi) {
		if (lo >= hi) return;
		int mid = partition(a, lo, hi);
		sort(a, lo, mid-1);
		sort(a, mid+1, hi);
	}
	
	private static int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		// 
		while (true) {
			while (a[++i] < a[lo]) {
				if (i == hi) break;
			}
			while (a[--j] > a[lo]) {
				if (j == lo) break;
			}
			if (i >= j) break;
			exch(a, i, j);
		}
		// 到这里i=j,需要交换 lo和 j的位置
		exch(a, lo, j);
		return j;
	}
	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {3, 2, 4, 1, 5, 10, 4, 6, 3, 7};
		QuickSort.sort(a);
		System.out.println(Arrays.toString(a));
	}

}
