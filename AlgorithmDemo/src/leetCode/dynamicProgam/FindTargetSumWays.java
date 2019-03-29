package leetCode.dynamicProgam;
/**
 *  You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

 * @author GaoYuan
 *
 *分析：先不说动态规划，如果全部算完，可以用递归，类似一个二叉树，最后的叶子节点是和，查看和是否是target即可
 */
public class FindTargetSumWays {
	/**
	 * 二叉树BFS遍历
	 * Runtime: 383 ms, faster than 35.88% of Java online submissions for Target Sum.
	   Memory Usage: 35.9 MB, less than 95.86% of Java online submissions for Target Sum.
	 */
	public int findTargetSumWays1(int[] nums, int S) {
		return bfs(nums, 0, 0, S);
	}
	private int bfs(int[] nums, int index, int sumPre, int S) {
		if (index == nums.length) {
			if (sumPre == S) {return 1;}
			else return 0;
		}
		return bfs(nums, index + 1, sumPre + nums[index], S) +
				bfs(nums, index + 1, sumPre - nums[index], S);
	}
	/**
	 * F(s) = F(s - nums[length-1]) + F(s + nums[length-1])
	 * 这样应该会少很多重复计算？ 感觉和上面的一样。。
	 * Runtime: 335 ms, faster than 44.14% of Java online submissions for Target Sum.
Memory Usage: 35.9 MB, less than 95.86% of Java online submissions for Target Sum.
	 */
	public int findTargetSumWays2(int[] nums, int S) {
		return recursion(nums, nums.length - 1, S);
	}
	
	private int recursion(int[] nums, int index, int S) {
		if (index < 0) {
			if (S == 0) return 1;
			else return 0;
		}
		return recursion(nums, index - 1, S - nums[index]) + 
				recursion(nums, index - 1, S + nums[index]);
	} 
	/**
	 * dp解法 来自评论区
	 * 这确实很dp，不重复计算，把每次的结果想方设法保存下来，学到了，怎么建立大的空间去保存
	 * 把所有有可能的结果当成数组去保存，虽然没有的很多，但是空间换取了时间
	 * Runtime: 5 ms, faster than 99.47% of Java online submissions for Target Sum.
	   Memory Usage: 37.5 MB, less than 58.62% of Java online submissions for Target Sum.
	 */
	public int findTargetSumWays3(int[] nums, int s) {
		int sum = 0; 
	    for(int i: nums) sum+=i;
	    if(s>sum || s<-sum) return 0;
	    // sumvalue = index - sum;
	    int[] dp = new int[2*sum+1];
	    // 这里表明目前s为0的情况有一种
	    dp[0+sum] = 1;
	    for(int i = 0; i<nums.length; i++){
	        int[] next = new int[2*sum+1];
	        for(int k = 0; k<2*sum+1; k++){
	            if(dp[k]!=0){
	            	// 一开始只有sum=0有1种情况，遍历第一个点的时候， 就有两种了，
	            	// sum +- nums[i]作为index，保存下来
	                next[k + nums[i]] += dp[k];
	                next[k - nums[i]] += dp[k];
	            }
	        }
	        dp = next;
	    }
	    return dp[sum+s];
	} 
	static void main(String[] args) {
		FindTargetSumWays solution = new FindTargetSumWays();
		int[] nums = {1, 1, 1, 1, 1};
		System.out.println(solution.findTargetSumWays2(nums, 3));
	}

}
