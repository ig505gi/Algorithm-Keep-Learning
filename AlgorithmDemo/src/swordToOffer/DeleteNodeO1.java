package swordToOffer;

/**
 * 时间复杂度为O(1)的删除节点算法
 * @author GaoYuan
 *
 */
public class DeleteNodeO1 {
	
	// 删除节点 返回root
	public Node deleteNode(Node root, Node node) {
		/*
		 * 1.不用找父节点，把下一个节点的内容复制到该节点
		 * 2.是最后一个节点，必须从头找到父节点
		 * 3.只有一个节点，要把root置位空
		 */
		// 有空
		if (root == null | node == null) {
			System.out.println("有一个为空！");
			return root;
		}
		// 只有一个节点
		if (root.next == null) {
			if (node == root) {
				System.out.println("只有一个节点，并删除了！");
				return null;
			}
			else {
				System.out.println("只有一个节点，但是指定删除的不是它");
				return root; // node不是root，不用删除
			}
		}
		// 尾节点, 这里不能确定node是否在链表中。。如果要确认，必须再遍历一遍
		if (node.next == null) {
			Node pre = root;
			while (pre.next != node) {
				pre = pre.next;
			}
			pre.next = null;
			System.out.println("制定了最后一个节点，删除了！");
		} else { // 普通节点，直接复制下一个节点，指向下下个节点
			node.val = node.next.val;
			node.next = node.next.next;
			// Java应该不用删除该节点 自动内存管理
			System.out.println("普通节点，删除了！");
		}
		return root;
		
	}
	
	public static void main(String[] args) {
		Node root = new Node(0);
		// root.next = new Node(1);
		// root.next.next = new Node(2);
		
	}

}

class Node {
	 int val;
	 Node next;
	 Node(int x) { val = x; }
}