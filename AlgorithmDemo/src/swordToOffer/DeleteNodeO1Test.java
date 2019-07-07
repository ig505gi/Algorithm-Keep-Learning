package swordToOffer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DeleteNodeO1Test {
	DeleteNodeO1 solution = new DeleteNodeO1();
	
	@Test
	void testDeleteNull() {
		solution.deleteNode(null, null);
		// Node root = new Node(0);
		// root.next = new Node(1);
		// root.next.next = new Node(2);
		
	}
	
	@Test
	void testDeleteOne() {
		Node root = new Node(0);
		Node node = new Node(1);
		solution.deleteNode(root, null);
		solution.deleteNode(root, root);
		
	}
	
	@Test
	void testDeleteTail() {
		Node root = new Node(0);
		root.next = new Node(1);
		root.next.next = new Node(2);
		solution.deleteNode(root, root.next.next);
	}
	
	@Test
	void testDeleteNomal() {
		Node root = new Node(0);
		root.next = new Node(1);
		root.next.next = new Node(2);
		solution.deleteNode(root, root.next);
	}

}
