import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyHeapImpl;

import static org.junit.Assert.*;

public class HeapTest {

    MyHeap<Integer> minHeap;
    MyHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        minHeap = new MyHeapImpl<>(true);  // Min-Heap
        maxHeap = new MyHeapImpl<>(false); // Max-Heap
    }

    @Test
    public void insertMinHeapTest() {
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        assertEquals(1, (int) minHeap.get()); //el elemento mínimo debería estar arriba
        assertEquals(4, minHeap.size()); //el tamaño del heap debería ser 4
    }

    @Test
    public void insertMaxHeapTest() {
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);

        assertEquals(8, (int) maxHeap.get()); //el elemento máximo debe estar en el tope
        assertEquals(4, maxHeap.size()); // el tamaño del heap debería ser 4
    }

    @Test
    public void deleteMinHeapTest() {
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        assertEquals(1, (int) minHeap.delete()); //elimino el elemento del tope (valor mínimo)
        assertEquals(3, (int) minHeap.get()); //el elemento que está al tope debe ser el siguiente elemento mínimo
        assertEquals(3, minHeap.size()); // el tamaño del heap debe reducirse a 3
    }

    @Test
    public void deleteMaxHeapTest() { //análogo al anterior pero con un heap máximo
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);

        assertEquals(8, (int) maxHeap.delete());
        assertEquals(5, (int) maxHeap.get());
        assertEquals(3, maxHeap.size());
    }

    @Test
    public void buildHeapMinHeapTest() { //probamos construir un heap mínimo a partir de un array de elementos
        Integer[] array = {5, 3, 8, 1};
        MyHeapImpl<Integer> heap = new MyHeapImpl<>(array, true);

        assertEquals(1, (int) heap.get());
        assertEquals(4, heap.size());
    }

    @Test
    public void buildHeapMaxHeapTest() { //probamos construir un heap máximo a partir de un array de elementos
        Integer[] array = {5, 3, 8, 1};
        MyHeapImpl<Integer> heap = new MyHeapImpl<>(array, false);

        assertEquals(8, (int) heap.get());
        assertEquals(4, heap.size());
    }

    @Test
    public void deleteAllMinHeapTest() {
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        //eliminamos todos los elementos y verificamos que se eliminen en el orden correcto
        assertEquals(1, (int) minHeap.delete());
        assertEquals(3, (int) minHeap.delete());
        assertEquals(5, (int) minHeap.delete());
        assertEquals(8, (int) minHeap.delete());
        assertEquals(0, minHeap.size()); //al final el tamaño del heap debe ser 0
    }

    @Test
    public void deleteAllMaxHeapTest() { //análogo al anterior pero con heap máximo
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);

        assertEquals(8, (int) maxHeap.delete());
        assertEquals(5, (int) maxHeap.delete());
        assertEquals(3, (int) maxHeap.delete());
        assertEquals(1, (int) maxHeap.delete());
        assertEquals(0, maxHeap.size());
    }

    @Test(expected = RuntimeException.class)
    public void deleteEmptyHeapTest() {
        minHeap.delete();
    }

    @Test(expected = RuntimeException.class)
    public void getEmptyHeapTest() {
        minHeap.get();
    }

    @Test
    public void toStringTest() {
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        //verificamos la representación en String del heap
        String expected = "1 3 8 5 ";
        assertEquals(expected, minHeap.toString());
    }
}

