package ds.set;

import java.util.Iterator;
/**
 * Implementation of Dynamic Sized-Set using object composition
 * @author Fernando Bermudez
 *
 * @param <E>
 */
public class DynamicSet<E> implements Set<E> {
	
	int maxCapacity;
	StaticSet<E> set;
	
	
	public DynamicSet(int initialCapacity) {
	
		this.maxCapacity = initialCapacity;
		this.set = new StaticSet<>(initialCapacity);
	
	}
	

	@Override
	public Iterator<E> iterator() {
		return set.iterator();
	}

	@Override
	public boolean add(E o) {
		
		if(this.size() == maxCapacity) {
			maxCapacity *= 2;
			StaticSet<E> newSet = new StaticSet<>(maxCapacity);
			
			for (E e : set) {
				newSet.add(e);
			}
			
			set = newSet;
		}
		
		return set.add(o);
			
	}

	@Override
	public boolean remove(E o) {
		return set.remove(o);
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public boolean isMember(E o) {
		return set.isMember(o);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean isSubSet(Set<E> S2) {
		return set.isSubSet(S2);
	}

	@Override
	public Set<E> union(Set<E> S2) {
		return set.union(S2);
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		return set.intersection(S2);
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		return set.difference(S2);
	}

}
