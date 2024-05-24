package uy.edu.um.prog2.adt.tree;

import java.util.ArrayList;
import java.util.List;

public class SearchBinaryTreeImpl<T extends Comparable<T>> implements BinaryTree<T> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
    }
    public void add(T oElement) {
        TreeNode<T> oElementToAdd = new TreeNode<>(oElement);
        if (root == null) {
            root = oElementToAdd;
        } else {
            root.add(oElement);
        }
    }

    public boolean contains(T oElement) {
        return contains(oElement, root);
    }

    private boolean contains(T oElementToSearch, TreeNode<T> oTree) {
        boolean bContains = false;
        if (oTree != null) {
            int nValue = oElementToSearch.compareTo(oTree.getValue());
            if (nValue == 0) {
                bContains = true;
            } else if (nValue > 0) {
                bContains = contains(oElementToSearch, oTree.getRight());
            } else {
                bContains = contains(oElementToSearch, oTree.getLeft());
            }
        }
        return bContains;
    }

    public void remove(T oElement) {
        if (root != null) {
            if (oElement == root.getValue()) {
                // Borrar el root
                if (!(root.getRight() == null)) {
                    TreeNode<T> temp = root.getLeft();
                    TreeNode<T> node = root.getRight();
                    root = node;
                    while (node.getLeft() != null) {
                        node = node.getLeft();
                    }
                    node.add(temp);
                } else {
                    root = root.getLeft();
                }
            } else {
                root.remove(oElement);
            }
        }
    }

    public T find(T oElement) {
        return find(oElement, root);
    }

    private T find(T oElementToSearch, TreeNode<T> oTree) {
        T oSearchedElement = null;
        if (oTree != null) {
            int nValue = oElementToSearch.compareTo(oTree.getValue());
            if (nValue == 0) {
                oSearchedElement = oTree.getValue();
            } else if (nValue > 0) {
                oSearchedElement = find(oElementToSearch, oTree.getRight());
            } else {
                oSearchedElement = find(oElementToSearch, oTree.getLeft());
            }
        }
        return oSearchedElement;
    }

    public List<T> preOrder() {
        List<T> colValues = new ArrayList<>();
        if (root != null) {
            colValues.addAll(root.preOrderTraverse());
        }
        return colValues;
    }

    public List<T> posOrder() {
        List<T> colValues = new ArrayList<>();
        if (root != null) {
            colValues.addAll(root.postOrderTraverse());
        }
        return colValues;
    }

    public List<T> inOrder() {
        List<T> colValues = new ArrayList<>();
        if (root != null) {
            colValues.addAll(root.inOrderTraverse());
        }
        return colValues;
    }
}
