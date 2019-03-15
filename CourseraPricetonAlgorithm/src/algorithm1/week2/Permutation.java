package algorithm1.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		
		for (int k = Integer.parseInt(args[0]); k > 0; k--) {
			StdOut.println(rq.dequeue());
		}
	}

}
