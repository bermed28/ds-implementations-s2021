package ds.dictionary;

import java.util.ArrayList;
import java.util.Iterator;


public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	
	private static final int INITCAP = 4; 
	
	private MyEntry<K, V>[] arr;    
	// entries are sorted by value of the key in nondecreasing order
	// arr[i].getKey().compareTo(arr[i+1].getKey()) <= 0, i=0, ..., size-2
	
	private int size; 
	
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

	public SortedArrayDictionary() { 
		this(INITCAP); 
	}
	
	@SuppressWarnings("unchecked")
	public SortedArrayDictionary(int cap) { 
		if (cap < INITCAP) 
			cap = INITCAP; 
		arr  = (MyEntry[]) new MyEntry[cap]; 
		size = 0; 
	}
	
	@Override
	public V add(K key, V value) {
		int index = locateIndex(key); 
		if (index < size && arr[index].getKey().compareTo(key) == 0) { 
			return arr[index].setValue(value); 
		} // key not found
		else { 
			// need to add a new entry to the array
			if (arr.length == size) 
				increaseCapacity(); 
			
			MyEntry<K, V> newEntry = new MyEntry<>(key, value); 
			
			// open space for the new entry in the array
			for (int i = size-1; i>= index; i--)
				arr[i+1] = arr[i];     // copy one position to the right in the array
			
			arr[index] = newEntry; 
			
			size++; 
			
			return null; 
		}
	}

	@Override
	public V remove(K key) {
		int index = locateIndex(key); 
		if (index < size && arr[index].getKey().compareTo(key) == 0) { 
			// entry with this key is found at position arr[index]
			V vtr = arr[index].getValue(); 
			for (int i=index; i<size-1; i++)
				arr[i] = arr[i+1]; 
			arr[size-1] = null; 
			size--; 
			
			return vtr; 
		}	
		else 
			return null; 
	}

	@Override
	public V getValue(K key) {
		int index = locateIndex(key); 
		if (index < size && arr[index].getKey().compareTo(key) == 0)
			return arr[index].getValue(); 
		else 
			return null; 
	}

	@Override
	public boolean contains(K key) {
		int index = locateIndex(key); 
		return (index < size && arr[index].getKey().compareTo(key) == 0); 
	}

	@Override
	public Iterator<K> getKeyIterator() {
		ArrayList<K> keyList = new ArrayList<>(); 
		
		for (int i=0; i<size; i++)        // had an error here - can't do: for(Entry..e: arr)...
			keyList.add(arr[i].getKey()); 
		
		return keyList.iterator();
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

	/**
	 * Searches the sorted array for an entry whose key is 
	 * equal a key. 
	 * @param key to search for
	 * @return 0 <= index < size of 
	 *   arr[index].getKey().compareTo(key) == 0 
	 *   if not found, the index that it returns  is the location
	 *   where it should be if added
	 *
	 */
	private int locateIndex(K key) { 
		int index = 0; 
		while (index < size && arr[index].getKey().compareTo(key) < 0)
			index++; 
		return index; 
	}
	
	@SuppressWarnings("unchecked")
	private void increaseCapacity() { 
		MyEntry<K, V>[] newArr = (MyEntry<K, V>[]) new MyEntry[2*arr.length];
		
		for (int i = 0; i<size; i++) { 
			newArr[i] = arr[i]; 
			arr[i] = null; 
		}
		
		arr = newArr; 
	
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		ArrayList<Entry<K, V>> entryList = new ArrayList<>(); 
		
		for (Entry<K, V> entry : arr) 
			entryList.add(entry);
		 
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
	
}
