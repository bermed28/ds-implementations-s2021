package ds.dictionary;

import java.util.ArrayList;
import java.util.Iterator;


public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	
	@SuppressWarnings("hiding")
	private class MyEntry<K extends Comparable<? super K>, V> implements Entry<K, V> {

		private K key; 
		private V value; 
		
		public MyEntry(K key, V value) { 
			this.key = key; 
			this.value = value; 
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		} 
		
		public V setValue(V value) { 
			V old = this.value; 
			this.value = value; 
			return old; 
		}
		
		public String toString() { 
			return "[" + key + ", " + value + "]"; 
		}
	}

	private class Node { 
		private MyEntry<K, V> data; 
		private Node next; 
		
		public Node(MyEntry<K, V> data, Node next) { 
			this.data = data; 
			this.next = next; 
		}
		
		public MyEntry<K, V> getData() {
			return data;
		}
		public void setData(MyEntry<K, V> data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	    public void clear() { 
	    	data = null; 
	    	next = null; 
	    }
	}

	// instance fields of class SortedLinkedDictionary
	private Node first; 
	private int size;
	
	public SortedLinkedDictionary() { 
		first = null; 
		size = 0; 
	}

	@Override
	public V add(K key, V value) {

		Node p = null;  // to be used as needed to store the reference to the
		                // node that precedes the node with an existing entry
		                // with this key or the node where it should be added.
		
		if (size > 0) {
			// list is not empty 
			p = locatePrevNode(key);   // get the proper value of p
			// if p == null then the searched entry is at the first node
			// or it should be added as the first node of the sorted linked list.
			if (p == null && first.getData().getKey().compareTo(key) == 0) { 
				// An entry with the given key actually exists at the first node
				// of the list. The value of such entry must be replace by
				// parameter value and the old value is returned. 
				// See method setValue in the inner class MyEntry
				return first.getData().setValue(value); 
			}
			else if (p != null && p.getNext() != null)
				if (p.getNext().getData().getKey().compareTo(key) == 0) {
					// An entry with the given key actually exists at the node
					// after node p. The value of such entry must be replace by
					// parameter value and the old value is returned. 
					// See method setValue in the inner class MyEntry
					return p.getNext().getData().setValue(value); 
				}
		}

		// If it reaches here, then the new pair (key, value) needs
		// to be added as a new entry in the dictionary; so, insert the node
		// at the proper location in the sorted linked list; which is
		// going to be a new first node or a new node after node p.
		if (p == null) {
			// list is empty or the new entry must be added as the first
			// entry in the sorted linked list... a new first node is 
			// inserted.
			first = new Node(new MyEntry<K, V>(key, value), first); 
		}
		else {
			// The new entry must be added as part of a new node to be
			// inserted right after node p in the sorted linked list. 
			p.setNext(new Node(new MyEntry<K, V>(key, value), p.getNext())); 
		}

		size++; 
		return null; 
		
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue(K key) {
		if (size > 0) {
			// list is not empty 
			Node p = locatePrevNode(key); 
			if (p == null && first.getData().getKey().compareTo(key) == 0) { 
				return first.getData().getValue(); 
			}
			else if (p != null && p.getNext() != null)
				if (p.getNext().getData().getKey().compareTo(key) == 0) 
					return p.getNext().getData().getValue(); 
		}
		
		// no entry with the given key exists in this dictionary
		return null; 
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		ArrayList<Entry<K, V>> entryList = new ArrayList<>(); 
		
		Node current = first; 
		while (current != null) {
			entryList.add(current.getData()); 
			current = current.getNext(); 
		}
		
		return entryList;    // Because ArrayList<..> is Iterable<..>

	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> values() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Locates the node that precedes the position in the list where the entry
	 * with this key is located or will fit. 
	 * @param key the key to search for.
	 * @return null if the key is at the first node of the list or that it would
	 * be located there if not found. 
	 * If the returned value is not null, then it corresponds to the node that
	 * precedes its location, or where it should be added in the linked list if not 
	 * there already. 
	 */
	private Node locatePrevNode(K key) { 
		Node p = null; 
		Node current = first; 
		while (current != null && current.getData().getKey().compareTo(key) < 0) {
			p = current; 
			current = current.getNext(); 
		}
		return p; 
	}
}
