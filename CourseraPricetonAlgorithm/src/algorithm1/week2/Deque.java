package algorithm1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size = 0;
	private Node<Item> first;
	private Node<Item> last;
	
	@SuppressWarnings("hiding")
	private class Node<Item> {
		private Item item;
		private Node<Item> pre = null;
		private Node<Item> next = null;
	}
	
	/* 
     * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
     * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
     * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
     * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator. 
	 */
	public Deque() // construct an empty deque
	{
		first = null;
		last = null;
	}

	public boolean isEmpty() // is the deque empty?
	{
		return first == null && last == null;
	}

	public int size() // return the number of items on the deque
	{
		return size;
	}

	public void addFirst(Item item) // add the item to the front
	{
		if (item == null) { throw new IllegalArgumentException(); }
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.pre = null;
		first.next = null;
		if (last == null) {
			last = first;
		} else {
			first.next = oldFirst;
			oldFirst.pre = first;
		}
		size++;
	}

	public void addLast(Item item) // add the item to the end
	{
		if (item == null) { throw new IllegalArgumentException(); }
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.pre = null;
		last.next = null;
		if (first == null) {
			first = last;
		} else {
			last.pre = oldLast;
			oldLast.next = last;
		}
		size++;
	}

	public Item removeFirst() // remove and return the item from the front
	{
		if (isEmpty()) { throw new NoSuchElementException(); }
		Item item = first.item;
		first = first.next;
		if (first == null) {
			last = null;
		} else {
			first.pre = null;
		}
		size--;
		return item;
	}

	public Item removeLast() // remove and return the item from the end
	{
		if (isEmpty()) { throw new NoSuchElementException(); }
		Item item = last.item;
		last = last.pre;
		if (last == null) {
			first = null;
		} else {
			last.next = null;
		}
		size--;
		return item;
	}

	public Iterator<Item> iterator() // return an iterator over items in order from front to end
	{
		
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) { throw new NoSuchElementException(); }
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	public static void main(String[] args) // unit testing (optional)
	{
		Deque<String> deque = new Deque<String>();
		String item = null;
		try{
			// deque.removeFirst();
			deque.removeLast();
			deque.addFirst(item);
		} catch (NoSuchElementException e) {
			System.out.println("correct!");
		} finally {
			deque.addFirst("b");
			deque.addLast("c");
			deque.addFirst("a");
			deque.addFirst("o");
			for (String i : deque) {
				System.out.print(i + ", "); // 0, 1, 2 //o, a, b, c
			}

			deque.removeLast();
			deque.removeFirst();
			for (String i : deque) {
				System.out.print(i + ", "); // 0, 1  //a, b
			}
			// deque.removeFirst();
			System.out.print(deque.first.item + " == " + deque.last.item);
		}
	}
	

}
