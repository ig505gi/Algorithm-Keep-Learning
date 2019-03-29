package leetCode.dynamicProgam;
/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:
 * @author GaoYuan
 * 以下大错特错！！！不删除了。。
 * ----------------------------------------------------------------
 * 对于 对于与一个 n 的树 >=3， 
 * n节点所在位置有可能在根，有可能在叶子，有可能在中间
 * 记为N(n) = Nroot + Nleaf + Nmid
 * 当加入n+1时，n+1可以加在根上，可以加在叶子上，还可以代替n加在中间，
 * 那么，对于n+1的树，
 * Nroot(n+1) = N(n) ; Nleaf = N(n); 
 * Nmid(n+1) = Nleaf(n) + Nmid(n)x2
 * 理解，加在中间，可以替换n当叶子，那么 n只能作为n+1的叶子，
 * 可以替换Nmid(n)这么多子树的 n，然后n既可以做叶子，也可以做跟，所以乘2
 * ----------------------------------------------------------------
 * 
 * BST概念混淆，
 */
public class UniqueBinarySearchTrees {
	/**
	 * 错误的代码
	 */
	public int numTreesWrong(int n) {
		if (n == 1) return 1;
		// 为了方便，index从1开始，0不做任何作用
		// 为了节省空间，root和leaf的个数一样，用一个数组表示
		int[] nRootOrLeaf = new int[n + 1];
		int[] nMid = new int[n + 1];
		// 因为少用了一个数组，直接从2赋初始值
		// 如果用三个数组，前两个必然相等，而n=1时不相等
		// 当n=1是，1+0+0=1，n=2时1+1+0=2
		nRootOrLeaf[2] = 1;
		// 为了计算快，乘2用两个相加代替
		for (int i = 3; i < n + 1; i++) {
			nRootOrLeaf[i] = nRootOrLeaf[i - 1] + nRootOrLeaf[i - 1] + nMid[i - 1];
			nMid[i] = nRootOrLeaf[i - 1] + nMid[i - 1] + nMid[i - 1];
		}
		return nRootOrLeaf[n] + nRootOrLeaf[n] + nMid[n];
	}
	/**
	 * 正确的代码，看评论区。。但是应该自己也会的
	 * 为了取得独一无二，我们把根定为从1，2。。。
	 * 因为BST根的左边一定小于根，右边一定大于根。。就很轻松看出规律了！！
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees.
	   Memory Usage: 31.7 MB, less than 100.00% of Java online submissions for Unique Binary Search Trees.
	 * @param args
	 */
	public int numTrees(int n) {
		if (n == 0 || n== 1) return 1;
		int[] N = new int[n + 1];
		N[0] = 1; N[1] = 1;
		for (int i = 2; i < n + 1; i++) {
			// 轮流作为根
			for (int j = 1; j <= i; j++) {
				N[i] += (N[j-1] * N[i - j]);
			}
		}
		return N[n];
	}
	
	public static void main(String[] args) {
		UniqueBinarySearchTrees solution = new UniqueBinarySearchTrees();
		System.out.println(solution.numTrees(10));
	}

}
