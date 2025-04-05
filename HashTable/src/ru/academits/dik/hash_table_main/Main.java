package ru.academits.dik.hash_table_main;

import ru.academits.dik.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>();

        HashTable<Integer> hashTable2 = new HashTable<>();
        hashTable2.add(1);

        HashTable<Integer> hashTable3 = new HashTable<>();

        hashTable3.add(1);
        hashTable3.add(11);
        hashTable3.add(111);
        hashTable3.add(21432353);
        hashTable3.add(45677);

        HashTable<Integer> hashTable4 = new HashTable<>();
        hashTable4.add(1);
        hashTable4.add(11);
        hashTable4.add(111);
        hashTable4.add(21432353);
        hashTable4.add(45677);
        hashTable4.remove(21432353);

        ArrayList<HashTable<Integer>> hashTables = new ArrayList<>();
        hashTables.add(hashTable1);
        hashTables.add(hashTable2);
        hashTables.add(hashTable3);
        hashTables.add(hashTable4);

        testConstructors();
        testSize(hashTables);
        testIsEmpty(hashTables);
        testContains(hashTables);
        testIterator(hashTables);
        testToArray(hashTables);
        testRemove(hashTables);
        testContainsAll(hashTables);
        testAddAll(hashTables);
        testRemoveAll(hashTables);
        testRetainAll(hashTables);
        testClear(hashTables);
    }

    private static void testConstructors() {
        HashTable<Integer> hashTable = new HashTable<>(20);

        hashTable.add(2);
        hashTable.add(22);
        hashTable.add(222);
        hashTable.add(1);

        System.out.println(hashTable);
    }

    private static void testSize(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест size:");

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.size());
        }

        System.out.println();
    }

    private static void testIsEmpty(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест isEmpty:");

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.isEmpty());
        }

        System.out.println();
    }

    private static void testContains(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест contains:");

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.contains(11));
        }

        System.out.println();
    }

    private static void testIterator(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест iterator:");

        for (HashTable<Integer> hashTable : hashTables) {
            for (Integer integer : hashTable) {
                System.out.println(integer);
            }
        }

        System.out.println();
    }

    private static void testToArray(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест toArray:");

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println("Исходная хэш-таблица:" + hashTable);
            Object[] array1 = hashTable.toArray();
            System.out.println(Arrays.toString(array1));

            Integer[] array2 = {1, 2, 3, 4, 5, 6, 7};
            array2 = hashTable.toArray(array2); // Правилен ли такой результат: [null, 2, 3, 4, 5, 6, 7] для пустой хэш-таблицы?
            System.out.println(Arrays.toString(array2));
            System.out.println();
        }

        System.out.println();
    }

    private static void testRemove(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест remove:");

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.remove(21432353));
            System.out.println(hashTable);
        }

        System.out.println();
    }

    private static void testContainsAll(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест containsAll:");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(11);
        arrayList.add(21432353);

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.containsAll(arrayList));
        }

        System.out.println();
    }

    private static void testAddAll(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест addAll:");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(11);
        arrayList.add(21432353);

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.addAll(arrayList));
            System.out.println(hashTable);
        }

        System.out.println();
    }

    private static void testRemoveAll(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест removeAll:");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(11);
        arrayList.add(21432353);

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.removeAll(arrayList));
            System.out.println(hashTable);
        }

        System.out.println();
    }

    private static void testRetainAll(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест retainAll:");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(11);
        arrayList.add(21432353);

        for (HashTable<Integer> hashTable : hashTables) {
            System.out.println(hashTable.retainAll(arrayList));
            System.out.println(hashTable);
        }

        System.out.println();
    }

    private static void testClear(ArrayList<HashTable<Integer>> hashTables) {
        System.out.println("Тест clear:");

        for (HashTable<Integer> hashTable : hashTables) {
            hashTable.clear();
            System.out.println(hashTable);
        }

        System.out.println();
    }
}