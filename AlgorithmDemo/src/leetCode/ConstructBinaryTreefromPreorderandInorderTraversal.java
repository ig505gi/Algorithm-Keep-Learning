package leetCode;

import java.util.Arrays;
import leetCode.util.TreeNode;

/**
 * For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
 * @author GaoYuan
 *
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
	/**
	 * Runtime: 18 ms, faster than 9.23% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
Memory Usage: 77.1 MB, less than 5.06% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
	一直在copy数组 非常慢！！
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) return null;
		TreeNode root = construct(preorder, inorder);
		return root;
	}
	
	private TreeNode construct(int[] preorder, int[] inorder) {
		TreeNode newNode = new TreeNode(preorder[0]);
		if (preorder.length == 1) return newNode;
		// 1. 找到root在inorder中的位置
		int rootIndx = 0;
		for (; rootIndx < inorder.length; rootIndx++) {
			if (inorder[rootIndx] == preorder[0]) break;
		}
		// 2. 复制数组前面的为左子树，右面的为右子树
		if (rootIndx > 0) {
			int[] leftpre = Arrays.copyOfRange(preorder, 1, rootIndx + 1);
			int[] leftin = Arrays.copyOfRange(inorder, 0, rootIndx);
			newNode.left = construct(leftpre, leftin);
		}
		if (rootIndx + 1 < inorder.length) {
			int[] rightpre = Arrays.copyOfRange(preorder, rootIndx + 1, preorder.length);
			int[] rightin = Arrays.copyOfRange(inorder, rootIndx + 1, inorder.length);
			newNode.right = construct(rightpre, rightin);
		}	
		return newNode;
	}
	/**
	 * 不复制数组
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) return null;
		TreeNode root = construct2(preorder, inorder);
		return root;
	}
	
	private TreeNode construct2(int[] preorder, int[] inorder) {
		TreeNode newNode = new TreeNode(preorder[0]);
		if (preorder.length == 1) return newNode;
		// 1. 找到root在inorder中的位置
		int rootIndx = 0;
		for (; rootIndx < inorder.length; rootIndx++) {
			if (inorder[rootIndx] == preorder[0]) break;
		}
		// 2. 复制数组前面的为左子树，右面的为右子树
		if (rootIndx > 0) {
			int[] leftpre = Arrays.copyOfRange(preorder, 1, rootIndx + 1);
			int[] leftin = Arrays.copyOfRange(inorder, 0, rootIndx);
			newNode.left = construct(leftpre, leftin);
		}
		if (rootIndx + 1 < inorder.length) {
			int[] rightpre = Arrays.copyOfRange(preorder, rootIndx + 1, preorder.length);
			int[] rightin = Arrays.copyOfRange(inorder, rootIndx + 1, inorder.length);
			newNode.right = construct(rightpre, rightin);
		}	
		return newNode;
	}

	public static void main(String[] args) {
		

	}

}


