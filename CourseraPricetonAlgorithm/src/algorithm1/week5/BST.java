package algorithm1.week5;
/**
 * balanced search tree
 * 由2-3 tree变化来
 * 主要子操作: left rotation, right rotation, color flip
 * @author admin
 *
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	private final boolean RED = true;
	private final boolean BLACK = false;
	
	private class Node {
		Key key;
		@SuppressWarnings("unused")
		Value val;
		Node left, right;
		boolean color; //color of parent link
		
		Node(Key key, Value val, boolean color){
			this.key = key;
			this.val = val;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if (x == null) return false; // null link is black
		return x.color;
	}
	
	private Node rotateLeft(Node h) {
		// if isRed(h.right)
		Node x = h.right;
		h.right = x.left;
		h.color = RED;
		x.color = h.color;
		x.left = h;
		return x;
	}
	
	private Node rotateRight(Node h) {
		// if isRed(h.left)
		Node x = h.left;
		h.left = x.right;
		h.color = RED;
		x.color = h.color;
		x.right = h;
		return x;
	}
	
	private Node flipColors(Node x) {
		// if isRed(x.right) && isRed(x.left)
		x.color = RED;
		x.right.color = BLACK;
		x.left.color = BLACK;
		return x;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, RED);
		int cmp = key.compareTo(x.key);
		if (cmp == 0) x.val = val;
		else if (cmp > 0)  x.right = put(x.right, key, val);
		else /* cmp < 0 */ x.left = put(x.left, key, val);
		
		if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
		if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
		if (isRed(x.right) && isRed(x.left)) x = flipColors(x);
		
		return x;
	}
	public BST() {
		
	}

	public static void main(String[] args) {

	}

}
