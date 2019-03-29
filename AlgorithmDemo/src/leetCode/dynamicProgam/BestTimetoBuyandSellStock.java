package leetCode.dynamicProgam;
/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


 * @author GaoYuan
 *
 */
public class BestTimetoBuyandSellStock {
	public int solution1(int[] prices) {
        int[] max = new int[prices.length];
        for (int i = 0; i < max.length; i++) {
            int buy = prices[i];
            for (int j = i+1; j< max.length; j++) {
                max[j] = Math.max(max[j], prices[j] - buy);
            }
        }
        int res = 0;
        for (int i = 0; i < max.length; i++) {
            if (max[i] > res) { res = max[i]; }
        }
        return res;
    }
	
	/**
	 * 很艰难才写对。。比81%的快，没有用辅助数组，但是用了好几个int，空间超过50%
	 * @param prices
	 * @return
	 */
	public static int solution2(int[] prices) {
		int buyMin = Integer.MAX_VALUE;
		int buyIndex = 0, sellIndex = 0;
		int sellMax = 0, max = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < buyMin) {
				buyMin = prices[i]; 
				buyIndex = i;
				// 这个一开始漏掉了，如果找到了最小的，不能用之前的最大了，要重新搜索后面的
				sellMax = 0;
			}
			if (i > buyIndex && prices[i] > sellMax) {
				sellMax = prices[i]; 
				sellIndex = i;
			} 
			if (sellIndex > buyIndex) {
				// max保存的是之前的最优解，如果遇到更优的，才替换
				max = Math.max(sellMax - buyMin, max);
			}
			System.out.println("buyIndex:" + buyIndex + " sellIndex:" + sellIndex);
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] a = {3,3,5,0,0,3,1,4};
		System.out.println(BestTimetoBuyandSellStock.solution2(a));
	}
}
