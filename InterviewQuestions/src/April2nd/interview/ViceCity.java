package April2nd.interview;

import java.util.Arrays;
import java.util.Stack;

/**
 * 微软sample test 第三题
 * 房间0-N个，小偷在J房间，警察在I房间，从I开始，有多少种情况警察能捉到小偷，警察行动满足以下条件：
 * 1. 在k房间前，下一步只能走第 k+1 房间 或者 第（k + J/2）mod J 房间
 * 2. 不能走重复的房间
 * input1： I = 2  input2： J = 4
 * output：2
 * reason： 2—>3->4, 2->0->3->4
 * 读题能力太差。。好几次曲解意思。。。。。
 *最后还是用了dfs来写
 * @author GaoYuan
 *
 */
public class ViceCity {

	public int houses(int input1, int input2) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(input1);
		return dfs(stack, input2);
	}
	private int dfs(Stack<Integer> stack, int target) {
		int k = stack.peek();
		if (k == target) {
			Object[] anArray = stack.toArray();
			System.out.println(Arrays.asList(anArray));
			return 1;
		}
		int res = 0;
		int next = k + 1;
		if (!stack.contains(next)) {
			stack.push(next);
			res += dfs(stack, target);
			stack.pop();
		}
		next = (k + target/2) % target;
		if (next != k + 1 && !stack.contains(next)) {
			stack.push(next);
			res += dfs(stack, target);
			stack.pop();
		}
		return res;
	}
	public static void main(String[] args) {
		ViceCity v = new ViceCity();
		System.out.println(v.houses(2, 4));
	}

}
