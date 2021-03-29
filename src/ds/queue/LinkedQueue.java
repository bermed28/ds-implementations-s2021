package ds.queue;


public class LinkedQueue<E> implements Queue<E> {

	private static class Node<E> {    
		private E element; 
		private Node<E> next; 

		public Node(E elm) {
			this.element = elm;
			this.next = null;
		}

		public Node(E elm, Node<E> nextNode) {
			this.element = elm;
			this.next = nextNode;
		}

		public Node() {
			this.element = null;
			this.next = null;
		}

		public E getElement() {
			return this.element;
		}

		public Node<E> getNext(){
			return this.next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void setElement(E elm) {
			this.element = elm;
		}

		public void clear() {
			this.element = null;
			this.next = null;
		}
	} // END CLASS NODE

	private Node<E> first, last;   // references to first and last node
	private int size; 

	public LinkedQueue() {         // initializes instance as empty queue
		first = last = null; 
		size = 0; 
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}

	public E getFront() throws EmptyQueueException {
		if (isEmpty()) 
			throw new EmptyQueueException("Queue is empty");
		return first.getElement(); 
	}

	public E dequeue() throws EmptyQueueException {
		E etr = getFront();     // throws the EST if needed.		
		Node<E> rmNode = first;
		first = first.getNext();
		rmNode.clear();
		size--;
		return etr; //Dummy Return
	}

	public void enqueue(E e) {
		Node<E> newNode = new Node<>(e, null);
		if (size == 0) {
			first = last = newNode; 
			last.setNext(null);

		} else { 
			last.setNext(first);
			last.setNext(newNode);
			last = newNode;

		}
		size++; 
	}
}