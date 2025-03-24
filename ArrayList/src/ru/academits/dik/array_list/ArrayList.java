package ru.academits.dik.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private int size;
    private E[] elements;
    private int modificationCount = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[]{};
            size = 0;
        } else if (capacity > 0) {
            //noinspection unchecked
            elements = (E[]) new Object[capacity];
            size = 0;
        } else {
            throw new IllegalArgumentException("Вместимость не может быть меньше 0. Передано значение вместимости: " + capacity);
        }
    }

    public void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize() {
        if (elements.length > size) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int index = -1;
        private final int currentModificationCount = modificationCount;

        @Override
        public boolean hasNext() {
            if (currentModificationCount != modificationCount) { // В этом методе не требуется эта проверка?
                throw new ConcurrentModificationException("Во время вызова метода произошло изменение размеров списка.");
            }

            return index + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился.");
            }

            if (currentModificationCount != modificationCount) {
                throw new ConcurrentModificationException("Во время вызова метода произошло изменение размеров списка.");
            }

            index++;
            return elements[index];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            grow();
        }

        size++;
        modificationCount++;
        elements[size - 1] = element;
        return true;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, 2 * elements.length + 1);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                if (i == size - 1) {
                    elements[i] = null;
                    size--;
                    modificationCount++;
                    return true;
                }

                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null;
                size--;
                modificationCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexToAdd(index);

        if (size + c.size() > elements.length) {
            ensureCapacity(size + c.size());
        }

        if (size == index) {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(c.toArray(), 0, elements, index, c.size());
            size += c.size();
            modificationCount++;
            return true;
        }

        E[] temp = Arrays.copyOfRange(elements, index, size);

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, elements, index, c.size());
        System.arraycopy(temp, 0, elements, index + c.size(), temp.length);

        size += c.size();
        modificationCount++;
        return true;
    }

    private void checkIndexToAdd(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Передано значение индекса: " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка. Передано значение индекса: "
                    + index + ". Размер списка: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Передано значение индекса: " + index);
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше или равен размеру списка. Передано значение индекса: "
                    + index + ". Размер списка: " + size);
        }
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
        modificationCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E temp = elements[index];
        elements[index] = element;
        return temp;
    }

    @Override
    public void add(int index, E element) {
        checkIndexToAdd(index);

        if (index == size) {
            add(element);
            return;
        }

        E[] temp = Arrays.copyOfRange(elements, index, size - 1);

        if (size == elements.length) {
            grow();
        }

        elements[index] = element;
        System.arraycopy(temp, 0, elements, index + 1, temp.length);
        size++;
        modificationCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E temp = elements[index];

        if (index == size - 1) {
            elements[index] = null;
            size--;
            modificationCount++;
            return temp;
        }

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        modificationCount++;
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = size - 1; i > -1; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = size - 1; i > -1; i--) {
            if (c.contains(elements[i])) {
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (size > a.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}