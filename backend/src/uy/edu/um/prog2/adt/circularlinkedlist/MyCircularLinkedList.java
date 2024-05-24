package uy.edu.um.prog2.adt.circularlinkedlist;

public interface MyCircularLinkedList<T> {
    void add(T value);

    T getCircularLogic(int position);

    boolean contains(T value);

    void remove(T value);

    int size();
}
