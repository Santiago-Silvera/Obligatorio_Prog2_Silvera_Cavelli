/**
 *
 */
package uy.edu.um.prog2.adt.tree;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends Comparable<T>> {

    private T value;

    private TreeNode<T> left;

    private TreeNode<T> right;

    public TreeNode(T oValue) {
        this.value = oValue;
    }

    public void add(T oElement) {
        int nValue = oElement.compareTo(value);
        if (nValue == 0) throw new KeyAlreadyExistsException();
        TreeNode<T> oElementToAdd = new TreeNode<>(oElement);
        if (nValue > 0) {
            if (right == null) {
                right = oElementToAdd;
            } else {
                right.add(oElement);
            }
        } else {
            if (left == null) {
                left = oElementToAdd;
            } else {
                left.add(oElement);
            }
        }
    }

    public void add(TreeNode<T> treeRoot) {
        int comp = treeRoot.getValue().compareTo(value);
        if (comp == 0) throw new KeyAlreadyExistsException();
        if (comp > 0) {
            right = treeRoot;
        } else {
            left = treeRoot;
        }

    }

    public TreeNode<T> remove(T oElement) {
        int nValue = (oElement).compareTo(value);
        if (nValue > 0) {
            if (right != null) {
                right = right.remove(oElement);
            }
        } else if (nValue < 0) {
            if (left != null) {
                left = left.remove(oElement);
            }
        } else if (left != null && right != null) {
            // Encontre el elemento a eliminar
            value = right.findMin();
            right = right.remove(value);
        } else {
            if (left != null) {
                return left;
            } else {
                return right;
            }
        }
        return this;
    }

    public List<T> inOrderTraverse() {
        List<T> colList = new ArrayList<>();
        if (left != null) {
            colList.addAll(left.inOrderTraverse());
        }
        colList.add(value);
        if (right != null) {
            colList.addAll(right.inOrderTraverse());
        }
        return colList;
    }

    public List<T> preOrderTraverse() {
        List<T> colList = new ArrayList<>();
        colList.add(getValue());
        if (left != null) {
            colList.addAll(left.preOrderTraverse());
        }
        if (right != null) {
            colList.addAll(right.preOrderTraverse());
        }
        return colList;
    }

    public List<T> postOrderTraverse() {
        List<T> colList = new ArrayList<>();
        if (left != null) {
            colList.addAll(left.postOrderTraverse());
        }
        if (right != null) {
            colList.addAll(right.postOrderTraverse()); //se corrigió esta funcionalidad
        }
        colList.add(getValue());
        return colList;
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T findMin() {
        T oReturn = value;
        if (left != null) {
            oReturn = left.findMin();
        }

        return oReturn;
    }

}
