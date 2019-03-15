package algorithm1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] s;
	private int index; // from s(index), always null
	private int N; // not null, s++
	@SuppressWarnings("unchecked")
	public RandomizedQueue() // construct an empty randomized queue
	{
		s = (Item[]) new Object[1];
		s[0] = null;
		index = 0;
		N = 0;
	}

	public boolean isEmpty() // is the randomized queue empty?
	{
		return N == 0;
	}

	public int size() // return the number of items on the randomized queue
	{
		return N;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int length) {
		Item[] copy = (Item[]) new Object[length];
		int newIndx = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				copy[newIndx] = s[i];
				newIndx++;
			}
		}
		s = copy;
	}
	public void enqueue(Item item) // add the item
	{
		if (item == null) { throw new IllegalArgumentException(); }
		if (index >= s.length) {
			resize(s.length * 2);
			index = N;
		}
		s[index++] = item;
		N++;
		// System.out.println("当前数组：" + Arrays.toString(s) + "; index = " + index + "; N = " + N + "; 数组长度：" + s.length);
	}

	public Item dequeue() // remove and return a random item
	{
		if (N == 0) { throw new NoSuchElementException(); }
		int removeIndex;
		Item item;
		while (true) {
			removeIndex = StdRandom.uniform(s.length);
			if (s[removeIndex] != null) {
				item = s[removeIndex];
				s[removeIndex] = null;
				N--;
				if (N > 0 && N <= s.length/4 ) {
					resize(s.length / 2);
					index = N;
				}
				break;
			}
		}
		// System.out.println("当前数组：" + Arrays.toString(s) + "; index = " + index + "; N = " + N + "; 数组长度：" + s.length);
		return item;
	}

	public Item sample() // return a random item (but do not remove it)
	{
		if (N == 0) { throw new NoSuchElementException(); }
		int sampleIndex;
		Item item;
		while (true) {
			sampleIndex = StdRandom.uniform(s.length);
			if (s[sampleIndex] != null) {
				item = s[sampleIndex];
				break;
			}
		}
		return item;
	}
	/*
	 * 初始化算法，生成一个和queue大小一样的数组，null 对应0，有item则为1
	 * 从中选出一个后，将其1-->0
	 * 直到全部选出
	 * @see java.lang.Iterable#iterator()
	 * 
	 * 更好的解决办法，随机选一个index 然后将末尾替换即可。。
	 */
	public Iterator<Item> iterator() // return an independent iterator over items in random order
	{
		return new RQIterator();
	}
	
	private class RQIterator implements Iterator<Item> {
		private int[] freq;
		private int x;
		
		public RQIterator() {
			freq = new int[s.length];
			x = N;
			for (int i = 0; i < freq.length; i++) {
				if (s[i] != null) { freq[i] = 1; }
			}
		}
		@Override
		public boolean hasNext() {
			return x != 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) { throw new NoSuchElementException(); }
			Item item;
			int index = StdRandom.discrete(freq); 
			item = s[index];
			freq[index] = 0;
			x--;
			// System.out.println("freq[]= " + Arrays.toString(freq) + "; x = " + x);
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) // unit testing (optional)
	{
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		System.out.println(rq.isEmpty());
		// System.out.println(rq.dequeue());
		// System.out.println(rq.sample());
		rq.enqueue("A");
		rq.enqueue("B");
		System.out.println(rq.isEmpty());
		rq.enqueue("C");
		rq.enqueue("D");
		rq.enqueue("Z");
		rq.enqueue("Y");
		rq.enqueue("X");
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		rq.enqueue("E");
		System.out.println(rq.dequeue());
		rq.enqueue("W");
		System.out.println(rq.dequeue());
		System.out.println(rq.sample());
		System.out.println(rq.sample());
		
		for (String s: rq) {
			System.out.println(s);
		}
	}

}
