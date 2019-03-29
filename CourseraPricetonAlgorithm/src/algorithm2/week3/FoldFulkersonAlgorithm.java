package algorithm2.week3;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.Queue;

/**
 * Augmenting path. Find an undirected path from s to t such that:
・Can increase flow on forward edges (not full).
・Can decrease flow on backward edge (not empty).

该算法解决maxflow和mincut的问题
需要判断是否有augmentingpath
需要的辅助数组有

 * @author GaoYuan
 *
 */
public class FoldFulkersonAlgorithm {
	private FlowNetwork g;
	private FlowEdge[] edgeTo;
	private double flowValue;
	
	public FoldFulkersonAlgorithm(FlowNetwork g, int s, int t) {
		this.g = g;
		setFlowValue(0.0);
		// hasAugmentingPath更新egdeTo，顺着t可以找到s
		while(!hasAugmentingPath(s, t)) {
			//第一个错误 要用正无穷，而不是最大值
			double bottle = Double.POSITIVE_INFINITY;
			// 遍历一遍所有的点，找出bottle
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				//查询每个点的剩余能力，如果比瓶颈小则赋值给他
				if (edgeTo[v].residualCapacityTo(v) < bottle) {
					bottle = edgeTo[v].residualCapacityTo(v);
				}
			}
			//再遍历所有的点，flow全部加上bottle
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}
			// 更新flow
			setFlowValue(getFlowValue() + bottle);
			
			//当没有AugmentingPath时，跳出循环，此时的flow即为maxflow
		}
	}
	
	private boolean hasAugmentingPath(int s, int t) {
		// BFS从s开始， 能走的路是
		// v-w,如果residualCapcityTo(w)为0，说明不是AugmentingPath
		// 有可能有很多条，但是我们赋值给最后一条
		edgeTo = new FlowEdge[g.V()];
		boolean[] marked = new boolean[g.V()];
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		//第二个错误，少了s的
		marked[s] = true;
		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (FlowEdge e: g.adj(v)) {
				int w = e.other(v);
				if (!marked[w] && e.residualCapacityTo(w) > 0) {
					marked[w] = true;
					q.enqueue(w);
					// 这里错了！！是指向w的边是e，，edgeTo[v] = e;
					edgeTo[w] = e;
				}
			}
		}
//		if (marked[t]) return true;
//		return false;
		// 直接return就行
		return marked[t];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("" + 2 + 2);
		System.out.println(2 + 2);
	}

	public double getFlowValue() {
		return flowValue;
	}

	public void setFlowValue(double flowValue) {
		this.flowValue = flowValue;
	}

}
