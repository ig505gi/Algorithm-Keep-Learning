package leetCode;

import leetCode.util.ListNode;

/**
 * @author Gao Yuan
 * @date 2019-08-02 - 12:35
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
 * Memory Usage: 39.5 MB, less than 14.75% of Java online submissions for Merge Two Sorted Lists.
 */
public class MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 出来之后 一定有一个为null, 不可能两个都为null
        if (l1 == null) cur.next = l2; // 后面都是l2
        if (l2 == null) cur.next = l1; // 后面都是l1
        return newHead.next;
    }
}
