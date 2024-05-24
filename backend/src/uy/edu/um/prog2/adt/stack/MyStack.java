package uy.edu.um.prog2.adt.stack;
public interface MyStack<T> {

	/**
	 * Adds a new element to the top of the stack.
	 *
	 * @param value the element to add
	 */
	void push(T value);

	/**
	 * Removes the element at the top of the stack.
	 *
	 * @return the element that was removed
	 * @throws EmptyStackException if the stack is empty
	 */
	T pop() throws EmptyStackException;

	/**
	 * Retrieves the element at the top of the stack.
	 *
	 * @return the element at the top of the stack
	 */
	T peek();

	/**
	 * Gets the size of the stack.
	 *
	 * @return the size of the stack
	 */
	int size();
	
}
