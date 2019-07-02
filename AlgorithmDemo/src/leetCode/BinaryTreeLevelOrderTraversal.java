package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Runtime: 1 ms, faster than 74.98% of Java online submissions for Binary Tree Level Order Traversal.
Memory Usage: 37.5 MB, less than 9.54% of Java online submissions for Binary Tree Level Order Traversal.
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeLevelOrderTraversal {
    
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        nodeList.add(root);
        traversal(res, nodeList);
        
		return res;
    }

	private void traversal(List<List<Integer>> res, List<TreeNode> nodeList) {
		List<Integer> nodes = new ArrayList<Integer>();
		List<TreeNode> newList = new ArrayList<TreeNode>();
		if (nodeList.isEmpty()) return;
		for (TreeNode node: nodeList) {
			nodes.add(node.val);
			if (node.left != null) newList.add(node.left);
			if (node.right != null) newList.add(node.right);
		}
		res.add(nodes);
		traversal(res, newList);
		
	}
	
	/**
	 * dfs解法，虽然没有层数，但是可以传参数的时候保存一个层数！
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Level Order Traversal.
Memory Usage: 37.4 MB, less than 39.89% of Java online submissions for Binary Tree Level Order Traversal.
	 */
	
	public  List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, root, 1);
        
		return res;
    }

	private void dfs(List<List<Integer>> res, TreeNode node, int height) {
		if (node == null) return;
		if (height > res.size()) {
			res.add(new ArrayList<Integer>());
		}
		res.get(height - 1).add(node.val);
		dfs(res, node.left, height + 1);
		dfs(res, node.right, height + 1);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(1);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		BinaryTreeLevelOrderTraversal solu = new BinaryTreeLevelOrderTraversal();
		List<List<Integer>> res = solu.levelOrder2(root);
		for (List<Integer> r: res) {
			for (int n: r) {
				System.out.println(n);
			}
		}
	}
	
}

class TreeNode {
	 int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode(int x) { val = x; }
}
