package algorithm2.week1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

public class BreadthFirstSearch {
	private Graph graph;
	private int v;
	private int[] edgeTo;
	private boolean[] visited;
	private int[] distTo;
	
	public BreadthFirstSearch(Graph g, int v) {
		this.graph = g;
		this.v = v;
		this.edgeTo = new int[g.V()];
		this.visited = new boolean[g.V()];
		this.distTo = new int[g.V()];	
	}
	// 自己写的版本
	public void bfs1() {
		Queue<Integer> q = new Queue<Integer>();
		visited[v] = true;
		q.enqueue(v);	
		while(!q.isEmpty()) {
			int visit = q.dequeue();
			if (visited[visit]) continue;
			// 将连接的都加入
			for (int v: graph.adj(visit)) {
				q.enqueue(v);
				edgeTo[v] = visit;
				distTo[v] = distTo[visit] + 1;
			}
			visited[visit] = true;
		}		
	}
	
	// 课程里的版本	
	private void bfs2() {
		Queue<Integer> q = new Queue<Integer>();
		visited[v] = true;
		q.enqueue(v);
		while (!q.isEmpty()) {
			int visit = q.dequeue();
			// 将连接的都加入
			for (int v : graph.adj(visit)) {
				if (!visited[v]) {
					q.enqueue(v);
					visited[v] = true;
					edgeTo[v] = visit;
					distTo[v] = distTo[visit] + 1;
				}
			}

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
