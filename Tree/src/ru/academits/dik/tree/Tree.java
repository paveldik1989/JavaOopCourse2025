package ru.academits.dik.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
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
                    break;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(element));
                    size++;
                    break;
                }
            }
        }
    }

    public boolean contains(E element) {
        TreeNode<E> currentNode = root;

        while (compare(element, currentNode.getValue()) != 0) {
            if (compare(element, currentNode.getValue()) < 0) {
                if (currentNode.getLeftChild() == null) {
                    return false;
                } else {
                    currentNode = currentNode.getLeftChild();
                }
            } else if (compare(element, currentNode.getValue()) > 0) {
                if (currentNode.getRightChild() == null) {
                    return false;
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }
        }

        return true;
    }


    public void breadthTraverse(Consumer<E> consumer) {
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

    public void depthRecursionTraverse(Consumer<E> consumer) {
        visit(root, consumer);
    }

    private void visit(TreeNode<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getValue());

        for (TreeNode<E> child : currentNode.getChildren()) {
            visit(child, consumer);
        }
    }

    public void depthTraverse(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.removeLast();
            consumer.accept(currentNode.getValue());

            if (currentNode.getRightChild() != null) {
                stack.addLast(currentNode.getRightChild());
            }

            if (currentNode.getLeftChild() != null) {
                stack.addLast(currentNode.getLeftChild());
            }
        }
    }

    public boolean remove(E element) {
        //0. Тривиальный случай
        if (size == 0) {
            return false;
        }

        //1. удаление листа (без детей)
        //2. удаление узла с одним ребенком
        //3. удаление узла с двумя детьми

        TreeNode<E> nodeToDeleteParent = null;
        TreeNode<E> nodeToDelete = root;
        boolean isLeftChild = false;

        //Поиск узла и его родителя для удаления
        while (compare(element, nodeToDelete.getValue()) != 0) {
            if (compare(element, nodeToDelete.getValue()) < 0) {
                if (nodeToDelete.getLeftChild() == null) {
                    return false;
                } else {
                    nodeToDeleteParent = nodeToDelete;
                    nodeToDelete = nodeToDelete.getLeftChild();
                    isLeftChild = true;
                }
            } else if (compare(element, nodeToDelete.getValue()) > 0) {
                if (nodeToDelete.getRightChild() == null) {
                    return false;
                } else {
                    nodeToDeleteParent = nodeToDelete;
                    nodeToDelete = nodeToDelete.getRightChild();
                    isLeftChild = false;
                }
            }
        }

        //1. удаление листа
        if (nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() == null) {
            //если удаляем корень
            if (nodeToDeleteParent == null) {
                root = null;
                size--;
                return true;
            }

            //TODO этот блок кода повторяется его бы поместить в какую то вспомогательную функцию
            if (isLeftChild) {
                nodeToDeleteParent.setLeftChild(null);
            } else {
                nodeToDeleteParent.setRightChild(null);
            }

            size--;
            return true;
        }

        //2. удаление узла с одним ребенком
        //2.1. Если у удаляемого узла нет левого ребенка
        if (nodeToDelete.getLeftChild() == null) {
            //если удаляем корень
            if (nodeToDeleteParent == null) {
                root = root.getRightChild();
                size--;
                return true;
            }

            if (isLeftChild) {
                nodeToDeleteParent.setLeftChild(nodeToDelete.getRightChild());
            } else {
                nodeToDeleteParent.setRightChild(nodeToDelete.getRightChild());
            }

            size--;
            return true;
        }

        //2.2. Если у удаляемого узла нет левого ребенка
        if (nodeToDelete.getRightChild() == null) {
            if (nodeToDeleteParent == null) {
                root = root.getLeftChild();
                size--;
                return true;
            }

            if (isLeftChild) {
                nodeToDeleteParent.setLeftChild(nodeToDelete.getLeftChild());
            } else {
                nodeToDeleteParent.setRightChild(nodeToDelete.getLeftChild());
            }

            size--;
            return true;
        }

        //3. Удаление узла с двумя детьми.
        //3.1. Найти замену удаляемому узлу:
        //3.1.1. Взять правого ребенка
        TreeNode<E> replacementNode = nodeToDelete.getRightChild();
        TreeNode<E> replacementParentNode = nodeToDelete;

        //3.1.2 Найти минимальный узел у него. Влево до конца
        while (replacementNode.getLeftChild() != null) {
            replacementParentNode = replacementNode;
            replacementNode = replacementNode.getLeftChild();
        }

        if (replacementParentNode != nodeToDelete) {
            replacementParentNode.setLeftChild(replacementNode.getRightChild());
            //  ИЗМЕНЕНО: Явно устанавливаем правое поддерево
            replacementNode.setRightChild(nodeToDelete.getRightChild());
        } else {
            // НОВЫЙ БЛОК: Обработка случая прямого правого ребенка
            replacementParentNode.setRightChild(replacementNode.getRightChild());
        }

        //3.2. У родителя удаляемого узла подменяем ссылку с удаляемого узла на этот самый левый элемент
        //Если корень
        if (nodeToDeleteParent == null) {
            root = replacementNode;
            replacementNode.setLeftChild(nodeToDelete.getLeftChild());
            size--;
            return true;
        }

        if (isLeftChild) {
            nodeToDeleteParent.setLeftChild(replacementNode);
        } else {
            nodeToDeleteParent.setRightChild(replacementNode);
        }

        //3.3. Добавляем детей удаляемого узла заменяемому
        replacementNode.setLeftChild(nodeToDelete.getLeftChild());

        size--;
        return true;
    }

//    private void madeLinkToParent(TreeNode<E> replacementNode, TreeNode<E> parent, boolean isLeftChild) {
//        if (isLeftChild) {
//            parent.setLeftChild(replacementNode);
//        } else {
//            parent.setRightChild(replacementNode);
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        breadthTraverse(x -> sb.append(x.toString()).append(", "));

        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    private int compare(E value1, E value2) {
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        //noinspection unchecked
        Comparable<E> v1 = (Comparable<E>) value1;
//        Comparable<E> v2 = (Comparable) value2; не понятно почему если так, то в return v1.compareTo(v2);
//        ошибка Required type: E - почему требуется Е а не Comparable<E>
//        Provided: Comparable<E>
//        return v1.compareTo(v1); - даже это ошибку выдает

        if (v1 == null && value2 == null) {
            return 0;
        }

        if (v1 == null) {
            return -1;
        }

        if (value2 == null) {
            return 1;
        }

        return v1.compareTo(value2);
    }
}