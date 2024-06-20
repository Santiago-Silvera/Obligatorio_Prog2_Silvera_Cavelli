package uy.edu.um.prog2.adt.queue;

public interface MyQueue<T> {

	/**
	 * Adds a new element to the end of the queue.
	 *
	 * @param value the element to add
	 */
	void enqueue(T value);

	/**
	 * Removes the element at the front of the queue.
	 *
	 * @return the element that was removed
	 * @throws EmptyQueueException if the queue is empty
	 */
	T dequeue() throws EmptyQueueException;

	/**
	 * Retrieves the element at the front of the queue.
	 *
	 * @return the element at the front of the queue
	 */
	boolean contains(T value);

	/**
	 * Gets the size of the queue.
	 * @return the size of the queue
	 */
	int size();
	
}
