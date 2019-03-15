package algorithm1.week1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
	private boolean[][] grid;
	final private WeightedQuickUnionUF uf;
	private int numberOfOpenSites = 0;
	
	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		if (n <= 0) {
			throw new IllegalArgumentException("n must >= 1");
		}
		
		//初始化grid,uf
		grid = new boolean[n][n];
		//需要两个虚拟节点 site(row,col)---grid[row-1][col-1]----uf((col-1)*n+row)
		uf = new WeightedQuickUnionUF(n*n+2);
		//将top连通，将bottom连通
		for (int i = 1; i<=n; i++) {
			uf.union(0, i);
			uf.union(n * n + 1, n * (n - 1) + i);
		}
	}
	
	public void open(int row, int col)    // open site (row, col) if it is not open already
	{
		int n = grid.length;
		if (row <= 0 || col <= 0 || row > n || row > n) {
			throw new IllegalArgumentException();
		}
		if (!isOpen(row, col)) {
			grid[row-1][col-1] = true;
			numberOfOpenSites++;
			// System.out.println("open site (" +row+", "+col+"); number of open sites: "+ numberOfOpenSites );
			if (row-1 > 0 && isOpen(row-1, col)) uf.union(ufIndx(row, col), ufIndx(row-1, col));
			if (row+1 < n+1 && isOpen(row+1, col)) uf.union(ufIndx(row, col), ufIndx(row+1, col));
			if (col-1 > 0 && isOpen(row, col-1)) uf.union(ufIndx(row, col), ufIndx(row, col-1));
			if (col+1 < n+1 && isOpen(row, col+1)) uf.union(ufIndx(row, col), ufIndx(row, col+1));
		}
		
	}
	
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		int n = grid.length;
		if (row <= 0 || col <= 0 || row > n || row > n) {
			throw new IllegalArgumentException();
		}
		return grid[row-1][col-1];
	}
	
	private int ufIndx(int row, int col) {
		return grid.length * (row - 1) + col;
	}
	
	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		int n = grid.length;
		if (row <= 0 || col <= 0 || row > n || row > n) {
			throw new IllegalArgumentException();
		}
		boolean flag = false;
		if (isOpen(row, col) && uf.connected(0, ufIndx(row, col))) {
			flag = true;
		}
		return flag;
	}
	
	public int numberOfOpenSites()       // number of open sites
	{
		return numberOfOpenSites;
	}
	
	public boolean percolates()              // does the system percolate?
	{
		int n = grid.length;
		boolean flag = false;
		if (uf.connected(0, n * n + 1)) {
			flag = true;
		}
		return flag;
	}
	
	public static void main(String[] args)   // test client (optional)
	{
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(1, 2);
		System.out.println(p.numberOfOpenSites());
		p.open(4, 1);
		p.open(2, 2);
		System.out.println(p.percolates());
		//p.open(2, 1);
		p.open(2,3);
		p.open(3, 2);
		p.open(3, 1);
		System.out.println(p.numberOfOpenSites());
		System.out.println(p.percolates());
	}
}
