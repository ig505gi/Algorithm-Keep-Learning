package April2nd.interview;
/**
 * 微软春招sample test第四题
 * 创造一个序列，要满足的条件有下面几条：
 * 1. S为该序列的sum
 * 2. N为该序列的个数
 * 3. T为间隔，序列中的两个只差必须大于0，小于等于T（因此不能相等，且为递增）
 * 4. 第一个必须小于等于T
 * 5. 为自然数。。。 
 * @author GaoYuan
 *example：S 25，N 5，T 2
 *output：3
 *reason：{1,3,5,7,9}, {2,4,5,6,8}, {2,3,5,7,8}
 *
 *还是想不出来动态规划解法。。。最后写了类似dfs的算法。。
 *分析：F(S, N, T, start) = F(S - (start+1), N - 1, T, start + 1) +... 
 *						+F(S - (start+T), N - 1, T, start + T)
 */
public class IncreasingNumberSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
