package ru.academits.dik.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int BUCKETS_DEFAULT_COUNT = 10;

    private int size;
    private int modCount;
    private final ArrayList<E>[] buckets;

    public HashTable() {
        this(BUCKETS_DEFAULT_COUNT);
    }

    public HashTable(int bucketsCount) {
        if (bucketsCount < 1) {
            throw new IllegalArgumentException("Размер массива хэш-таблице должен быть > 0, передан размер массива: " + bucketsCount);
        }

        //noinspection unchecked
        buckets = (ArrayList<E>[]) new ArrayList[bucketsCount];
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
        int bucketIndex = getBucketIndex(o);
        return buckets[bucketIndex] != null && buckets[bucketIndex].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int bucketIndex;
        private int elementInBucketIndex = -1;
        private int elementIndex = -1;
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
        Object[] resultArray = new Object[size];
        int i = 0;

        for (E element : this) {
            resultArray[i] = element;
            i++;
        }

        return resultArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) { // Не понятно почему случай когда размер одинаковый не тот?
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        int i = 0;

        for (E element : this) {
            //noinspection unchecked
            a[i] = (T) element;
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int bucketIndex = getBucketIndex(e);

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
        int bucketIndex = getBucketIndex(o);

        if (buckets[bucketIndex] == null) {
            return false;
        }

        boolean isRemoved = buckets[bucketIndex].remove(o);

        if (isRemoved) {
            size--;
            modCount++;
        }

        return isRemoved;
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
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                int sizeBeforeRemove = bucket.size();

                if (bucket.removeAll(c)) {
                    isChanged = true;
                    size -= sizeBeforeRemove - bucket.size();
                }
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                int sizeBeforeRemove = bucket.size();

                if (bucket.retainAll(c)) {
                    isChanged = true;
                    size -= sizeBeforeRemove - bucket.size();
                }
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(buckets);
    }

    private int getBucketIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % buckets.length);
    }
}