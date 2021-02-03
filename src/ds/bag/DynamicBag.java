package ds.bag;

import java.util.Iterator;

public class DynamicBag<E> implements Bag<E> {
	
	private int maxCapacity;
	private StaticBag<E> bag;
	
	
	public DynamicBag(int initialCapacity) {
	
		bag = new StaticBag<E>(initialCapacity);
		maxCapacity = initialCapacity;
	
	}
	

	@Override
	public boolean add(E o) {
		
		if(maxCapacity == bag.size()) {
			//reallocate
			maxCapacity *= 2;
			StaticBag<E> newBag = new StaticBag<E>(maxCapacity);
			
			for (E oldElm : bag) {
				newBag.add(oldElm);
			}
			
			bag = newBag;
		}
		
		return bag.add(o);
		
	}

	@Override
	public boolean remove(E o) {
		return bag.remove(o);
	}
	
	@Override
	public E remove() {
		return bag.remove();
	}

	@Override
	public int removeAll(E o) {
		return bag.removeAll(o);
	}

	@Override
	public void clear() {
		bag.clear();
	}

	@Override
	public int getFrequencyOf(E o) {
		return bag.getFrequencyOf(o);
	}

	@Override
	public boolean contains(E o) {
		return bag.contains(o);
	}

	@Override
	public int size() {
		return bag.size();
	}

	@Override
	public boolean isEmpty() {
		return bag.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return bag.iterator();
	}


	@Override
	public E[] toArray() {
		return bag.toArray();
	}


	@Override
	public void bagAdjuster(int n) {
		bag.bagAdjuster(n);
	}


	@Override
	public boolean equals(Bag<E> b2) {
		// TODO Auto-generated method stub
		return bag.equals(b2);
	}
}
