import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.NodeWithKeyValue;
import org.junit.Before;
import org.junit.Test;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Set;

import static org.junit.Assert.*;

public class HashTest {
    MyHashTable<Integer, String> hash;
    @Before
    public void setUp() {
        hash = new MyHashTable<>(10, 0.75f);
    }
    @Test
    public void putTest1() {
        // El hash inicialmente debe estar vacío
        assertEquals(0, hash.size());

        // Agregamos elementos al hash
        Integer k = 1;
        hash.put(k, "Valor 1");
        // Verificamos que se coloque en el lugar correcto
        // La posicion indicada por el hash debe contener la key
        assertEquals(k, hash.getTable()[k.hashCode() % hash.getCAPACITY()].getKey());
        assertEquals(1, hash.size());
    }

    @Test
    public void putTest2() {
        // Les prometo que el 1 y el 11 tienen el mismo hash code.
        hash.put(1, "v1");
        Integer k = 11;
        hash.put(k, "v2");
//        hash.showHash();
//        System.out.println(k.hashCode() % hash.getCAPACITY());
        // el programa tiene que asegurar que cuando colisiona va al siguiente valor
        assertEquals(k, hash.getTable()[(k.hashCode() % hash.getCAPACITY()) +1].getKey());
    }

    @Test
    public void putTest3() {
        Integer k = 35;
        hash.put(k, "Valor 1");
        // Si la key ya existe y no ha sido borrada al momento de intentar de agregarla de nuevo, arrojamos una excepcion
        assertThrows(KeyAlreadyExistsException.class, () -> hash.put(k, "Valor 2"));
    }

    @Test
    public void putTest4() {
        hash.put(1, "v1");
        hash.put(2, "v2");
        Integer k = 11;
        hash.put(k, "v3");
        // El algoritmo de colicion va a seguir buscando hasta que encuentre un null, si hay otras key entre medio no importa
        assertEquals(k, hash.getTable()[k.hashCode() % hash.getCAPACITY() +2].getKey());
    }

    @Test()
    public void resizeTest() {
        // Agregamos 8 elementos a la tabla
        for (int i = 0; i < 8; i++) {
            hash.put(i, "Valor " + i);
        }
        assertEquals(8, hash.size());
        // Igualmente chequeamos que hallan 9 elementos no nulos
        int count = 0;
        for (NodeWithKeyValue<Integer, String> node : hash.getTable()) {
            if (node != null) {
                count++;
            }
        }
        assertEquals(8, count);
        // La tabla debe tener un tamaño de 10 * 2 = 20
        assertEquals(20, hash.getCAPACITY());
    }

    @Test
    public void containsKeyTest1() {
        // Agregamos elementos al hash
        Integer k = 1;
        hash.put(k, "Valor 1");
        assertTrue(hash.containsKey(k));
    }

    @Test
    public void containsKeyTest2() {
        assertFalse(hash.containsKey(1));
    }

    @Test
    public void removeTest1() {
        // Agregamos elementos al hash
        Integer k = 1;
        hash.put(k, "Valor 1");
        assertEquals("Valor 1", hash.remove(k));
        assertFalse(hash.containsKey(k));
        assertNull(hash.remove(2));
    }

    @Test
    public void removeTest2() {
        // Remove debe devolver null si la key no existe
        assertNull(hash.remove(1));
    }

    // Tests para el método keySet
    @Test
    public void keySetTest1() {
        // Verificamos que inicialmente el conjunto de keys está vacío
        Set<Integer> keys = hash.keySet();
        assertTrue(keys.isEmpty());
    }

    @Test
    public void keySetTest2() {
        // Agregamos elementos al hash
        hash.put(1, "Valor 1");
        hash.put(2, "Valor 2");
        hash.put(3, "Valor 3");

        // Verificamos que el conjunto de keys contiene todas las claves agregadas
        Set<Integer> keys = hash.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
        assertTrue(keys.contains(3));
    }

    @Test
    public void keySetTest3() {
        // Agregamos elementos y luego removemos algunos
        hash.put(1, "Valor 1");
        hash.put(2, "Valor 2");
        hash.put(3, "Valor 3");
        hash.remove(2);

        // Verificamos que el conjunto de keys contiene solo las claves no eliminadas
        Set<Integer> keys = hash.keySet();
        assertEquals(2, keys.size());
        assertTrue(keys.contains(1));
        assertFalse(keys.contains(2));
        assertTrue(keys.contains(3));
    }

}
