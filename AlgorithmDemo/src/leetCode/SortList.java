package leetCode;

import leetCode.util.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4

Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5


 * @author GaoYuan
 * 思路：需要nlogn的时间复杂度 还不能用多余的空间
 * 冒泡是可以实现n2，而nlogn的话可以用分治
 * 假如前一半已经排序好了，后一半也排序好了，
 * 任务是合并这两半，
 */
public class SortList {

	/*
	 * Runtime: 3 ms, faster than 97.37% of Java online submissions for Sort List.
Memory Usage: 40 MB, less than 97.56% of Java online submissions for Sort List.
	不错的成绩，自己想的方法是对的，但是具体的一些小细节上还是实现的太慢太磨叽了，然后看了讨论才写出来，还是写的不够多。
	 */
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode slow = head, quick = head, pre = null;
		while (quick != null && slow != null && quick.next != null) {
			pre = slow;
			slow = slow.next;
			quick = quick.next.next;
		}
		pre.next = null; // 切断前后两部分
		head = sortList(head);
		slow = sortList(slow);
		return merge(head, slow);
	}
	
	public ListNode merge(ListNode p, ListNode q) {
		// p和q一定不为null, 且p和q是排序好的
		ListNode head = new ListNode(0);
		ListNode tail = head;
		while (p != null && q != null) {
			if (p.val <= q.val) {
				tail.next = p;
				p = p.next;
				tail = tail.next;
			} else {
				tail.next = q;
				q = q.next;
				tail = tail.next;
			}
		}
		if (p != null) tail.next = p;
		else if (q != null) tail.next = q;
		return head.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
