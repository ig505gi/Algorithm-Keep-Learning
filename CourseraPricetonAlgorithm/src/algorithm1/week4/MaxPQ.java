package algorithm1.week4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int capability) {
		this.pq = (Key[])new Comparable[capability];
	}
	
	public MaxPQ(Key[] a) {
		
	}

	public void insert(Key key) {
		if (N >= this.pq.length - 1) { throw new IllegalArgumentException("不能插入！已满！"); }
		this.pq[++N] = key;
		swim(N);
		StdOut.println(Arrays.toString(pq) + "; N = " + N);
	}
	
	public Key delMax() {
		if (isEmpty()) { throw new IllegalArgumentException("为空！"); }
		Key key = pq[1];
		exch(1, N);
		pq[N--] = null;
		sink(1);
		StdOut.println(Arrays.toString(pq) + "; N = " + N);
		return key;
	}
	
	public boolean isEmpty() {
		return this.N == 0;
	}
	
	private void swim(int k) {
		//和该节点的父亲对比，，比他大的话就交换
		while (k/2 >= 1 && less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		// 把孩子中最大的挑出来，变成父亲，直到没有孩子或者孩子都比父亲小
		while (k * 2 <= this.N) {
			int j = k * 2;
			if (j + 1 <= this.N && less(j, j + 1)) j++;
			if (less(j, k)) break;
			exch(j, k);
			k = j;
		}
	}
	
	private void exch(int i, int j) {
		Key temp = this.pq[i];
		this.pq[i] = this.pq[j];
		this.pq[j] = temp;
	}
	
	private boolean less(int i, int j) {
		return this.pq[i].compareTo(this.pq[j]) < 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxPQ<Integer> p = new MaxPQ<Integer>(10);
		p.insert(5);
		p.insert(4);
		p.insert(6);
		p.insert(10);
		p.insert(2);
		StdOut.println(p.delMax());
		p.insert(9);
		p.insert(6);
		p.insert(1);
		StdOut.println(p.delMax());
	}

}
