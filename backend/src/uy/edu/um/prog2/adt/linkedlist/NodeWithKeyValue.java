package uy.edu.um.prog2.adt.linkedlist;

import lombok.Getter;
import lombok.Setter;

// Clase NodeWithKeyValue que hereda de Node y tiene dos tipos genéricos K y V
@Getter
public class NodeWithKeyValue<K, V> extends Node<V> {
    private final K key;
    @Setter
    private boolean deleted = false;

    public NodeWithKeyValue(K key, V value) {
        super(value);
        this.key = key;
    }

    @Override
    public String toString() {
        return "NodeWithKeyValue{" +
                "key=" + key +
                ", deleted=" + deleted +
                '}';
    }
}
