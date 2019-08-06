package leetCode;

import leetCode.util.TreeNode;

/**
 * @author Gao Yuan
 * @date 2019-08-06 - 09:02
 */
public class DiameterOfBinaryTree {
    /*
    找到树的最大路径，而不是找最深度，就是任何两个节点之间的最大距离
    确定递归函数的功能，maxDepth(root),返回任意节点的最大深度，同时对左右节点递归求和 则可以得到最大路径，
    和一个全局变量对比
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
Memory Usage: 38.4 MB, less than 40.00% of Java online submissions for Diameter of Binary Tree.
    一遍成功！
     */
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int ldepth = maxDepth(root.left);
        int rdepth = maxDepth(root.right);
        max = Math.max(max, ldepth + rdepth);
        return Math.max(ldepth, rdepth) + 1;
    }
}
