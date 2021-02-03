package ds.bag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * Implementation of a Static Sized Bag 
 * using an array of a generic type E
 * 
 * @author bermed28
 *
 * @param <E>
 */
public class ArrayBag<E> implements Bag<E> {
	
	private int currentSize;
	private E[] elements;
	
	private final static int DEFAULT_SIZE = 25;
	
	public ArrayBag() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayBag(int maxCapacity) {
		if(maxCapacity < 1) {
			elements = (E[]) new Object[DEFAULT_SIZE];
		} else {
			elements = (E[]) new Object[maxCapacity];
		}
		
		currentSize = 0;
	}
	
	
	private class BagIterator<T> implements Iterator<T>{
		
		int currentPosition;
		

		public boolean hasNext() {
			return currentPosition < size();
		}

	
		@SuppressWarnings("unchecked")
		public T next() {
			if(this.hasNext()) {
				return (T) elements[currentPosition++];
			} else {
				throw new NoSuchElementException();
			}
		}	
		
	}

	public boolean add(E elm) {
		if(size() == elements.length) 
			return false;
		
		
		elements[currentSize++] = elm;
		return true;

	}
	
	public E remove() {
		if(!this.isEmpty()){
			E last = elements[currentSize -1];
			elements[currentSize-1] = null;
			currentSize--;
			return last;
		}
		return null;
	}

	public boolean remove(E elm) {
		for (int i = 0; i < size(); i++) {
			if(elements[i].equals(elm)) {
				elements[i] = elements[--currentSize];
				elements[currentSize] = null;
				return true;
			}
		}
		
		return false;
	}

	public int removeAll(E elm) {
		int copiesRemoved = 0;
		
		while(remove(elm))
			copiesRemoved++;
		
		
		return copiesRemoved;
	}

	public void clear() {
		for (int i = 0; i < elements.length; i++) 
			elements[i] = null;
		
		currentSize = 0;
	}

	public int getFrequencyOf(E elm) {
		int copies = 0;
		
		for (int i = 0; i < size(); i++) 
			if(elements[i].equals(elm)) copies++;
		
		return copies;
	}

	public boolean contains(E elm) {
		return getFrequencyOf(elm) > 0;
	}

	public int size() {
		return currentSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		Object[] arr = new Object[size()];
		
		for (int i = 0; i < size(); i++) {
			arr[i] = (Object) elements[i];
		}
		return (E[]) arr;
	}

	public Iterator<E> iterator() {
		return new BagIterator<E>();
	}
	
	/**
	 * ADD YOUR CODE HERE
	 * 
	 * Add your method implementations 
	 * of the prototypes you added in the interface
	 * 
	 */
	
	 /*
     * Implement the method bagAdjuster(). (Mutator member method)
     * This method will receive an integer as input and it will modify the bag so that
     * there is exactly that amount of EACH element.
     * 
     * Example:
     * Bag b = {a, b, b, c}
     * 
     * If we call:
     * b.bagAdjuster(2)
     * 
     * After the method, b should have: {a, a, b, b, c, c}
     * 
     * If we call:
     * b.adjustBag(1)
     * After the method, b should have: {a, b, c}
     */
    
    public void bagAdjuster(int amount) {
    	// Add code here
    	// Get array with the elements of the bag
    	E[] b = this.toArray();
    	// Iterate through the array 
    	// Not ideal to use size() since the bag will be modified
    	for(int i = 0; i < b.length; i++) {
    		// Reached end of elements (Array may have empty spaces)
    		if(b[i] == null)
    			break;
    		// Remove elements if there are more than given amount
    		while(this.getFrequencyOf(b[i]) > amount)
    			this.remove(b[i]);
    		// Add more elements if not enough 
    		while(this.getFrequencyOf(b[i]) < amount)
    			this.add(b[i]);
    	}
    	
    }

    /*
     * Implement the method equals(Bag<E> b2). (Non-mutator member method)
     * This method will compare the Bag with b2 to see if they have the same content.
     * 
     * NOTE: 
     * - Remember bags are unordered collections so they do not have to necessarily be in the same order. 
     * 
     * Examples:
     * If we have:
     * Bag b = {a, b, c}
     * Bag b1 = {a, c, b}
     * Bag b2 = {a, c}
     * Bag b3 = {a, a, c}
     * 
     * Then:
     * b.equals(b1) should return True
     * b1.equals(b) should return True
     * b.equals(b2) should return False
     * b2.equals(b3) should return False
     */
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
	
	/**
     * Implement a member method for the Bag ADT called moreFrequentThan(E obj). 
     * 
     * The method is Non-mutator, meaning it does not modify the target object.
     * 
     * The method receives a parameter of type E and returns a new Bag with all
     * the elements than appear more frequent than obj.
     * 
     * NOTE:
     * - If the Bag is empty return an empty bag. 
     * - This method should not affect the original bag.
     * - The order of the elements is irrelevant (bags are unordered). 
     * - The new Bag that is returned shouldn't have duplicates.
     * 
     * Example:
     * Bag B = {Joe, Ned, Kim, Joe, Ned, Joe, Ari, Lu}
     * Bag E = B.moreFrequentThan(Lu)
     * 
     * Then B and E should look like this:
     * B = {Joe, Ned, Kim, Joe, Ned, Joe, Ari, Lu}
     * E = {Joe, Ned}
     * 
     * @param obj - The element that will determine which elements are more frequent than this one
     * @return A new Bag<E> with all the elements more frequent than obj
     */
    public Bag<E> moreFrequentThan(E obj) {
    	// Add code here
    	
    	//Create bag
		Bag<E> bag = new ArrayBag<E>();
    	// Check if object is in the bag
    	if(!this.isEmpty()) {
        	// Get count
        	int count = this.getFrequencyOf(obj);
        	// Get the object available in the Bag
        	E[] stuff = this.toArray();
        	for(E o : stuff) {
        		// Make sure no duplicates get added and compare the frequency
        		if(!bag.contains(o) && this.getFrequencyOf(o) > count)
        			bag.add(o);
        	}
    	}
    	return bag;
    	
    }
    
    /**
     * 
	 * Implement a non-member method for the Bag ADT called removeDuplicates()
	 * 
	 * This method receives a Bag<E> as parameter and removes all duplicates from it. 
	 * The method returns the amount of duplicates removed from the bag.
	 * 
	 * The method MODIFIES the target bag
	 * 
	 * Example:
	 * Bag b = {a, b, a, c, d, a, b}
	 * removeDuplicates(b) should return 3
	 * After the method b should have: {a, b, c, d}
	 * 
	 * @param B - bag to search for duplicates
	 * @return the amount of duplicates that were removed from B
	 	
		public static <E> int removeDuplicates(Bag<E> B) {
			// ADD YOUR CODE HERE
			int count = 0;
			// Get array of the bag elements
			E[] bag = B.toArray();
			
			for(E o: bag) {
				// If the frequency is larger than 1 remove
				while(B.getFrequencyOf(o) > 1) {
					B.remove(o);
					count++;
				}	
			}
			return count;
		}
     * 
     * 
     */
}
