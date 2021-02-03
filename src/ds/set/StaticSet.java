package ds.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of Static-Sized Set using Set ADT & Array
 * @author bermed28
 *
 * @param <E>
 */
public class StaticSet<E> implements Set<E> {

	private int currentSize;
	private E[] elements;
	
	@SuppressWarnings("unchecked")
	public StaticSet(int maxCapacity) {
		if (maxCapacity < 1)
			throw new IllegalArgumentException("Max capacity must be at least 1");
		
		currentSize = 0;
		elements = (E[]) new Object[maxCapacity];
	}
	
	
	private class SetIterator<T> implements Iterator<T> {
		private int currentPosition;

		public SetIterator() {
			this.currentPosition = 0;
		}

		@Override
		public boolean hasNext() {
			return this.currentPosition < size();
		}

		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			if (this.hasNext()) {
				T result = (T) elements[this.currentPosition++];
				return result;
			}
			else
				throw new NoSuchElementException();				
		}
	}
	
	
	@Override
	public boolean add(E o) {
		if(size() == elements.length) 
			throw new IllegalArgumentException("Set is full"); 
		
		else if(isMember(o)) 
			return false;
		
		else {
			elements[currentSize++] = o;
			return true;
		}
	}

	@Override
	public boolean remove(E o) {
		
		for (int i = 0; i < size(); i++) {
			if (elements[i].equals(o)) {
				elements[i] = elements[--currentSize];
				elements[currentSize] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		
		for (int i = 0; i < size(); i++) {
			elements[i] = null;
		}
		
		currentSize = 0;

	}

	@Override
	public boolean isMember(E o) {
		
		for (int i = 0; i < size(); i++) {
			if (elements[i].equals(o)) {
				return true;
			}
		}
		return false;
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
	public boolean isSubSet(Set<E> S2) {
		
		for (E elm : this) {
			if(!S2.isMember(elm)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<E> union(Set<E> S2) {
		Set<E> S3 = new StaticSet<>(this.size() + S2.size());
		
		for (E e : this) 
			S3.add(e);
		
		
		for (E e : S2) {
			if(!S3.isMember(e)) {
				S3.add(e);
			}
			
		}
		
		return S3;
		
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		return this.difference(this.difference(S2));
		
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		
		Set<E> S3 = new StaticSet<>(size() + S2.size());
		
		for (E e : this) {
			if(!S2.isMember(e)) {
				S3.add(e);
			}
		}
		
		
		return S3;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new SetIterator<E>();
	}

}
