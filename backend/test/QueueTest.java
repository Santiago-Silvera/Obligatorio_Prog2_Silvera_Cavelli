import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.queue.EmptyQueueException;
import uy.edu.um.prog2.adt.queue.MyQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    MyQueue<Integer> queue;
    @Before
    public void setUp() {
        queue = new MyLinkedListImpl<>();
        assertEquals(queue.size(), 0);
    }

    @Test
    public void sizeTest() {
        queue.enqueue(1);
        assertEquals(queue.size(), 1);
        try {
            queue.dequeue();
        } catch (Exception ignored) {
            fail("Error con dequeue().");
        }
        assertEquals(queue.size(), 0);
    }

    @Test
    public void enqueueTest() {
        // Se agrega un elemento al queue
        queue.enqueue(22);
        // El queue debe contener el elemento agregado
        assertTrue(queue.contains(22));
        // El tamaño del queue debe ser 1
        assertEquals(queue.size(), 1);
    }

    @Test
    public void dequeueTest() {
        // Se agrega un elemento al queue
        queue.enqueue(1);
        // Se elimina el elemento del queue
        try {
            Integer dequeuedElement = queue.dequeue();
            // El elemento eliminado debe ser el mismo que se agrego
            assertEquals(dequeuedElement, (Integer) 1);
            // El queue debe estar vacio
            assertEquals(queue.size(), 0);
        } catch (EmptyQueueException e) {
            fail("Error con dequeue(): " + e.getMessage());
        }
        assertThrows(EmptyQueueException.class, () -> queue.dequeue());
    }

    @Test
    public void containsTest() {
        // Se agrega un elemento al queue
        queue.enqueue(1);
        // El queue debe contener el elemento agregado
        assertTrue(queue.contains(1));
        // El queue no debe contener un elemento que no se ha agregado
        assertFalse(queue.contains(2));
    }
}
