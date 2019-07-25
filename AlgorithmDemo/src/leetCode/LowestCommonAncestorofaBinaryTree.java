package leetCode;

import java.util.Stack;
import leetCode.util.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 找最近的公共祖先
 * @author GaoYuan
 * 1.dfs，在stack中保存的是从root到p或q的路径，假如先找到了p
 * 2. 然后将p作为res，如果stack，pop()就赋值给res，找到q停止，此时的res就是结果
 */
public class LowestCommonAncestorofaBinaryTree {

	/*
	 * public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	 * Stack<Integer> stack = new Stack<Integer>(); int res = 0; // 遍历找到p或q的时候停止
	 * dfs(stack, root, p, q, res);
	 * 
	 * return null; }
	 * 
	 * private void dfs(Stack<Integer> stack, TreeNode node, TreeNode p, TreeNode q,
	 * int res) { if (node == null) return; stack.push(node.val); if (stack.peek()
	 * == p.val || stack.peek() == q.val) { res = stack.peek(); } dfs(stack,
	 * node.left, p, q, res); stack.pop(); dfs(stack, node.right, p, q, res); }
	 */

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null | root == p | root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
        	return right;
        } else if (right == null) {
        	return left;
        } else {
        	return root;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
