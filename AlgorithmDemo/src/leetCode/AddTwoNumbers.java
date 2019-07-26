package leetCode;

import leetCode.util.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 * @author GaoYuan
 *
 *
 *看了讨论区，基本上是用一个新的链表，并且循环的时候带上多余的一位数，最后返回表头
 *比较简单。。不再实现
 */

public class AddTwoNumbers {
    
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        add(l1, l2);
        return l1;
    }
    
    private void add(ListNode l1, ListNode l2){
        if (l2 != null)
            l1.val += l2.val;
        if (l1.val >= 10) {
            l1.val -= 10;
            if (l1.next != null)
                l1.next.val += 1;
            else
                l1.next = new ListNode(1);
        }
        
        // l1先空，或者同时为空的情况
        if (l1.next == null ) {
            if (l2 == null || l2.next == null) return;
            l1.next = l2.next;
            add(l1.next, null);
        } 
        // l2已经不存在的情况
        else if (l2 == null || l2.next == null) {
            add(l1.next, null);
        } 
        // 都不为空的情况
        else {          
            add(l1.next, l2.next);
        }
    }
}
