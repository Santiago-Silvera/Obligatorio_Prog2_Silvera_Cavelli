
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.stack.EmptyStackException;
import uy.edu.um.prog2.adt.stack.MyStack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    // Preparamos el stack para todos los test unitarios
    MyStack<Integer> stack;
    @Before
    public void setUp() {
        stack = new MyLinkedListImpl<>();
    }

    @Test
    public void sizeTest() {
        // Al inicio el stack debe contener 0 elementos
        assertEquals(stack.size(), 0);

        // Tras agregar un elemento el stack debe tener un tamaño de 1
        stack.push(1);
        assertEquals(stack.size(), 1);

        // Tras eliminar un elemento debe volver a tener 0 elementos
        try {
            stack.pop();
        } catch (Exception ignored) {
            // Dada la logica del test esta rama no se debe poder alcanzar
            fail("Error con pop().");
        }
        assertEquals(stack.size(), 0);
    }

    @Test
    public void pushTest() {
        // Al inicio el stack debe estar vacio
        assertNull(stack.peek());
        assertEquals(stack.size(), 0);

        // Tras agregar un elemento el stack debe tener un tamaño de 1
        stack.push(22);
        assertEquals(stack.peek(), (Integer) 22);
        assertEquals(stack.size(), 1);
    }

    @Test
    public void popTest() {
        // Agregar un elemento a la pila
        stack.push(1);

        // Al eliminar un elemento, la pila debe volver a estar vacía
        try {
            Integer poppedElement = stack.pop();
            assertEquals(poppedElement, (Integer) 1);
            assertEquals(stack.size(), 0);
        } catch (EmptyStackException e) {
            fail("Error con pop(): " + e.getMessage());
        }

        // Intentar eliminar un elemento de una pila vacía debe lanzar una excepción
        try {
            stack.pop();
            fail("Se esperaba EmptyStackException");
        } catch (EmptyStackException ignored) {
            // Se esperaba esta excepción, por lo que la prueba pasa
        }
    }

    @Test
    public void peekTest() {
        // Empujar un elemento a la pila
        stack.push(1);
        // Verificar si el elemento superior de la pila es el que acabamos de empujar
        assertEquals(stack.peek(), (Integer) 1);
        // Verificar si el tamaño de la pila es 1 después de empujar un elemento
        assertEquals(stack.size(), 1);

        // Empujar otro elemento a la pila
        stack.push(2);
        // Verificar si el elemento superior de la pila es el que acabamos de empujar
        assertEquals(stack.peek(), (Integer) 2);
        // Verificar si el tamaño de la pila es 2 después de empujar otro elemento
        assertEquals(stack.size(), 2);
    }
}
