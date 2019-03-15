package algorithm1.week4.eightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	private final static int BLANK = 0;
	private int n;
	private int[][] blocks;

	/*
	 * construct a board from an n-by-n array of blocks (where blocks[i][j] = block
	 * in row i, column j)
	 */
	public Board(int[][] blocks) {
		if (blocks == null)
			throw new IllegalArgumentException();
		if (blocks.length < 2)
			throw new IllegalArgumentException("dimension is too small!");
		// 复制数组，不要对原数组操作，从博客学习到的，觉得很正确，目前没遇到，目测以后会遇到坑
		this.n = blocks.length;
		int[][] copyblock = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copyblock[i][j] = blocks[i][j];
			}
		}
		this.blocks = copyblock;
	}

	public int dimension() // board dimension n
	{
		if (blocks == null)
			return 0;
		return n;
	}

	private int goalValue(int row, int col) {
		if (col == n - 1 && row == n - 1)
			return BLANK;
		return row * n + col + 1;
	}

	public int hamming() // number of blocks out of place
	{
		int numOfOutPlace = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 0不能算错位。。
				if (blocks[i][j] != BLANK && blocks[i][j] != goalValue(i, j))
					numOfOutPlace++;
			}
		}
		return numOfOutPlace;
	}

	public int manhattan() // sum of Manhattan distances between blocks and goal
	{
		int totalDistance = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 计算每个点的曼哈顿距离。。不能算上0
				if (blocks[i][j] != BLANK) {
					int distance = Math.abs(i - goalRow(blocks[i][j])) + Math.abs(j - goalCol(blocks[i][j]));
					totalDistance += distance;
				}
			}
		}
		return totalDistance;
	}

	private int goalRow(int value) {
		if (value == BLANK)
			return n - 1;
		return (value - 1) / n;
	}

	private int goalCol(int value) {
		if (value == BLANK)
			return n - 1;
		return (value - 1) % n;
	}

	public boolean isGoal() // is this board the goal board?
	{
		if (hamming() == 0)
			return true;
		return false;
	}

	public Board twin() // a board that is obtained by exchanging any pair of blocks
	{
		// 就是随机交换两个不是空白的,方便就是从左上开始，交换前两个不是空的block
		Board twinBoard = new Board(blocks);
		int firRow = 0;
		int firCol = 0;
		if (blocks[firRow][firCol] == BLANK)
			firCol++;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (blocks[row][col] != blocks[firRow][firCol] && blocks[row][col] != BLANK) {
					twinBoard.swap(firRow, firCol, row, col);
					return twinBoard;
				}
			}
		}
		return twinBoard;
	}

	private void swap(int row1, int col1, int row2, int col2) {
		int temp = blocks[row1][col1];
		blocks[row1][col1] = blocks[row2][col2];
		blocks[row2][col2] = temp;
	}

	public boolean equals(Object y) // does this board equal y?
	{
		// 这里一开始比较输出的字符串，简化代码，但是速度不一定快
		// 修改代码，一个一个block进行比较
		if (y == null || y.getClass() != Board.class) return false;
		Board that = (Board) y;
		if (this.n != that.n) return false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] != that.blocks[i][j])
					return false;
			}
		}
		return true;
	}

	public Iterable<Board> neighbors() // all neighboring boards
	{
		Queue<Board> neighbors = new Queue<Board>();
		int iBlank = Integer.MAX_VALUE;
		int jBlank = Integer.MAX_VALUE;
		flag: for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == BLANK) {
					iBlank = i;
					jBlank = j;
					break flag;
				}
			}
		}
		// 写到这里知道为什么构造函数要复制数组了
		// >=0 这里bug最终发现了，导致neighbor少了许多许多！
		if (iBlank + 1 < n && iBlank + 1 >= 0) {
			Board neighborL = new Board(blocks);
			neighborL.swap(iBlank, jBlank, iBlank + 1, jBlank);
			neighbors.enqueue(neighborL);
		}
		if (iBlank - 1 < n && iBlank - 1 >= 0) {
			Board neighborR = new Board(blocks);
			neighborR.swap(iBlank, jBlank, iBlank - 1, jBlank);
			neighbors.enqueue(neighborR);
		}
		if (jBlank + 1 < n && jBlank + 1 >= 0) {
			Board neighborD = new Board(blocks);
			neighborD.swap(iBlank, jBlank, iBlank, jBlank + 1);
			neighbors.enqueue(neighborD);
		}
		if (jBlank - 1 < n && jBlank - 1 >= 0) {
			Board neighborU = new Board(blocks);
			neighborU.swap(iBlank, jBlank, iBlank, jBlank - 1);
			neighbors.enqueue(neighborU);
		}

		return neighbors;
	}

	public String toString() // string representation of this board (in the output format specified below)
	{
		if (dimension() == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		sb.append(dimension()).append("\n");
		// 对齐了 先设置为2
		// 看了个作业说2可以通过系统校验，实际n<128 测试的时候用6
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(String.format("%2d ", blocks[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();

	}

	public static void main(String[] args) {
		/*
		 * int n = 4; int[][] b = new int[n][n]; for (int i = 0; i < n; i++) { for (int
		 * j = 0; j < n; j++) { b[i][j] = i * n + j + 1; } } b[n-1][n-1] = 0; Board
		 * board1 = new Board(b);
		 */
		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board board = new Board(blocks);
		// board = board.twin();
		StdOut.println(board);
		StdOut.println(board.isGoal());
		StdOut.println(board.dimension());
		StdOut.println(board.manhattan());
		StdOut.println(board.hamming());
		StdOut.println(board.twin());
		StdOut.println(board.equals(board.twin()));
		for (Board nei : board.neighbors()) {
			System.out.println(nei);
		}
	}

}
