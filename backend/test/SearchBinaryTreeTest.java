import uy.edu.um.prog2.adt.tree.BinaryTree;
import uy.edu.um.prog2.adt.tree.SearchBinaryTreeImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SearchBinaryTreeTest {
    BinaryTree<Integer> BST;
    @Before
    public void setUp() {BST = new SearchBinaryTreeImpl<>();}

    //Test unitarios del método Add
    @Test
    public void addTest1() {
        //probamos agregar elementos a un árbol vacío
        BST.add(5);
        Integer v = 5;
        assertEquals(BST.getRoot().getValue(), v);
    }

    @Test
    public void addTest2() {
        // Comentario santi: para mi esto es mas un test de contains que de add, porque estas asumiendo que contains funciona
        //probamos agregar múltiples elementos al árbol
        BST.add(3);
        BST.add(8);
        BST.add(5);
        BST.add(16);
        assertTrue(BST.contains(3));
        assertTrue(BST.contains(8));
        assertTrue(BST.contains(5));
        assertTrue(BST.contains(16));
        assertFalse(BST.contains(2)); // El 2 nunca fue agregado por lo que tiene que devolver false
    }

    @Test
    public void addTest3() {
        // probamos que el hijo con valor menor quede en la izquierda y el hijo con valor mayor quede en la derecha
        BST.add(10); // root
        BST.add(5);  // left
        BST.add(15); // right
        Integer v = 5;
        assertEquals(BST.getRoot().getLeft().getValue(), v);
        v = 15;
        assertEquals(BST.getRoot().getRight().getValue(), v);
    }

    @Test
    public void addTest4() {
        // probamos la naturaleza recursiva del algoritmo
        BST.add(10);
        BST.add(9);
        BST.add(8);
        Integer v = 8;
        assertEquals(BST.getRoot().getLeft().getLeft().getValue(), v);
    }

    //Test unitarios del método Remove
    @Test
    public void removeTest1() {
        //eliminamos una hoja del árbol (luego no debería encontrarlo)
        BST.add(5);
        BST.add(3);
        BST.add(7);
        BST.remove(3);
        assertFalse(BST.contains(3)); //eliminé el 8 así que no debe encontrarlo
    }

    @Test
    public void removeTest2() {
        //elimino una hoja del árbol con un solo hijo
        BST.add(5);
        BST.add(3);
        BST.add(4); // 4 va a ser hijo derecho de 3 porque es menor que 5 pero mayor que 3
        BST.remove(3);
        Integer v = 4;
        assertEquals(BST.getRoot().getLeft().getValue(), v);
        assertFalse(BST.contains(3));
    }

    @Test
    public void removeTest3() {
        //remuevo una hoja del árbol con dos hijos
        BST.add(5);
        BST.add(3);
        BST.add(7);
        BST.add(6);
        BST.add(8);
        BST.remove(7); //elimino una hoja con dos hijos
        assertFalse(BST.contains(7));
        assertTrue(BST.contains(6));
        assertTrue(BST.contains(8));
    }

    @Test
    public void removeTest4() {
        // Si solo tenemos el root, al borrarlo debe quedar null la root
        BST.add(10);
        BST.remove(10);
        assertNull(BST.getRoot());
    }

    @Test
    public void removeTest5() {
        // Observamos que quitando el root, si hay varios nodos el comportamiento es adecuado
        BST.add(10);
        BST.add(8);
        BST.add(9);
        BST.add(7);
        BST.add(11);
        BST.remove(10);
        Integer v = 11;
        assertEquals(BST.getRoot().getValue(), v);
        v = 8;
        assertEquals(BST.getRoot().getLeft().getValue(), v);
        assertNull(BST.getRoot().getRight());
    }

    //Test unitarios para método Contains
    @Test
    public void containsTest1() {
        //veamos que un árbol vacío no contiene ningún elemento
        assertFalse(BST.contains(5));
        assertFalse(BST.contains(1));
        assertFalse(BST.contains(11));
    }

    @Test
    public void containsTest2() {
        //veamos que contiene un elemento previamente insertado en el árbol y que no contiene otro que no fue insertado
        BST.add(5);
        assertTrue(BST.contains(5));
        assertFalse(BST.contains(2));
    }

    //Test unitarios para método Find
    @Test
    public void findTest1() {
        //en un árbol vacío no puede encontrar nada
        assertNull(BST.find(3));
        assertNull(BST.find(1));
    }

    @Test
    public void findTest2() {
        //en un árbol con varios elementos tratamos de encontrar un elemento particular, y no debe encontrar elementos no insertados
        BST.add(3);
        BST.add(4);
        BST.add(12);
        BST.add(1);
        BST.add(8);
        BST.add(2);
        assertEquals(3,BST.find(3).intValue()); //encuentra la raíz del árbol
        assertEquals(12, BST.find(12).intValue());
        assertNull(BST.find(5));
        assertNull(BST.find(13));
    }

    @Test
    public void findTest3() {
        //tratamos de encontrar un elemento en un árbol con múltiples niveles
        BST.add(5);
        BST.add(3);
        BST.add(7);
        BST.add(2);
        BST.add(4);
        BST.add(6);
        BST.add(8);
        assertEquals(4, BST.find(4).intValue());
        assertEquals(6,BST.find(6).intValue());
    }
    //find funciona correctamente

    //Test unitario para recorrido inOrder
    @Test
    public void inOrderTest() {
        BST.add(5);
        BST.add(3);
        BST.add(7);
        BST.add(1);
        BST.add(4);
        List<Integer> expected1 = Arrays.asList(1, 3, 4, 5, 7);
        assertEquals(expected1, BST.inOrder());
        BST.add(9);
        BST.add(2);
        List<Integer> expected2 = Arrays.asList(1,2, 3, 4, 5, 7,9);
        assertEquals(expected2, BST.inOrder());
    }
    // recorrido inOrder funciona correctamente

    //Test unitario para recorrido preOrder
    @Test
    public void preOrderTest() {
        BST.add(6);
        BST.add(4);
        BST.add(3);
        BST.add(5);
        BST.add(8);
        BST.add(10);
        BST.add(7);
        List<Integer> expected1 = Arrays.asList(6,4,3,5,8,7,10);
        assertEquals(expected1, BST.preOrder());
    }
    // recorrido preOrder funciona correctamente

    //Test unitario para recorrido posOrder
    @Test
    public void posOrderTest() {
        BST.add(5);
        BST.add(3);
        BST.add(7);
        BST.add(1);
        BST.add(4);
        List<Integer> expected1 = Arrays.asList(1,4,3,7,5);
        assertEquals(expected1, BST.posOrder());
    }
    // recorrido postOrder funciona correctamente

}
