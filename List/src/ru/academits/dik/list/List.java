package ru.academits.dik.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<E> {
    private Node<E> head;
    private int size;

    public List() {
    }

    public int getSize() {
        return size;
    }

    public void addFirst(E value) {
        head = new Node<>(value, head);
        size++;
    }

    public void add(int index, E value) {
        checkIndexForAdd(index);

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node<E> previousNode = getNodeByIndex(index - 1);
        previousNode.setNext(new Node<>(value, previousNode.getNext()));
        size++;
    }

    public E getFirst() {
        checkIsEmpty();

        return head.getValue();
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<E> previousNode = getNodeByIndex(index - 1);
        Node<E> currentNode = previousNode.getNext();

        previousNode.setNext(currentNode.getNext());
        size--;

        return currentNode.getValue();
    }

    public boolean remove(E value) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.getValue(), value)) {
            head = head.getNext();
            size--;
            return true;
        }

        Node<E> previousNode = head;
        Node<E> currentNode = head.getNext();

        while (currentNode != null) {
            if (Objects.equals(currentNode.getValue(), value)) {
                previousNode.setNext(currentNode.getNext());
                size--;
                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return false;
    }

    public E removeFirst() {
        checkIsEmpty();

        E value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public E getValue(int index) {
        checkIndex(index);
        Node<E> currentNode = getNodeByIndex(index);

        return currentNode.getValue();
    }

    public E setValue(int index, E value) {
        checkIndex(index);
        Node<E> currentNode = getNodeByIndex(index);

        E oldValue = currentNode.getValue();
        currentNode.setValue(value);
        return oldValue;
    }

    public List<E> copy() {
        if (head == null) {
            return new List<>();
        }

        List<E> copiedList = new List<>();
        copiedList.head = new Node<>();
        copiedList.size = size;

        Node<E> currentNode = head;
        Node<E> currentCopiedListNode = copiedList.head;

        while (currentNode != null) {
            currentCopiedListNode.setNext(new Node<>(currentNode.getValue()));
            currentCopiedListNode = currentCopiedListNode.getNext();
            currentNode = currentNode.getNext();
        }

        copiedList.head = copiedList.head.getNext();
        return copiedList;
    }

    public void reverse() {
        Node<E> previousNode = null;
        Node<E> currentNode = head;

        while (currentNode != null) {
            Node<E> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        Node<E> currentNode = head;
        sb.append('[');

        while (currentNode != null) {
            sb.append(currentNode.getValue()).append(", ");
            currentNode = currentNode.getNext();
        }

        sb.deleteCharAt(sb.length() - 2);
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }

    private void checkIsEmpty() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и не может быть больше или равен числу элементов в списке." +
                    " Вы указали индекс: " + index + ". Число элементов в списке: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и не может быть больше числа элементов в списке" +
                    ". Вы указали индекс: " + index + ". Число элементов в списке: " + size);
        }
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
}