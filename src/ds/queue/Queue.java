package ds.queue;

public interface Queue<E> {		  
	   int size();
	   boolean isEmpty();	   
	   E getFront() throws EmptyQueueException;
	   void enqueue(E element);
	   E dequeue() throws EmptyQueueException;
}