package ru.academits.dik.list;

import ru.academits.dik.node.Node;

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
        checkIndex(index);

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node<E> previousNode = new Node<>(null, head);
        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(new Node<>(value, currentNode));
        size++;
    }

    public E getFirst() {
        return head.getValue();
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<E> previousNode = new Node<>(null, head);
        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(currentNode.getNext());
        size--;

        return currentNode.getValue();
    }

    public boolean remove(E value) {
        if (head.getValue().equals(value)) {
            head = head.getNext();
            size--;
            return true;
        }

        Node<E> previousNode;
        Node<E> currentNode = head;

        while (currentNode.getNext() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();

            if (currentNode.getValue().equals(value)) {
                previousNode.setNext(currentNode.getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
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

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0, вы указали индекс: " + index);
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше или равен числу элементов в списке, вы указали индекс: "
                    + index + ". Число элементов в списке: " + size);
        }
    }

    public List<E> copy() {
        List<E> copiedList = new List<>();

        Node<E> currentNode = head;
        copiedList.head = new Node<>(head.getValue(), head.getNext());

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            new Node<>(currentNode.getValue(), currentNode.getNext());
            copiedList.size++;
        }

        return copiedList;
    }

    public void reverse() {
        if (head.getNext() == null) {
            return;
        }

        if (head.getNext().getNext() == null) {
            Node<E> temp = head.getNext();
            head.setNext(null);
            temp.setNext(head);
            head = temp;
            return;
        }

        Node<E> prePreviousNode = head;
        Node<E> previousNode = head.getNext();
        Node<E> currentNode = head.getNext().getNext();
        Node<E> temp;

        prePreviousNode.setNext(null);

        while (currentNode.getNext() != null) {
            previousNode.setNext(prePreviousNode);
            temp = currentNode;
            currentNode = currentNode.getNext();
            prePreviousNode = previousNode;
            previousNode = temp;
        }

        previousNode.setNext(prePreviousNode);
        currentNode.setNext(previousNode);
        head = currentNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<E> currentNode = head;
        sb.append('[');
        sb.append(currentNode.getValue().toString());

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            sb.append(", ").append(currentNode.getValue().toString());
        }

        sb.append(']');
        return sb.toString();
    }
}