package swordToOffer;
import leetCode.util.TreeNode;

import java.util.*;

public class ReConstructBinaryTree {

	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre == null || in == null)
			return null;
		if (pre.length == 0 || in.length == 0)
			return null;
		int rootVal = pre[0];
		int rootIndex = -1;
		// 找到root在in中的index
		for (int i = 0; i < in.length; i++) {
			if (in[i] == rootVal) {
				rootIndex = i;
				break;
			}
		}
		TreeNode root = new TreeNode(rootVal);
		int[] leftIn = Arrays.copyOfRange(in, 0, rootIndex);
		int[] rightIn = Arrays.copyOfRange(in, rootIndex + 1, in.length);
		int[] leftPre = Arrays.copyOfRange(pre, 1, leftIn.length + 1);
		int[] rightPre = Arrays.copyOfRange(pre, leftIn.length + 1, in.length);
		root.left = reConstructBinaryTree(leftPre, leftIn);
		root.right = reConstructBinaryTree(rightPre, rightIn);
		return root;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
