package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 之字型层序遍历
 * 想到用stack保存每一层
 * Runtime: 1 ms, faster than 74.80% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
Memory Usage: 37.2 MB, less than 29.98% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
 * @author GaoYuan
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>(); 
        if (root != null) stack.push(root);
        boolean flag = true;
        while (!stack.isEmpty()) {
        	List<Integer> level = new ArrayList<Integer>();
			Stack<TreeNode> newStack = new Stack<TreeNode>();
			while (!stack.isEmpty()) {
				TreeNode tem = stack.pop();
				level.add(tem.val);
				if (flag) {
					if (tem.left != null) newStack.push(tem.left);
					if (tem.right != null) newStack.push(tem.right);
				} else {
					if (tem.right != null) newStack.push(tem.right);
					if (tem.left != null) newStack.push(tem.left);
				}
			}
			flag = !flag;
        	res.add(level);
        	stack = newStack;
        }
        return res;
    }
	
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
