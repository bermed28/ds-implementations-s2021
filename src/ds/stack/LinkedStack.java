package ds.stack;

import java.util.EmptyStackException;


public class LinkedStack<E> implements StackInterface<E> {
	
	private static class Node<E> {
		private E data; 
		private Node<E> next; 
		
		public Node(E data, Node<E> next) { 
			this.data = data; 
			this.next = next; 
		}

		public E getData() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}
		
		public void clear() {  // no need to return data
			data = null; 
			next = null; 
		}
		
		// notice that setters are not needed in this 
		// implementation of the stack
	}

	// instance variables for the stack object
	
	private Node<E> top; 
	private int size; 
	
	public LinkedStack() { 
		top = null; 
		size = 0; 
	}
	
	@Override
	public void push(E newEntry) {
		Node<E> nNode = new Node<>(newEntry, top); 
		top = nNode; 
		size++; 
	}

	@Override
	public E pop() {
		E etr = peek(); 
		Node<E> ntr = top; 
		top = top.getNext(); 
		size--; 
		ntr.clear();
		return etr;
	}

	@Override
	public E peek() {
		if (isEmpty()) 
			throw new EmptyStackException(); 
		return top.getData();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		Node<E> ntd = top; 
		while (top != null) { 
			ntd.clear(); 
			top = top.getNext(); 
			ntd = top; 
		}
		
		size = 0; 
	}

}
