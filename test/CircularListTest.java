import uy.edu.um.prog2.adt.circularlinkedlist.MyCircularLinkedList;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircularListTest {
    MyCircularLinkedList<Integer> circularList;
    @Before
    public void setUp() {circularList = new MyLinkedListImpl<>();}

    //Test unitarios para el método Size
    @Test
    public void sizeTest1 () {
        //inicialmente, una lista circular vacía debe tener tamaño 0
        assertEquals(0, circularList.size());
    }

    @Test
    public void sizeTest2() {
        //agrego elementos a la lista circular y el tamaño debe variar
        circularList.add(1);
        circularList.add(2);
        assertEquals(2, circularList.size());
    }

    @Test
    public void sizeTest3() {
        //elimino elementos de la lista circular y evalúo el comportamiento del tamaño
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.remove(1); //acá elimino al elemento
        assertEquals(2,circularList.size());
    }
    // size funciona correctamente

    //Test unitarios para método Add
    @Test
    public void addTest1() {
        // pruebo que a una lista vacía agrega un elemento, y además agrega el elemento correcto
        circularList.add(1);
        assertEquals(1, circularList.size()); //verifico que el tamaño de la lista circular se modificó
        assertTrue(circularList.contains(1)); //verifico que se agregó el valor que quería (en este caso, 1)
    }

    @Test
    public void addTest2() {
        //en una lista no vacía, agregamos otros elementos y evaluamos el comportamiento
        circularList.add(1);
        circularList.add(4);
        assertEquals(2, circularList.size());
        assertTrue(circularList.contains(4));
        circularList.add(11);
        assertEquals(3,circularList.size());
        assertTrue(circularList.contains(11));
    }
    //add funciona correctamente

    //Test unitarios para método Remove
    @Test
    public void removeTest1(){
        //en una lista vacía, la acción de remover un elemento no debe hacer nada (en esta implementación no se debería lanzar excepción)
        circularList.remove(3);
        assertEquals(0, circularList.size());
    }

    @Test
    public void removeTest2() {
        //en una lista no vacía, elimino elementos y compruebo que se eliminen los valores correctos
        circularList.add(3);
        circularList.add(8);
        circularList.add(11);
        circularList.add(5);
        assertEquals(4,circularList.size()); //la lista inicialmente tiene 4 elementos
        circularList.remove(13); //esta acción no debería hacer nada, el elemento no se insertó
        assertEquals(4, circularList.size());
        circularList.remove(8); //el 8 si fue insertado, debe eliminarse
        assertEquals(3, circularList.size());
        assertFalse(circularList.contains(8));
    }
    //remove funciona correctamente

    //Test unitarios para método contains
    @Test
    public void containsTest1() {
        //verifico que encuentra elementos que si están en la lista, y que retorna false si el elemento buscado no se insertó
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        assertTrue(circularList.contains(1));
        assertFalse(circularList.contains(5));
        assertTrue(circularList.contains(2));
        assertTrue(circularList.contains(3));
        assertFalse(circularList.contains(8));
    }

    @Test
    public void containsTest2() {
        // pruebo eliminar elementos de una lista y verificar que ya no los encuentra
        circularList.add(3);
        circularList.add(5);
        assertTrue(circularList.contains(3)); //en principio si lo contiene y lo debe encontrar
        circularList.remove(3); //ahora elimino el 3
        assertFalse(circularList.contains(3)); //ya no lo debe encontrar
    }
    //contains funciona correctamente

    //Test unitarios de getCircularLogic
    @Test
    public void getCircularLogicTest1() {
        //en una lista vacía, la lógica circular debe retornar nulo para cualquier posición insertada
        assertNull(circularList.getCircularLogic(2));
        assertNull(circularList.getCircularLogic(0));
    }

    @Test
    public void getCircularLogicTest2() {
        //en una lista no vacía verifico que funcione correctamente la lógica circular
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        assertEquals(1, circularList.getCircularLogic(0).intValue());
        assertEquals(2, circularList.getCircularLogic(1).intValue());
        assertEquals(3, circularList.getCircularLogic(2).intValue());
        assertEquals(1, circularList.getCircularLogic(3).intValue()); // 3 % 3 = 0
        assertEquals(2, circularList.getCircularLogic(4).intValue()); // 4 % 3 = 1
        assertEquals(3, circularList.getCircularLogic(5).intValue()); // 5 % 3 = 2
    }

    @Test
    public void getCircularLogicTest3() {
        // veamos que luego de manipular la lista eliminando un elemento, el método sigue funcionando
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.remove(2);
        assertEquals(1, circularList.getCircularLogic(0).intValue());
        assertEquals(3, circularList.getCircularLogic(1).intValue());
        assertEquals(1, circularList.getCircularLogic(2).intValue());
    }

    @Test
    public void getCircularLogicTest4() {
        // veamos que maneja de forma adecuada las posiciones negativas
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        assertEquals(3, circularList.getCircularLogic(-1).intValue()); // -1 % 3 = 2
        assertEquals(2, circularList.getCircularLogic(-2).intValue()); // -2 % 3 = 1
        assertEquals(1, circularList.getCircularLogic(-3).intValue()); // -3 % 3 = 0
    }
    //getCircularLogic funciona correctamente
}
