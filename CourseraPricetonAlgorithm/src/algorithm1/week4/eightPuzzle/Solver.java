package algorithm1.week4.eightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.需要记住前驱 2.比较neighbors的优先级 3.任意换两个非空的block， 两个相对的board，一个可解，一个不可解
 * 4.参照3，同时解twin和原board，可以得出结论是否可解
 * 
 * @author admin
 *
 */
public class Solver {
	private Node currentNode;
	private Node twinNode;

	private class Node implements Comparable<Node> {
		private int moves;
		private int priority;
		private Board current;
		private Node parent;

		// 构造node的时候 只需要传入current和parent，其他参数即可计算得到
		// 注意initial没有parent，空指针异常
		public Node(Board current, Node parent) {
			this.current = current;
			this.parent = parent;
			if (parent == null)
				this.moves = 0;
			else
				this.moves = parent.moves + 1;
			this.priority = current.manhattan() + this.moves;
		}

		@Override
		public int compareTo(Node that) {
			if (that == null)
				throw new IllegalArgumentException();
			return this.priority - that.priority;
		}
	}
	/*
	 * 没必要递归！ 直接循环即可
	 */
	public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)
	{
		if (initial == null)
			throw new IllegalArgumentException();
		currentNode = new Node(initial, null);
		twinNode = new Node(initial.twin(), null);
		// 同时求解，需要两个MinPQ
		MinPQ<Node> pq1 = new MinPQ<Node>();
		MinPQ<Node> pq2 = new MinPQ<Node>();
		pq1.insert(currentNode);
		pq2.insert(twinNode);
		// 一定有一个解，但是父亲不一定是initial
		while(true) {
			currentNode = pq1.delMin();
			if (currentNode.current.isGoal()) break;
			for (Board neighbor : currentNode.current.neighbors()) {
				if (currentNode.parent == null || !neighbor.equals(currentNode.parent.current)) {
					Node newNode = new Node(neighbor, currentNode);
					pq1.insert(newNode);
				}
			}
			twinNode = pq2.delMin();
			if (twinNode.current.isGoal()) break;
			for (Board neighbor : twinNode.current.neighbors()) {
				if (twinNode.parent == null || !neighbor.equals(twinNode.parent.current)) {
					Node newNode = new Node(neighbor, twinNode);
					pq2.insert(newNode);
				}
			}
		}

	}
	/*
	// 返回的是有结果的node，再向上追寻，如果最终的父亲是tiwn则无解
	private Node search(MinPQ<Node> pq) {
		if (pq.min().current.isGoal())
			return pq.min();
		// if (pq2.min().current.isGoal())
		//	return pq2.min();
		// 没有解，就继续迭代，并且要删除这个要查询的节点
		Node parent1 = pq.delMin();
		// Node parent2 = pq2.delMin();
		// 注意处理空指针，有可能没有父亲的父亲(原代码需要处理)
		// 原代码：parent1.parent == null || !neighbor.equals(parent1.parent.current)
		// 如果为 null不进行第二个判断，所有邻居一定保留，如果不为null，才进行二个判断，
		// bug:还是有可能会和祖爷爷相同，因此需要遍历parent，保证和以上的全部不同才行
		// 添加了 equalsPre()
		for (Board neighbor : parent1.current.neighbors()) {
			if (!equalsPre(neighbor, parent1)) {
				pq.insert(new Node(neighbor, parent1));
			}
		}
		for (Board neighbor : parent2.current.neighbors()) {
			if (!equalsPre(neighbor, parent2)) {
				pq2.insert(new Node(neighbor, parent2));
			}
		}
		return search(pq);
	}
	*/

	// 用board比较所有的parent，如果有相同的就返回true
	// 保证不走重复的
	/*
	private boolean equalsPre(Board board, Node parent) {
		boolean isEqual = false;
		for (Node temp = parent; temp != null; temp = temp.parent) {
			// 应该从爷爷开始比较，这里直接比较了父亲，简化代码，不然在函数外还要判断是否有爷爷
			if (board.equals(temp.current)) {
				isEqual = true;
				break;
			}
		}
		return isEqual;
	}
	*/

	public boolean isSolvable() // is the initial board solvable?
	{
		if (currentNode == null) return false;
		if (currentNode.current.isGoal()) {
			return true;
		} else {
			return false;
		}
		
	}

	public int moves() // min number of moves to solve initial board; -1 if unsolvable
	{
		if (!isSolvable())
			return -1;
		return currentNode.moves;
	}

	public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable
	{
		if (!isSolvable())
			return null;
		// 用栈来寸parent，然后返回栈即可
		Stack<Board> result = new Stack<Board>();
		result.push(currentNode.current);
		for (Node temp = currentNode; temp.parent != null; temp = temp.parent)
			result.push(temp.parent.current);
		return result;
	}

	public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}

	}

}
