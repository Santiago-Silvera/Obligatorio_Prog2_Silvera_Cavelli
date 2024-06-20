package uy.edu.um.prog2.adt.tree;

import java.util.List;

public interface BinaryTree<T extends Comparable<T>> {

    void add(T oElement);

    void remove(T oElement);

    boolean contains(T oElement);

    T find(T oElement);

    List<T> preOrder();

    List<T> posOrder();

    List<T> inOrder();

    TreeNode<T> getRoot();

}
