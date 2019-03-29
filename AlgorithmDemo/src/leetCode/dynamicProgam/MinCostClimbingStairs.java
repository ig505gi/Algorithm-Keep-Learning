package leetCode.dynamicProgam;
/**
 *  On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:

Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:

Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

 * @author GaoYuan
 *
 *
 * 带上 0 index，到n-1index，我们要跳到 n层，
 * 第一次相当于从-1开始，花费0，选择跳一层或者两层，从0或1开始，
 * 我们要记录到每一层要的最小cost，
 * 
 */
public class MinCostClimbingStairs {
	/**
	 * 一般成功，速度100%
	 * @param cost
	 * @return
	 */
	public static int minCostClimbingStairs(int[] cost) {
		if (cost.length == 0) return 0;
		int[] totalCost = new int[cost.length + 1];
		totalCost[0] = 0;
		totalCost[1] = 0;
		// totalCost[i] = min(totalCost[i-2] + cost[i-2], totalCost[i-1]+cost[i-1])
		for (int i = 2; i < cost.length + 1; i++) {
			totalCost[i] = Math.min(totalCost[i-2] + cost[i-2], totalCost[i-1]+cost[i-1]);
		}		
		return totalCost[cost.length];
    }
	public static void main(String[] args) {
		int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		System.out.println(MinCostClimbingStairs.minCostClimbingStairs(cost));
	}

}
