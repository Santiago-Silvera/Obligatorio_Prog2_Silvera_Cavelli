package uy.edu.um.prog2.adt.hashmap;

import uy.edu.um.prog2.adt.linkedlist.NodeWithKeyValue;

public interface MyHash<K, V> {

    /**
     * Inserts a key-value pair into the hash map.
     * If the key is already assigned, a linear walk is done to resolve the conflict.
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Checks if the hash map contains the given key.
     *
     * @param key the key
     * @return true if the key exists, false otherwise
     */
    boolean containsKey(K key);

    /**
     * Removes the key-value pair associated with the given key.
     *
     * @param key the key
     * @return the value that was associated with the key, or null if the key did not exist
     */
    V remove(K key);

    /**
     * Gets the key-value pair associated with the given key.
     *
     * @param key the key
     * @return the value that was associated with the key, or null if the key did not exist
     */
    V get(K key);

    /**
     * Returns the number of key-value pairs in the hash map.
     *
     * @return the size of the hash map
     */
    int size();

    NodeWithKeyValue<K, V>[] getTable();
}