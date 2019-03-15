package algorithm1.week1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	final private double CONFIDENCE_95 = 1.96;
	final private double[] result;
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("n and trials must > 0");
		}
		
		result = new double[trials];
		for (int i=0; i<trials; i++) {
			// System.out.println("trial begins, " + (i+1) +" times");
			Percolation p = new Percolation(n);
			int[] openList = StdRandom.permutation(n*n);
			// System.out.println(Arrays.toString(openList));
			int indx = 0;
			while (!p.percolates()) {
				int row = openList[indx] / n + 1;
				int col = openList[indx] % n + 1;
				p.open(row, col);
				indx++;
			}
			result[i] = (float)p.numberOfOpenSites() / n / n;
		    // System.out.println("trial ends, " + (i+1) +" times, the hreshold is " + result[i]);
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
	{
		return StdStats.mean(result);
	}
	
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(result);
	}
	
	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return mean() - CONFIDENCE_95 * stddev() / (Math.sqrt(result.length));
	}
	
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return mean() + CONFIDENCE_95 * stddev() / (Math.sqrt(result.length));
	}
	
    public static void main(String[] args)        // test client (described below)
    {
    	PercolationStats ps = new PercolationStats(200, 100);
    	System.out.println("mean                    = " + ps.mean());
    	System.out.println("stddev                  = " + ps.stddev());
    	System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", "+ ps.confidenceHi() + "]");
    }
    
}
