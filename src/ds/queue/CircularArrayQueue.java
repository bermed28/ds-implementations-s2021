package ds.queue;

public class CircularArrayQueue<E> implements Queue<E> {
	private static final int INITCAP = 10; 
	private E[] elements;   // the array
	private int first;      // the index of the first element in the queue... If size != 0, the elements are: 
	                        // elements[first], elements[(first+1)%elements.length, ..., 
	                        // elements[(first+size-1)%elements.length. 
	private int size; 
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue() { 
		elements = (E[]) new Object[INITCAP]; 
		first = 0; 
		size = 0; 
	}

	@Override
	/**
	 * Adds a new element to the end of the queue. 
	 */
	public void enqueue(E newEntry) {
		if (size == elements.length)
			doubleCapacity(); 

		int firstAvailable = (first+size) % elements.length; 
		
		elements[firstAvailable] = newEntry; 
		
		size++; 
		
	}

	@Override
	/**
	 * Extracts and returns the first element in the queue. Throws an exception
	 * if the queue is empty. 
	 */
	public E dequeue() throws EmptyQueueException {
		E etr = getFront();    // throws the exception, if empty.
		
		elements[first] = null; 
		first = (first + 1) % elements.length; 
		size--; 

		return etr;
	}

	@Override
	public E getFront() throws EmptyQueueException {
		if (isEmpty()) 
			throw new EmptyQueueException("The queue is empty."); 
		return elements[first];
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
	public void clear() {
		for (int i=0; i<size; i++) { 
			elements[(first+i)%elements.length] = null;        // support GC
		}

		// just leave it with the initial capacity
		elements = (E[]) new Object[INITCAP]; 
		
		first = 0; 

		size = 0; 
	}

	@SuppressWarnings("unchecked")
	private void doubleCapacity() { 
		E[] newArray = (E[]) new Object[2*elements.length]; 
		
		for (int i=0; i<size; i++) { 
			newArray[i] = elements[(first+i)%elements.length]; 
			elements[(first+i)%elements.length] = null;        // support GC
		}
		
		first = 0; 
		elements = newArray; 
	}
}

