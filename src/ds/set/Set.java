package ds.set;

public interface Set<E> extends Iterable<E>{
	
	public boolean add(E o);
	public boolean remove(E o);
	public void clear();
	public boolean isMember(E o);
	public int size();
	public boolean isEmpty();
	public boolean isSubSet(Set<E> S2);
	public Set<E> union(Set<E> S2);
	public Set<E> intersection(Set<E> S2);
	public Set<E> difference(Set<E> S2);
	
}
