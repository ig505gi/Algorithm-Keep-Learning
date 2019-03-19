package algorithm2.week1;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Shortest ancestral path. An ancestral path between two vertices v and w in a
 * digraph is a directed path from v to a common ancestor x, together with a
 * directed path from w to the same ancestor x. A shortest ancestral path is an
 * ancestral path of minimum total length. We refer to the common ancestor in a
 * shortest ancestral path as a shortest common ancestor. Note also that an
 * ancestral path is a path, but not a directed path.
 * 
 * @author GaoYuan All methods (and the constructor) should take time at most
 *         proportional to E + V in the worst case
 */
public final class SAP {
	private final Digraph g;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		if (G == null)
			throw new IllegalArgumentException();
		Digraph g = new Digraph(G);
		this.g = g;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		/* 自己写的bfs 只能用于一个点
		if (v >= g.V() || w >= g.V() || v < 0 || w < 0)
			throw new IllegalArgumentException();
		BFS bfsV = new BFS(g, v);
		BFS bfsW = new BFS(g, w);
		int length = Integer.MAX_VALUE;
		for (int i = 0; i < g.V(); i++) {
			if (!bfsV.visited(i) || !bfsW.visited(i))
				continue;
			if (bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
			}
		}
		if (length == Integer.MAX_VALUE)
			return -1;
		return length;
		*/
		if (v >= g.V() || w >= g.V() || v < 0 || w < 0)
			throw new IllegalArgumentException();
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(g, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(g, w);
		int length = Integer.MAX_VALUE; 
		for (int i = 0; i < g.V(); i++) { 
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i) && bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
			}
		}
		if (length == Integer.MAX_VALUE)
			return -1;
		return length;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {
		/*
		if (v >= g.V() || w >= g.V() || v < 0 || w < 0)
			throw new IllegalArgumentException();
		BFS bfsV = new BFS(g, v);
		BFS bfsW = new BFS(g, w);
		int length = Integer.MAX_VALUE;
		int ancestor = -1;
		for (int i = 0; i < g.V(); i++) {
			if (!bfsV.visited(i) || !bfsW.visited(i))
				continue;
			if (bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
				ancestor = i;
			}
		}
		if (length == Integer.MAX_VALUE)
			return -1;
		return ancestor;
		*/
		if (v >= g.V() || w >= g.V() || v < 0 || w < 0)
			throw new IllegalArgumentException();
		int ancestor = -1;
		int length = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(g, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(g, w);
		for (int i = 0; i < g.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i) && bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
				ancestor = i;
			}
		}
		return ancestor;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (!validate(v) || !validate(w))
			throw new IllegalArgumentException();
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(g, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(g, w);
		int length = Integer.MAX_VALUE; 
		for (int i = 0; i < g.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i) && bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
			}
		}
		if (length == Integer.MAX_VALUE)
			return -1;
		return length;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such
	// path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (!validate(v) || !validate(w))
			throw new IllegalArgumentException();
		int ancestor = -1;
		int length = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(g, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(g, w);
		for (int i = 0; i < g.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i) && bfsV.distTo(i) + bfsW.distTo(i) < length) {
				length = bfsV.distTo(i) + bfsW.distTo(i);
				ancestor = i;
			}
		}
		return ancestor;
	}
	
	private boolean validate(Iterable<Integer> v) {
		if (v == null) return false;
		for (Integer v0: v) {
			if (v0 == null) return false;
		}
		return true;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> a = new Queue<Integer>();
		Queue<Integer> b = new Queue<Integer>();
		a.enqueue(7);
		a.enqueue(4);
		b.enqueue(12);
		b.enqueue(2);
		StdOut.printf("length = %d, ancestor = %d\n", sap.length(a, b), sap.ancestor(a, b));
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
