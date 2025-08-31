package ru.academits.dik.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        comparator = null;
    }

    public int getSize() {
        return size;
    }

    public void add(E element) {
        if (root == null) {
            root = new TreeNode<>(element);
            size++;
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (compare(element, currentNode.getValue()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new TreeNode<>(element));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(element));
                    size++;
                    return;
                }
            }
        }
    }

    public boolean contains(E element) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;
        int comparisonResult = compare(element, currentNode.getValue());

        while (comparisonResult != 0) {
            if (comparisonResult < 0) {
                if (currentNode.getLeftChild() == null) {
                    return false;
                }

                currentNode = currentNode.getLeftChild();
            } else {
                if (currentNode.getRightChild() == null) {
                    return false;
                }

                currentNode = currentNode.getRightChild();
            }

            comparisonResult = compare(element, currentNode.getValue());
        }

        return true;
    }

    public void traverseBreadth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getValue());

            if (currentNode.getLeftChild() != null) {
                queue.add(currentNode.getLeftChild());
            }

            if (currentNode.getRightChild() != null) {
                queue.add(currentNode.getRightChild());
            }
        }
    }

    public void traverseDepthRecursion(Consumer<E> consumer) {
        traverseDepthRecursion(root, consumer);
    }

    private void traverseDepthRecursion(TreeNode<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getValue());

        traverseDepthRecursion(currentNode.getLeftChild(), consumer);
        traverseDepthRecursion(currentNode.getRightChild(), consumer);
    }

    public void traverseDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.pop();
            consumer.accept(currentNode.getValue());

            if (currentNode.getRightChild() != null) {
                stack.push(currentNode.getRightChild());
            }

            if (currentNode.getLeftChild() != null) {
                stack.push(currentNode.getLeftChild());
            }
        }
    }

    public boolean remove(E element) {
        // 0. Тривиальный случай
        if (size == 0) {
            return false;
        }

        // 1. удаление листа (без детей) или удаление узла с одним ребенком
        // 2. удаление узла с двумя детьми
        TreeNode<E> nodeToDeleteParent = null;
        TreeNode<E> nodeToDelete = root;

        boolean isLeftChild = false;
        int comparisonResult = compare(element, nodeToDelete.getValue());

        // Поиск узла и его родителя для удаления
        while (comparisonResult != 0) {
            if (comparisonResult < 0) {
                if (nodeToDelete.getLeftChild() == null) {
                    return false;
                }

                nodeToDeleteParent = nodeToDelete;
                nodeToDelete = nodeToDelete.getLeftChild();
                isLeftChild = true;
            } else {
                if (nodeToDelete.getRightChild() == null) {
                    return false;
                }

                nodeToDeleteParent = nodeToDelete;
                nodeToDelete = nodeToDelete.getRightChild();
                isLeftChild = false;
            }

            comparisonResult = compare(element, nodeToDelete.getValue());
        }

        // 1. Удаление листа либо узла с одним ребенком
        if (nodeToDelete.getLeftChild() == null) {
            linkReplacementNodeToNodeToDeleteParent(nodeToDeleteParent, nodeToDelete.getRightChild(), isLeftChild);
            size--;
            return true;
        }

        if (nodeToDelete.getRightChild() == null) {
            linkReplacementNodeToNodeToDeleteParent(nodeToDeleteParent, nodeToDelete.getLeftChild(), isLeftChild);
            size--;
            return true;
        }

        // 2. Удаление узла с двумя детьми.
        TreeNode<E> replacementNode = nodeToDelete.getRightChild();
        TreeNode<E> replacementParentNode = null;

        while (replacementNode.getLeftChild() != null) {
            replacementParentNode = replacementNode;
            replacementNode = replacementNode.getLeftChild();
        }

        if (replacementParentNode != null) {
            replacementParentNode.setLeftChild(replacementNode.getRightChild());
            replacementNode.setRightChild(nodeToDelete.getRightChild());
        }

        replacementNode.setLeftChild(nodeToDelete.getLeftChild());
        linkReplacementNodeToNodeToDeleteParent(nodeToDeleteParent, replacementNode, isLeftChild);
        size--;
        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        traverseBreadth(x -> sb.append(x).append(", "));

        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    private int compare(E value1, E value2) {
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        if (value1 == null && value2 == null) {
            return 0;
        }

        if (value1 == null) {
            return -1;
        }

        if (value2 == null) {
            return 1;
        }

        // noinspection unchecked
        Comparable<E> v1 = (Comparable<E>) value1;
        return v1.compareTo(value2);
    }

    private void linkReplacementNodeToNodeToDeleteParent(TreeNode<E> nodeToDeleteParent, TreeNode<E> replacementNode, boolean isLeftChild) {
        if (nodeToDeleteParent == null) {
            root = replacementNode;
        } else if (isLeftChild) {
            nodeToDeleteParent.setLeftChild(replacementNode);
        } else {
            nodeToDeleteParent.setRightChild(replacementNode);
        }
    }
}