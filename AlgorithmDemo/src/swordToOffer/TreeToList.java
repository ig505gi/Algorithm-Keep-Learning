package swordToOffer;

import leetCode.util.TreeNode;

/**
 * @author Gao Yuan
 * @date 2019-09-07 - 14:23
 */
public class TreeToList {

    public TreeNode Convert(TreeNode pRootOfTree) {

        TreeNode curListNode = convertToNode(pRootOfTree, null); // 转换完成，当前指向结尾

        while (curListNode != null && curListNode.left != null) {
            curListNode = curListNode.left;
        }

        return curListNode;
    }


    private TreeNode convertToNode(TreeNode pRootOfTree, TreeNode curListNode) {
        if (pRootOfTree == null) return curListNode;

        if (pRootOfTree.left != null) {
            curListNode = convertToNode(pRootOfTree.left, curListNode);
        }
        pRootOfTree.left = curListNode;
        if (curListNode != null) curListNode.right = pRootOfTree;
        curListNode = pRootOfTree;

        if (curListNode.right != null) {
            curListNode = convertToNode(pRootOfTree.right, curListNode);
        }
        return curListNode;
    }

    public static void main(String[] args) {

    }
}
