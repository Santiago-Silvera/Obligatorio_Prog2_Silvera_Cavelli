import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {
    MyList<Integer> list;
    @Before
    public void setUp() {list = new MyLinkedListImpl<>();}

    //Test unitarios del método Size
    @Test
    public void sizeTest1() {
        // al principio, la lista enlazada debe tener tamaño nulo
        assertEquals(0, list.size());
    }

    @Test
    public void sizeTest2() {
        //al agregar un elemento, el tamaño de la lista debe aumentar en 1
        list.add(5);
        assertEquals(1, list.size());
    }

    @Test
    public void sizeTest3() {
        //al eliminar el elemento de la lista, el tamaño debe volver a ser 0
        list.remove(5);
        assertEquals(0, list.size());
    }

    //Test unitarios del método Add
    @Test
    public void addTest1() {
        // en principio la lista empieza con 0 elementos
        assertEquals(0, list.size());

        //veamos que al agregar un elemento a la lista vacía, su tamaño crece y además se agrega el elemento que queremos
        list.add(1); //se agrega el 1
        assertEquals(1, list.size());
        assertEquals(1,list.get(0).intValue());
    }

    @Test
    public void addTest2() {
        //ahora agregamos elementos a una lista no vacía y evaluamos el comportamiento
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        assertEquals(1,list.get(0).intValue());
        assertEquals(2,list.get(1).intValue());
    }

    //Test unitarios para método Remove
    @Test
    public void removeTest1() {
        // probamos eliminar elementos de una lista vacía (en este caso, con esta implementación, no debe hacer nada)
        list.remove(0);
        assertEquals(0, list.size()); //en efecto, no debe hacer nada
    }
    @Test
    public void removeTest2() {
        // de una lista no vacía con un solo elemento, elimino dicho elemento
        list.add(1);
        list.remove(1);
        assertEquals(0, list.size()); //la lista que antes tenía un solo elemento, tras una operación de remove ya no debe tener elementos
    }

    @Test
    public void removeTest3() {
        //de una lista no vacía con más de un elemento, elimino un elemento en una posición intermedia
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(3);
        assertEquals(3, list.size());
        assertFalse(list.contains(3)); //verifico que se haya eliminado el elemento que quería eliminar
    }

    //Test unitarios del método Contains
    @Test
    public void containsTest1() {
        //en una lista vacía inicialmente vacía, agrego un elemento y verifico que lo encuentra
        list.add(1);
        assertTrue(list.contains(1));
    }

    @Test
    public void containsTest2() {
        //en una lista con varios elementos, verifico que encuentra un elemento en particular
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(2));
    }

    @Test
    public void containsTest3() {
        //en una lista con varios elementos, verifico que los elementos no registrados en la lista no pueden ser encontrados con contains
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(1)); //este si esta, así que lo debe encontrar
        assertFalse(list.contains(4)); //este no está, asi que no lo debe encontrar
    }

    //Test unitarios para el método Get
    @Test
    public void getTest1() {
        //pruebo obtener un elemento en una posición que aun no se registró (no debe retornar nada)
        assertNull(list.get(2)); //en una lista vacía, puedo tomar cualquier posición y no voy a tener elementos
    }

    @Test
    public void getTest2() {
        //en una lista no vacía con varios elementos, pruebo obtener el primer y el último elemento
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(9);
        assertEquals(1, list.get(0).intValue());
        assertEquals(9, list.get(3).intValue());
    }

    @Test
    public void getTest3() {
        //en una lista con varios elementos, pruebo obtener un elemento en una posición intermedia
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(3, list.get(2).intValue());
    }
}
