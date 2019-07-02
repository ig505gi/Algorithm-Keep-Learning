package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论区的一个评论：
 * 写递归的技巧是：明白一个函数的作用并相信它能完成这个任务，千万不要跳进这个函数里面企图探究更多细节，否则就会陷入无穷的细节无法自拔。你就算浑身是铁，能压几个栈？

按照前面说的技巧，先来定义清楚每个递归函数应该做的事：
pathSum 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，和为目标值的路径总数。
count 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，能凑出几个以该节点为路径开头，和为目标值的路径总数。
 * @author GaoYuan
 *
 */
public class PathSumIII {
	/*
	 *  5% 5%非常糟糕的成绩
	 */
	public int pathSum(TreeNode root, int sum) {
        // 从root开始遍历，
        List<Integer> sumByNow = new ArrayList<Integer>();
        int res = bfs(sumByNow, root, sum);
        return res;
    }
    
    private int bfs(List<Integer> sumByNow, TreeNode node, int sum) {
        int res = 0;
        if (node == null) return res;
        for (int i = 0; i < sumByNow.size(); i++) {
            sumByNow.set(i,sumByNow.get(i) + node.val);
            if (sumByNow.get(i) == sum) res++;
        }
        sumByNow.add(node.val);
        if (node.val == sum) res++;
        res += bfs(new ArrayList<Integer>(sumByNow), node.left, sum);
        res += bfs(new ArrayList<Integer>(sumByNow), node.right, sum);
        return res;
    }
    /*
     * pathSum 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，
     * 和为目标值的路径总数。
		count 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，
		能凑出几个以该节点为路径开头，和为目标值的路径总数。
		
		Runtime: 12 ms, faster than 17.27% of Java online submissions for Path Sum III.
Memory Usage: 40.3 MB, less than 73.35% of Java online submissions for Path Sum III.
     */
    
    public int pathSum2(TreeNode root, int sum) {
    	if (root == null) return 0;
    	int o = count(root, sum);
    	int l = pathSum2(root.left, sum);
    	int r = pathSum2(root.right, sum);
    	return o + r + l;
    }
    
	private int count(TreeNode root, int sum) {
		if (root == null) return 0;
		int res = 0;
		if (root.val == sum) res++;
		res += count(root.left, sum - root.val);
		res += count(root.right, sum - root.val);
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
