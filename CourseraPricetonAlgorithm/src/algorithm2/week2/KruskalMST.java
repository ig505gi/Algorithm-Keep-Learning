package algorithm2.week2;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 贪婪算法的一种特殊情况
 * 1. 将edge排序
 * 2. 从最小的开始选，如果不产生环，则加入MST
 * 2.1 维持一个UnionFind，如果w和v在一个Union中，则有环
 * 
 * @author GaoYuan
 *
 */
public class KruskalMST {
	private Queue<Edge> mst = new Queue<Edge>();
	
	public KruskalMST(EdgeWeightedGraph g) {
		MinPQ<Edge> minpq = new MinPQ<Edge>();
		for (Edge edge: g.edges()) {
			minpq.insert(edge);
		}
		
		UF uf = new UF(g.V());
		while (!minpq.isEmpty() && mst.size() <= g.V() - 1) {
			Edge e = minpq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.connected(v, w)) continue;
			uf.union(v, w);
			mst.enqueue(e);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
