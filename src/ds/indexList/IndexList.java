package ds.indexList;

/**
 * 
 * @author pedroi.rivera-vega
 *
 * @param <E>
 */
public interface IndexList<E> { 
	   /** Determines the size of the current list instance. 
	@return number of elements in the list
	   **/ 
	   int size(); 


	   /** Determines if the list is empty or not. 
	       @returns true if empty, false if not. 
	   **/ 
	   boolean isEmpty(); 


	   /** Accesses an element in the list that is located at the
	       position whose index is given.
	       @param index the index value of the position
	       whose element is being requested
	       @throws IndexOutOfBoundsException whenever the value
	       of index is not in the valid range from 0 to size-1.
	       @return reference to the particular element	
	   **/ 
	   E get(int index) throws IndexOutOfBoundsException; 


	   /** Removes an element from the list that is located at the
	       position whose index is given. No other element on the
	       current instance is affected. All remaining elements 
	       maintain the same relative order. Any current member 
	       (before the operation is applied to the list) that 
	       is located at a position with index larger than the index
	       of the element to remove will occupy the position whose
	       index is one unit less than its current position. The size
	       of the list is reduced by 1. 
	       @param index the index value of the position
	       whose element is being deleted
	       @throws IndexOutOfBoundsException whenever the value
	       of index is not in the valid range from 0 to size-1.
	       @return reference to the deleted element 	
	   **/ 
	   E remove(int index) throws IndexOutOfBoundsException; 


	   /** Replaces an element in the list that is located at the
	       position whose index is given by a new value, also given.
	       No other element in the list is affected, and its size
	       remains the same. 
	       @param index the index value of the position
	       whose element is being replaced
	       @param e reference to the new (replacing) element
	       @throws IndexOutOfBoundsException whenever the value
	       of index is not in the valid range from 0 to size-1.
	       @return reference to the element that was replaced	
	   **/ 
	   E set(int index, E e) throws IndexOutOfBoundsException; 


	   /** Adds a new element to the list. The new element will 
	       occupy the position whose index is given. Any current
	       member of the list that is located at a position 
	       with index larger than, or equal to, the index for the
	       new element to add will occupy the position whose
	       index is one unit more than its current position. The size
	       if the list is increased by 1. 
	       @param index the index value of the position to be
	       occupied by the new element
	       @param e reference to the new element
	       @throws IndexOutOfBoundsException whenever the value
	       of index is not in the valid range from 0 to size.
	       @return reference to the element that was replaced	
	   **/ 
	   void add(int index, E e) throws IndexOutOfBoundsException; 


	   /** Appends a new element to the list. The size of the list
	       is increased by 1.
	       @param e reference to the new element
	   **/ 
	   void add(E e); 
	   
	   /**
	    * Removes al occurrences of a given element in the list. 
	    * @param e the element to be removed; all those equal to e
	    * @return number of removals
	    */
	   int removeAll(E e);

	}

