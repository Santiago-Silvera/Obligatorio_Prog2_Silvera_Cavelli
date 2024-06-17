package uy.edu.um.prog2.adt.hashmap;

import uy.edu.um.prog2.adt.linkedlist.NodeWithKeyValue;
import lombok.Getter;
import lombok.Setter;

import javax.management.openmbean.KeyAlreadyExistsException;

public class MyHashTable<K, V> implements MyHash<K,V> {

    @Setter
    @Getter
    private int CAPACITY;
    @Getter
    private final float LOAD_FACTOR;
    private int size = 0;

    private NodeWithKeyValue<K, V>[] table;

    public MyHashTable(int capacity, float loadFactor) {
        this.CAPACITY = capacity;
        this.LOAD_FACTOR = loadFactor;
        this.table = new NodeWithKeyValue[CAPACITY];
    }

    @Override
    public void put(K key, V value) throws KeyAlreadyExistsException {
        if (size() + 1 >= CAPACITY * LOAD_FACTOR) {
            resize();
        }
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].getKey() == key) {
                if (table[index].isDeleted()) {
                    table[index].setValue(value);
                    table[index].setDeleted(false);
                    size++;
                    return;
                } else {
                    // Si la key ya existe y no ha sido borrada al momento de intentar de agregarla de nuevo, arrojamos una excepcion
                    throw new KeyAlreadyExistsException();
                }
            }
            index = (index + 1) % CAPACITY;
        }
        // Si sale del while sin return es porque llego a una posicion con null
        if (table[index] == null) {
            table[index] = new NodeWithKeyValue<>(key, value);
            size++;
    }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        boolean found = false;
        while (table[index] != null) {
            if (table[index].getKey() == key && !table[index].isDeleted()) {
                found = true;
                break;
            }
            index = (index + 1) % CAPACITY;
        }
        return found;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        // Si en la tabla tenemos null no hace nada de esto y tira derecho para el null
        if (table[index] != null) {
            // Vamos buscando hasta encontrar o un null, en cuyo caso salimos y devolvemos null
            // o encontramos la key
            while (table[index] != null) {
                if (table[index].getKey() == key && !table[index].isDeleted()) {
                    V value = table[index].getValue();
                    table[index].setDeleted(true);
                    size--;
                    return value;
                }
                index = (index + 1) % CAPACITY;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        // Si en la tabla tenemos null no hace nada de esto y tira derecho para el null
        if (table[index] != null) {
            // Vamos buscando hasta encontrar o un null, en cuyo caso salimos y devolvemos null
            // o encontramos la key
            while (table[index] != null) {
                if (table[index].getKey() == key && !table[index].isDeleted()) {
                    return table[index].getValue();
                }
                index = (index + 1) % CAPACITY;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public NodeWithKeyValue<K, V>[] getTable() {
        return table;
    }

    private void resize() {
        CAPACITY *= 2;
        NodeWithKeyValue<K, V>[] newTable = new NodeWithKeyValue[CAPACITY];
        for (NodeWithKeyValue<K, V> node : table) {
            if (node != null) {
                int index = hash(node.getKey());
                if (newTable[index] == null) {
                    newTable[index] = node;
                } else {
                    while (newTable[index] != null) {
                        index = (index + 1) % CAPACITY;
                        if (newTable[index] == null) {
                            newTable[index] = node;
                            break;
                        }
                    }
                }
            }
        }
        table = newTable;
    }

    private int hash(K key) {
        return key.hashCode() % CAPACITY;
    }

    public void showHash() {
        for (NodeWithKeyValue<K, V> node : table) {
            System.out.println("" + node);
        }
    }

}
