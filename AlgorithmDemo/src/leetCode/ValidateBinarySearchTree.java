package leetCode;

import leetCode.util.TreeNode;

/**
 * @author Gao Yuan
 * @date 2019-08-21 - 16:52
 */
public class ValidateBinarySearchTree {

    /*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Validate Binary Search Tree.
    一开始想错了，只比较了左节点和根，实际是要比较左子树的最右节点和根的大小
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        TreeNode leftRight = root.left;
        TreeNode rightLeft = root.right;
        while (leftRight != null && leftRight.right != null) { leftRight = leftRight.right; }
        while (rightLeft != null && rightLeft.left != null) { rightLeft = rightLeft.left; }
        boolean flag1 = leftRight == null || root.val > leftRight.val;
        boolean flag2 = rightLeft == null || root.val < rightLeft.val;
        return flag1 && flag2 && isValidBST(root.left) && isValidBST(root.right);
    }
    /*
    Runtime: 1 ms, faster than 44.71% of Java online submissions for Validate Binary Search Tree.
Memory Usage: 38.9 MB, less than 82.33% of Java online submissions for Validate Binary Search Tree.
    仅仅因为一句优化
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        TreeNode leftRight = root.left;
        TreeNode rightLeft = root.right;
        while (leftRight != null && leftRight.right != null) { leftRight = leftRight.right; }
        while (rightLeft != null && rightLeft.left != null) { rightLeft = rightLeft.left; }
        boolean flag1 = (leftRight == null) ? true : root.val > leftRight.val;
        boolean flag2 = (rightLeft == null) ? true : root.val < rightLeft.val;
        return flag1 && flag2 && isValidBST(root.left) && isValidBST(root.right);
    }


}
