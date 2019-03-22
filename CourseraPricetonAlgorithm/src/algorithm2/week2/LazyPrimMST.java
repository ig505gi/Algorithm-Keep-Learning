package algorithm2.week2;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 一种特殊的贪婪算法
 * MinPQ维持的是连接到T中所有v的边，按照权值排序
 * 如果v和w都在T中，那么忽略
 * 如果w不在T中，则加入mst
 * @author GaoYuan
 *
 */
public class LazyPrimMST {
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	private boolean[] marked;
	
	public LazyPrimMST(EdgeWeightedGraph g) {
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		marked = new boolean[g.V()];
		visit(g, 0);

		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if (!marked[v]) visit(g, v);
			if (!marked[w]) visit(g, w);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for (Edge e: g.adj(v)) {
			if (!marked[e.other(v)])
				pq.insert(e);
		}
	}
}
