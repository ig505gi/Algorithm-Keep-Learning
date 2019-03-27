package leetCode;

public class ReverseLinkedList {
	/**
	 * Definition for singly-linked list. public class ListNode { int val; ListNode
	 * next; ListNode(int x) { val = x; } }
	 */
	/**
	 * 迭代的解法 还有递归的解法
	 * @param head
	 * @return
	 */
	public ListNode reverseListInteration(ListNode head) {
		if (head == null) return null;
		ListNode pre = head;
		ListNode next = head.next;
		pre.next = null;
		while (next != null) {
			ListNode temp = next;
			next = next.next;
			temp.next = pre;
			pre = temp;
		}
		return pre;
	}
	
	public ListNode reverseListRecusive(ListNode head) {
		return reverse(head, null);
	} 


	private ListNode reverse(ListNode head, ListNode newHead) {
		if (head == null) {
			return newHead;
		} else {
			ListNode temp = head;
			head.next = newHead;
			return reverse(temp.next, head);
		}
	}


	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
