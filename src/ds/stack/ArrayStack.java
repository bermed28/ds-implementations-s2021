package ds.stack;

import java.util.EmptyStackException;


public class ArrayStack<E> implements StackInterface<E> {
	private static final int INITCAP = 10; 
	private E[] elements; 
	private int size; 
	
	public ArrayStack() { 
		elements = (E[]) new Object[INITCAP]; 
		size = 0; 
	}

	@Override
	public void push(E newEntry) {
		if (size == elements.length)
			doubleCapacity(); 
		
		elements[size++] = newEntry; 
		
	}

	@Override
	public E pop() {
		E etr = peek();     // throws ESE if needed...
		elements[--size] = null; 
		return etr;
	}

	@Override
	public E peek() {
		if (isEmpty()) 
			throw new EmptyStackException(); 
		return elements[size-1];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() { 
		return size; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		for (int i=0; i<size; i++) 
			elements[i] = null; 
		
		// just leave it with the inicial capacity
		elements = (E[]) new Object[INITCAP]; 
		
		size = 0; 
		
	}
	
	private void doubleCapacity() { 
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[2*elements.length]; 
		
		for (int i=0; i<size; i++) { 
			newArray[i] = elements[i]; 
			elements[i] = null;        // support GC
		}
		
		elements = newArray; 
	}

}
