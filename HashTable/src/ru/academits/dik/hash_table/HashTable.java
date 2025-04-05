package ru.academits.dik.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int BUCKETS_DEFAULT_AMOUNT = 10;
    private int size;
    private int modCount;

    ArrayList<E>[] buckets;

    public HashTable() {
        this(BUCKETS_DEFAULT_AMOUNT);
    }

    public HashTable(int bucketsAmount) {
        if (bucketsAmount < 1) {
            throw new IllegalArgumentException("Размер массива хэш-таблице должен быть > 0");
        }

        //noinspection unchecked
        buckets = (ArrayList<E>[]) new ArrayList[bucketsAmount];
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
        if (buckets[getBucketIndex(o.hashCode())] == null) {
            return false;
        }

        return buckets[getBucketIndex(o.hashCode())].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int bucketIndex = 0;
        private int elementInBucketIndex = -1;
        int elementIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return elementIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Во время обхода итератором хэш-таблица изменилась.");
            }

            elementIndex++;
            elementInBucketIndex++;

            if (buckets[bucketIndex] == null || elementInBucketIndex == buckets[bucketIndex].size()) {
                elementInBucketIndex = 0;

                do {
                    bucketIndex++;
                } while (buckets[bucketIndex] == null || buckets[bucketIndex].isEmpty());
            }

            return buckets[bucketIndex].get(elementInBucketIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultedArray = new Object[size];
        int i = 0;

        for (E element : this) {
            resultedArray[i] = element;
            i++;
        }

        return resultedArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length <= size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass()); // TODO разобраться с a.getClass()
        }

        int i = 0;

        for (E element : this) {
            //noinspection unchecked
            a[i] = (T) element;
            i++;
        }

        a[i] = null;

        return a;
    }

    @Override
    public boolean add(E e) {
        int bucketIndex = getBucketIndex(e.hashCode());

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>();
        }

        buckets[bucketIndex].add(e);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (buckets[getBucketIndex(o.hashCode())] == null) {
            return false;
        }

        size--;
        modCount++;
        return buckets[getBucketIndex(o.hashCode())].remove(o);
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
    public boolean addAll(Collection<? extends E> c) {
        boolean isChanged = false;

        for (E element : c) {
            if (add(element)) {
                isChanged = true;
            }
        }

        size += c.size();
        modCount++;
        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object element : c) {
            if (remove(element)) {
                isChanged = true;
                size--;
                modCount++;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                int sizeBeforeRemove = bucket.size();
                isChanged = bucket.retainAll(c);
                size -= sizeBeforeRemove - bucket.size();
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(buckets);
    }

    private int getBucketIndex(int hashCode) {
        return Math.abs(hashCode % buckets.length);
    }
}