package leetCode;

import leetCode.util.TreeNode;


/**
 * @author Gao Yuan
 * @date 2019-07-24 - 21:16
 * Runtime: 1 ms, faster than 83.07% of Java online submissions for House Robber III.
 * Memory Usage: 38.7 MB, less than 75.96% of Java online submissions for House Robber III.
 * 思路一直是对的，但是自己用代码实现起来还是吃力，一开始弯路绕了很多
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        int[] res = {0, 0};
        if (root == null) return res;

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        HouseRobberIII solution = new HouseRobberIII();
        System.out.println(solution.rob(root));
    }
}
