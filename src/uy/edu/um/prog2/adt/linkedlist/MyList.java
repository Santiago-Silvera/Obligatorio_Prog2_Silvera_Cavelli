package uy.edu.um.prog2.adt.linkedlist;

public interface MyList<T> extends Iterable<T> {

    /**
     * Adds a new element to the list.
     * @param value the element to add
     */
    void add(T value);

    /**
     * Retrieves the element at the given position.
     * @param position the position of the element to retrieve
     * @return the element at the given position
     */
    T get(int position);

    /**
     * Checks if the list contains the given element.
     * @param value the element to check
     * @return true if the element exists, false otherwise
     */
    boolean contains(T value);

    /**
     * Removes the given element from the list.
     * @param value the element to remove
     */
    void remove(T value);

    /**
     * Gets the size of the list.
     * @return the size of the list
     */
    int size();
}
