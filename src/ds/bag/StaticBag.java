package ds.bag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StaticBag<E> implements Bag<E> {

	private int currentSize;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 25;
	
	@SuppressWarnings("unchecked")
	public StaticBag(int maxCapacity) {
		if (maxCapacity < 1)
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		
		currentSize = 0;
		elements = (E[]) new Object[maxCapacity];
		
	}
	
	public StaticBag() {
		this(DEFAULT_CAPACITY);
	}
	
	private class BagIterator<T> implements Iterator<T>{
		
		int currentPosition;
		
		@Override
		public boolean hasNext() {
			return currentPosition < size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if(this.hasNext()) {
				return (T) elements[currentPosition++];
			} else {
				throw new NoSuchElementException();
			}
		}	
		
	}
	
	@Override
	public boolean add(E o) {
		if(size() == elements.length) 
			return false;
		
		
		elements[currentSize++] = o;
		return true;
	}

	@Override
	public boolean remove(E o) {
		
		for (int i = 0; i < size(); i++) {
			if(elements[i].equals(o)) {
				elements[i] = elements[--currentSize];
				elements[currentSize] = null;
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public E remove() {
		if(isEmpty()) 
			return null;
		
		
		return elements[currentSize--];
		
	}

	@Override
	public int removeAll(E o) {
		int copiesRemoved = 0;
		
		while(remove(o))
			copiesRemoved++;
		
		
		return copiesRemoved;
	}

	@Override
	public void clear() {
		
		for (int i = 0; i < elements.length; i++) 
			elements[i] = null;
		
		currentSize = 0;

	}

	@Override
	public int getFrequencyOf(E o) {
		int copies = 0;
		
		for (int i = 0; i < size(); i++) 
			if(elements[i].equals(o)) copies++;
		
		return copies;
	}

	@Override
	public boolean contains(E o) {
		
		return getFrequencyOf(o) > 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new BagIterator<E>();
	}

	@Override
	public E[] toArray() {
		Object[] arr = new Object[size()];
		
		for (int i = 0; i < size(); i++) {
			arr[i] = (Object) elements[i];
		}
		return (E[]) arr;
	}

	@Override
	public void bagAdjuster(int n) {
		E[] b = this.toArray();
    	// Iterate through the array 
    	// Not ideal to use size() since the bag will be modified
    	for(int i = 0; i < b.length; i++) {
    		// Reached end of elements (Array may have empty spaces)
    		if(b[i] == null)
    			break;
    		// Remove elements if there are more than given amount
    		while(this.getFrequencyOf(b[i]) > n)
    			this.remove(b[i]);
    		// Add more elements if not enough 
    		while(this.getFrequencyOf(b[i]) < n)
    			this.add(b[i]);
    	}
    	
	}

	@Override
	public boolean equals(Bag<E> b2) {
		if(this.size() == b2.size()) {
			
			E[] bag1 = this.toArray();
			E[] bag2 = b2.toArray();
			
			Arrays.sort(bag1);
			Arrays.sort(bag2);
			
			for (int i = 0; i < bag1.length; i++) {
				if (!bag1[i].equals(bag2[i])) {
					return false;
				}
			}
			
			return true;
		
		}
		
		return false;
	}


}
