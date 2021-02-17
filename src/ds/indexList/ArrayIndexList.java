package ds.indexList;

/**
 * 
 * @author pedroi.rivera-vega
 *
 * @param <E>
 */
public class ArrayIndexList<E> implements IndexList<E> {
	
	//Private Fields
	private static final int INITIAL_CAPACITY = 10; 
	private E[] elements;  // the internal array
	private int currentSize;   //Current number of elements in the list
	
	
	//Constructor
	@SuppressWarnings("unchecked")
	public ArrayIndexList() { 
		elements = (E[]) new Object[INITIAL_CAPACITY]; 
		currentSize = 0; 
	}

	
	/*MEMBER METHODS FOR ArrayIndexList ADT*/
	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= currentSize)
            throw new IndexOutOfBoundsException("Invalid index for GET operation, index = " + index); 

        // if here, then the index is valid and such element exist in the list
        // the element is located in arr[index]
        
        return elements[index];
	}

	@Override
	
	public E remove(int index) throws IndexOutOfBoundsException {
		
		/** 
		 * NOTE: 
		 * 
		 * We are not dealing here with the possible need to adjust the size of
		 * the internal array if too much space is detected as unused after the remove is completed. 
		 * 
		 * That is something that you may want to think about: When to do it and how.....
		 */
		
        if (index < 0 || index >= currentSize)
            throw new IndexOutOfBoundsException("Invalid index for REMOVE operation, index = " + index); 

        /* If we get here, then the index is valid and such element exist in the list*/
		E etr = elements[index];     // element to be removed
		
		/*Now we shift elements from position index+1 to size-1 one position to the left*/
		for (int i=index; i<currentSize-1; i++)
			elements[i] = elements[i+1]; 
		
		elements[currentSize-1] = null;    //We set the element to remove to null to avoid memory leaks
		
		currentSize--;    // Decrement current size of list 
		
        return etr;
	}

	@Override
	public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= currentSize)
            throw new IndexOutOfBoundsException("Invalid index for SET operation, index = " + index); 

        // if here, then the index is valid and such element exist in the list

        E etr = elements[index];     // element to be replaced
		
		elements[index] = e;     // element replaced by e
		
		return etr;
	}

	@Override
	public void add(int index, E e) throws IndexOutOfBoundsException {
		
		/*If the index is not from [0, size() -1] then we throw an exception*/
        if (index < 0 || index > currentSize)
            throw new IndexOutOfBoundsException("Invalid index for ADD operation, index = " + index); 

        /**
         * If we have a valid index, then such element can exist in the list.
         * 
         * But now, we need to check if the list is full
         * If it is, we need to re-allocate more space for our new element to come
         * 
         * This is because arrays occupy a contiguous block of memory, instead of adding as needed
         */
        if (currentSize == elements.length)
        	doubleCapacity(); 

        /**
         * Now that we have reallocated new space for more elements,
         * we need to open space for the new element, so move all elements in position:
         * 		index, index+1, ..., size-1
         * 
         * How many times and where? Only one position to the right in the array. 
         */ 
        for (int i = currentSize-1; i>=index; i--) 
        	elements[i+1] = elements[i]; 
        
        /** 
         * Now that all the required elements have been shifted to the right, 
         * the position index is available for the new item, 
         * without losing no pre-existing elements. 
         */
        elements[index] = e; 
        
        currentSize++; 
		
	}

	@Override
	public void add(E e) {
		/**
		 * Add at the end of the list,
		 * remember that a list DOES have a notion of order
		 */ 
		add(currentSize, e);
		
	}

	
	/**
	 * Doubles the capacity of the internal array, while keeping all the current elements. 
	 */
	@SuppressWarnings("unchecked")
	private void doubleCapacity() { 
		int newCapacity = 2 * elements.length;
		E[] newArr = (E[]) new Object[newCapacity];
		
		for (int i = 0; i < currentSize; i++) { 
			newArr[i] = elements[i]; 
			elements[i] = null; 
		}
		
		elements = newArr; 
	}

	
	/***
	@Override
	public int removeAll(E e) {
		
		
		//NOTE: THIS IMPLEMENTATION IS NOT CORRECT (THE ALGORITHM DOES NOT WORK CORRECTLY) 
		
		//Analyze, why does this implementation fail to work?

		int countRM = 0; 
		
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).equals(e)) {
				this.remove(i); 
				countRM++; 
			}
		}
		return countRM;
	}
	
	***/
	
	/**
	@Override
	public int removeAll(E e) {

		// This one works, but not efficient!!! Why???
		 
		int countRM = 0; 
		for (int i=size-1; i>=0; i--) {
			if (this.get(i).equals(e)) {
				this.remove(i); 
				countRM++; 
			}
		}
		return countRM;
	}
	**/
	
	@Override
	public int removeAll(E e) {
		/** This one works, and is efficient too! Why???
		 * 
		 * Although, perhaps, little harder to understand, 
		 * you should be able to do it after careful analysis. 
		 * 
		 * As it moves forward in the loop, elements are shifted to the final
		 * position to the left, as many positions as needed.
		 * 
		 * Remember that elements remaining must keep the same relative 
		 * order in the final list
		 * 
		 * This is because this is a list, not a bag. 
		 */
		int countRM = 0; 
		for (int i = 0; i < currentSize; i++) {
			if (elements[i].equals(e)) {
				countRM++; 
				elements[i] = null; 
			}
			else if (countRM > 0){
				elements[i - countRM] = elements[i];   // arr[i] is shifter countRM positions to the left...
				elements[i] = null; 
			}
		}
		
		currentSize = currentSize - countRM; 
		
		return countRM;
	}




}
