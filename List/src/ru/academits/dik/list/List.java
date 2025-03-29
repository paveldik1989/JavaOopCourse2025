package ru.academits.dik.list;

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

        Node<E> currentNode = head;

        while (currentNode.getNext() != null) {
            Node<E> previousNode = currentNode;
            currentNode = currentNode.getNext();

            if (Objects.equals(currentNode.getValue(), value)) {
                previousNode.setNext(currentNode.getNext());
                size--;
                return true;
            }
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

        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getValue();
    }

    public E setValue(int index, E value) {
        checkIndex(index);

        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        E oldValue = currentNode.getValue();
        currentNode.setValue(value);
        return oldValue;
    }

    public List<E> copy() {
        if (head == null) {
            return new List<>();
        }

        List<E> copiedList = new List<>();
        copiedList.head = new Node<>(head.getValue(), null);
        copiedList.size = size;

        Node<E> currentNode = head;
        Node<E> currentCopiedListNode = copiedList.head;

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            currentCopiedListNode.setNext(new Node<>(currentNode.getValue(), null));
            currentCopiedListNode = currentCopiedListNode.getNext();
        }

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
        sb.append('[').append(currentNode.getValue());

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            sb.append(", ").append(currentNode.getValue());
        }

        sb.append(']');
        return sb.toString();
    }

    private void checkIsEmpty() {
        if (head == null) {
            throw new NullPointerException("Список пуст.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0, вы указали индекс: " + index);
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше или равен числу элементов в списке, вы указали индекс: "
                    + index + ". Число элементов в списке: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0, вы указали индекс: " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше числа элементов в списке, вы указали индекс: "
                    + index + ". Число элементов в списке: " + size);
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