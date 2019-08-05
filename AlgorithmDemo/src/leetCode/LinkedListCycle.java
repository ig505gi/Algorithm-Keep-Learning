package leetCode;

import leetCode.util.ListNode;

/**
 * @author Gao Yuan
 * @date 2019-08-02 - 12:51
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
 * Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Linked List Cycle.
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        // 快慢指针
        if (head == null || head.next == null) return false;
        ListNode q = head, l = head;
        do {
            l = l.next;
            if (q.next != null) q = q.next.next;
            else q = q.next;
        } while (q != l && q != null);
        return q == l;
    }
}
