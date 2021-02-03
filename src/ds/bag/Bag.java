package ds.bag;

public interface Bag<E> extends Iterable<E> {
	
	public boolean add(E o);
	public boolean remove(E o);
	public E remove();
	public int removeAll(E o);
	public void clear();
	public int getFrequencyOf(E o);
	public boolean contains(E o);
	public int size();
	public boolean isEmpty();
	public E[] toArray();
	void bagAdjuster(int n);
	boolean equals(Bag<E> b2);
	
	
}
