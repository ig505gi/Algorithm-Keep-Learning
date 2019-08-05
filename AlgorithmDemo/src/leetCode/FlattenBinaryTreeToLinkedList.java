package leetCode;

import leetCode.util.TreeNode;

import javax.transaction.TransactionRequiredException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-08-02 - 16:51
 */
public class FlattenBinaryTreeToLinkedList {
    /*
    要把树给倒下去，应该是先根遍历，只要先跟遍历，可以创建新的节点，来完成
    这样要额外的空间

    因为返回的void，实际上的root的结构并未改变，这样做 只是把root指向了我新建的结构
     */
    // 错误方法！！
    public void wrongflatten(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        TreeNode head = new TreeNode(0);
        TreeNode cur = head;
        for (Integer val : list) {
            cur.right = new TreeNode(val);
            cur = cur.right;
        }
        root = head.right;
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }


    /*
    既然前面的方法是用一个多余的指针指向root.right...
    但是不成功，需要用两个指针指向左右，但是和我之前的方法一样还需要一个head指向最终的链表尾部
     */
    // 需要类变量支持
    TreeNode head = new TreeNode(0);
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode l = root.left;
        TreeNode r = root.right;
        head.right = root;
        root.left = null;
        head = root;
        flatten(l);
        flatten(r);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
        solution.flatten(root);
    }
}
