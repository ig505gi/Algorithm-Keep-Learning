package algorithm1.week4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
/**
 * BST: Binary Search Tree
 * @author admin
 *
 * @param <Key>
 * @param <Value>
 */

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int count; // 该树的大小

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}
	
	public BST() {}
	public BST(Key key, Value val) {
		this.root = new Node(key, val);
	}
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if (x == null) return 0;
		return x.count;
	}
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val) {
		if (x == null)
			x = new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp > 0)
			x.right = put(x.right, key, val);
		else if (cmp < 0)
			x.left = put(x.left, key, val);
		else
			x.val = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp > 0)
				x = x.right;
			else if (cmp < 0)
				x = x.left;
			else
				return x.val;
		}
		return null;
	}

	public Value floor(Key key) {
		Node x = floor(root, key);
		if (x == null) return null;
		else {
			return x.val;
		}
	}
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		
		int cmp = key.compareTo(x.key);
		if (cmp == 0) { // 这个节点就是需要的值
			return x; 
		}
		else if (cmp < 0) { // 一定是左子树的 floor
			return floor(x.left, key);
		}
		else { // floor一定在右子树，但是必须floor返回的不为空，如果floor返回为空，说明没找到floor
			Node v = floor(x.right, key);
			if (v == null) {
				return x;
			} else {
				return v;
			}
		}
		
	}
	
	public Value min() {
		return min(root).val;
	}
	private Node min(Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) { //返回的是根
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	} 
	public void delete(Key key) {
		root = delete(root, key);
	}
	public Node delete(Node x, Key key) { // 返回的是根
		if (x == null) return null;
		
		int cmp = key.compareTo(x.key);
		if (cmp == 0) { // 这个节点就是需要删除的值
			/*比较多余的版本 仔细想了下 还是错误的，不能只改变val
		    if (x.count == 1) x = null;
			 
			if (x.left != null && x.right != null) {
				Node t = min(x.right);
				x.right = deleteMin(x.right);
				x.val = t.val;
				x.key = t.key; // 比较麻烦了就， 应该直接将min赋值给x，用t保存原节点    
			}
			if (x.left != null) x = x.left;
			if (x.right != null) x = x.right;
			*/
			
			if (x.left == null) return x.right; //这就包括了都为空的情况，也返回了null
			if (x.right == null) return x.left;
			
			Node t = x;
			x = min(x.right);
			x.right = deleteMin(x.right);
			x.left = t.left;
		}
		else if (cmp < 0) { // 一定是左子树的
			x.left = delete(x.left, key);
		}
		else { // 一定在右子树
			x.right = delete(x.right, key);
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		inorder(root, q);
		return q;
	}
	
	private void inorder(Node x, Queue<Key> q) {
		if (x == null) return;
		inorder(x.left, q);
		q.enqueue(x.key);
		inorder(x.right, q);
	}

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<Integer, String>();
		bst.put(2, "a");
		bst.put(0, "I");
		bst.put(3, "new");
		bst.put(1, "am");
		bst.put(4, "coder");
		StdOut.println(bst.get(3));
		for (int key: bst.keys()) {
			StdOut.print(bst.get(key) + " ");
		}
		StdOut.println();
		StdOut.println(bst.size());
		StdOut.println(bst.floor(3));
		bst.delete(1);
		StdOut.println(bst.min());
		bst.deleteMin();
		StdOut.println(bst.min());
		bst.deleteMin();
		bst.delete(3);
		StdOut.println(bst.min());
		for (int key: bst.keys()) {
			StdOut.print(bst.get(key) + " ");
		}
	}

}
