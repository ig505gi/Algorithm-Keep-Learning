package leetCode;

import leetCode.util.ListNode;

/**
 * @author Gao Yuan
 * @date 2019-07-26 - 11:16
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
 * Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Remove Nth Node From End of List.
 *
 * 要把一个两个，删除头结点，删除尾节点的情况都要考虑进来
 */
public class RemoveNthNodeFromEndofList {
    // 双指针，第一个先走n+1步，然后同时走两个，当快的指向null时，慢的所指的就是要去除的父节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null) return null;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode quick = pre;
        ListNode slow = pre;
        for (int i = 0; i < n + 1; i++) {
            quick = quick.next;
        }
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }

        if (slow.next != null) {
            slow.next = slow.next.next;
        } else {
            slow.next = null;
        }
        return pre.next;
    }
}
