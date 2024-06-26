package uy.edu.um.prog2.adt.linkedlist;

import uy.edu.um.prog2.adt.circularlinkedlist.MyCircularLinkedList;
import uy.edu.um.prog2.adt.queue.EmptyQueueException;
import uy.edu.um.prog2.adt.queue.MyQueue;
import uy.edu.um.prog2.adt.stack.EmptyStackException;
import uy.edu.um.prog2.adt.stack.MyStack;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedListImpl<T> implements MyList<T>, MyQueue<T>, MyStack<T>, MyCircularLinkedList<T> {

    private Node<T> first;

    private Node<T> last;

    public MyLinkedListImpl() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(T value) {
        addToTheEnd(value);
    }

    private void addToBeginning(T value) {
        if (value != null) {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null) { // si la lista es vacia
                this.first = elementToAdd;
                this.last = elementToAdd;
            } else { // en caso de no ser vacia se agrega al comienzo
                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }
        } // si el elemento es vacio se ignora
    }

    private void addToTheEnd(T value) {
        if (value != null) {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null) { // si la lista es vacia
                this.first = elementToAdd;
            } else { // en caso de no ser vacia se agrega al final
                this.last.setNext(elementToAdd);
            }
            this.last = elementToAdd;

        } // si el elemento es vacio se ignora
    }


    @Override
    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;
        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {
            temp = temp.getNext();
            tempPosition++;
        }
        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {
            assert temp != null;
            valueToReturn = temp.getValue();
        }
        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;
        while (temp != null && !temp.getValue().equals(value)) {
            temp = temp.getNext();
        }
        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor
            contains = true;
        }
        return contains;
    }

    @Override
    public void remove(T value) {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;
        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {
            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();
        }

        if (searchValue != null) { // si encontre el elemento a eliminar
            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {
                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero
                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.
                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {
                assert beforeSearchValue != null;
                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;
                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last) {
                this.first = null;
                this.last = null;
            } else { // resto de los casos
                assert beforeSearchValue != null;
                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);
            }
        } // Si no es encuentra el valor a eliminar no se realiza nada
    }

    private T removeLast() { // esta operación remueve el último elemento y retorna el elemento eliminado
        T valueToRemove = null;
        if (this.last != null) {
            valueToRemove = this.last.getValue();
            remove(valueToRemove);
        }
        return valueToRemove;
    }

    @Override
    public int size() {
        int size = 0;
        Node<T> temp = this.first;
        while (temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    // Operaciones particulares a Queue
    @Override
    public void enqueue(T value) {
        addToBeginning(value);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.last == null) { // si la queue esta vacia
            throw new EmptyQueueException();
        }
        return removeLast();
    }

    // Operaciones particulares a Stack
    @Override
    public void push(T value) {
        addToTheEnd(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.last == null) { // si la pila esta vacia
            throw new EmptyStackException();
        }
        return removeLast();
    }

    @Override
    public T peek() {
        T valueToReturn = null;
        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }
        return valueToReturn;
    }

    // Operaciones particulares a CircularLinkedList
    @Override
    public T getCircularLogic(int position) {
        if (size() == 0) {
            return null;
        }
        return get((position % size() + size())% size()); // se corrige sumando size() para evitar que resulte en número negativo
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> temp = first;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T valueToReturn = temp.getValue();
                temp = temp.getNext();
                return valueToReturn;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        MyList.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return MyList.super.spliterator();
    }
}
