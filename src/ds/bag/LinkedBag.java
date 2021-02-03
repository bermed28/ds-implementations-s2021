package ds.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pedroirivera-vega
 *
 * This is an implementation of the Bag that is based on the 
 * singly linked list (SLL). 
 * 
 * Plan on how to use the SLL: Every instance of this type
 * will have a reference pointing to the first node of the 
 * SLL that will hold its elements If the bag has no elements, then
 * the SLL has no nodes and that first reference has to be null. 
 * If the bag has n elements, then the SLL will have n nodes, where 
 * each node will reference (as its data element) an element of the
 * bag. Every node in the list, except the last one, also references the
 * node that follows in the SLL (field next). The last node in the 
 * SLL has that field "next" set to null.  
 * 
 * This implements a dynamic bag of unlimited capacity. The internal
 * SLL will grow as new elements are added, and will shrink as elements 
 * are removed. 
 */
public class LinkedBag<T> implements Bag<T> {
	// the inner class for the data type of nodes in the singly linked list
	private class Node { 
		private T data;       // reference to the data element that the node holds
		private Node next;    // reference to the next node in the linked list
		private Node(T data, Node next) { 
			this.data = data; 
			this.next = next; 
		}
		private void clear() { 
			data = null; 
			next = null; 
		}
	}

	private Node first;    // reference to the first node of the SLL holding
	                       // the elements of this bag. Notice that "first" is just
						   // a reference to a node (not a node!).
	private int size;      // size of the bag.
	
	/**
	 * Initializes an empty bag of type LinkedBag. 
	 */
	public LinkedBag() { 
		first = null;     // the SLL has no nodes in an empty bag of this type
		size = 0;         // the size is 0
	}
	private class BagIterator<T> implements Iterator<T>{
		
		Node currentPosition = null;
		
		public BagIterator() {
			currentPosition = first;
		}
		

		public boolean hasNext() {
			return currentPosition != null;
		}

	
		@SuppressWarnings("unchecked")
		public T next() {
			if(this.hasNext()) {
				Node ntr = currentPosition; // Node to return
				currentPosition = currentPosition.next;
				return (T) ntr.data;
			} else {
				throw new NoSuchElementException();
			}
		}	
		
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;      // we can also use: return first == null; 
	}

	@Override
	public boolean add(T newEntry) {
		// The element can be added anywhere in the SLL. 
		// The easiest would be to add a new node that will reference to
		// the new element and which shall inserted as the new first
		// node in the SLL. The instance variable "first" is updated to
		// reference that new node, and the "next" part of that new node
		// is made to reference what used to be the first node in the SLL. 
		
		Node newNode = new Node(newEntry, first); 
		first = newNode; 
		size++; 
		return true;
	}

	
	@Override
	public T remove() {
		// Remember that this method can remove any element of the bag; 
		// if the bag is not empty. So, the easiest is to remove the 
		// first node. 
		
		if (isEmpty()) return null; 
		
		// if it reaches here, then the list is not empty.
		Node ntr = first;   // node to remove		
		first = first.next;  // the new first element is the node that follows
		                     // in the SLL. 
		
		size--;    // adjust the size.
		
		T etr = ntr.data;   // element to be removed. Save before clearing the node. 
		ntr.clear();     // Good cleaning habits to support the JVM Garbage 
		                 // Collector (GC). Just make null all the fields of 
		                 // the node being removed from the SLL. 

		return etr;
	}

	@Override
	public boolean remove(T anEntry) {
		Node target = findNode(anEntry); 
		if (target == null)
			return false;    // no such entry in this instance of the bag
		
		// if here, it is because target is the first node in the SLL that contains
		// reference to element equals to anEntry. Our strategy here is to 
		// copy whatever element is now at the first node in the SLL (if not
		// equal to target node. 
		
		// Check if it is the first, since it is a special case. 
		if (target != first) 
			target.data = first.data;   // just replace the data in target node
			
		// First node will be physically removed from the SLL. 
		Node ntr = first;     // this is the node that will be physically removed.
		first = first.next;   // the new first node of the SLL will be the actual second node
		                      // if any. 
		
		size--;   // properly adjust size ... one element is being removed!
		
		
		// clear ntr - Notice that this is not strictly needed. But it is a little 
		// help the the JVM GC. 
		ntr.clear(); 
		
		return true;
	}

	@Override
	public void clear() {
		// remove all nodes from the linked list...
		
		while (first != null) { 
			Node ntr = first; 
			first = first.next; 
			ntr.clear();
		}
		size = 0; 
		
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		// Just traverse the whole SLL and count how many times the 
		// element "anEntry" is being found. 
		int count = 0; 
		Node current = first; 
		while (current != null) {
			if (current.data.equals(anEntry)) 
				count++; 
			current = current.next; 
		}
		return count;
	}

	@Override
	public boolean contains(T anEntry) {
		return findNode(anEntry) != null;
	}

	@Override
	public T[] toArray() { 
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[size]; 
		Node current = first; 
		for (int index = 0; current != null; index++, current = current.next)
			result[index] = current.data; 
		return result;
	}

	/**
	 * Internal private method to find the first node, if any, 
	 * that contains a given element. 
	 * @param target the element to search for.
	 * @return null if target is nod found in the SLL; otherwise, 
	 * it returns the first node that references an element
	 * that is equals to target. 
	 */
	private Node findNode(T target) { 
		Node current = first; 
		while (current != null) 
			if (current.data.equals(target))
				return current; 
			else 
				current = current.next; 
		
		return null;    // when the list is empty target is not in there...
	}

	@Override
	public int removeAll(T elm) {
		// TODO Auto-generated method stub
		int count = 0;
		while(remove(elm))
			count++;
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new BagIterator<T>();
	}

	
	@Override
	public void bagAdjuster(int n) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean equals(Bag<T> b2) {
		// TODO Auto-generated method stub
		return false;
	}
}
