package ru.academits.dik.array_list_main;

import ru.academits.dik.array_list.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        testConstructors();
        testAddRemove();
        testIsEmpty();
        testContains();
        testIterator();
        testToArray();
        testAddAll();
        testIndexOf();
        testRetainAll();
        testRemoveAll();
        testContainsAll();
    }

    private static void testConstructors() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(100);
        System.out.println(list1);
        System.out.println(list2);

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.trimToSize();
        System.out.println(list1);

        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println(list2);
    }

    private static void testAddRemove() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(1000);
        System.out.println(list1);
        System.out.println(list2);

        for (int i = 1; i < 1000; i++) {
            list1.add(i);
        }

        for (int i = 0; i < 1000; i++) {
            list2.add(i, i);
        }

        System.out.println(list1);
        System.out.println(list2);
    }

    private static void testIsEmpty() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(1000);
        System.out.println(list1.isEmpty());
        System.out.println(list2.isEmpty());

        list1.add(100);
        System.out.println(list1.isEmpty());

        list1.remove(0);
        System.out.println(list1.isEmpty());

        list1.add(100);
        Integer x = 100;
        list1.remove(x);
        System.out.println(list1.isEmpty());
    }

    private static void testContains() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.contains(1));
        System.out.println(list.contains(10));
    }

    private static void testIterator() {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(list.iterator().hasNext());

        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            System.out.println(i);
        }

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void testToArray() {
        ArrayList<Integer> list1 = new ArrayList<>();

        list1.add(101);
        list1.add(201);
        list1.add(301);

        Object[] arr1 = list1.toArray();
        System.out.println(Arrays.toString(arr1));

        Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        arr2 = list1.toArray(arr2);
        System.out.println(Arrays.toString(arr2));

        Integer[] arr3 = {1, 2, 3};
        arr3 = list1.toArray(arr3);
        System.out.println(Arrays.toString(arr3));

        Integer[] arr4 = {};
        arr4 = list1.toArray(arr4);
        System.out.println(Arrays.toString(arr4));
    }

    private static void testAddAll() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(101);
        linkedList1.add(102);
        linkedList1.add(103);

        arrayList.addAll(linkedList1);
        System.out.println(arrayList);

        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(201);
        linkedList2.add(202);
        linkedList2.add(203);

        arrayList.addAll(5, linkedList2);
        System.out.println(arrayList);
    }

    private static void testIndexOf() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(327);

        System.out.println(arrayList.indexOf(2));
        System.out.println(arrayList.lastIndexOf(3));
        System.out.println(arrayList.lastIndexOf(999));
    }

    private static void testRetainAll() {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(101);
        linkedList1.add(102);
        linkedList1.add(103);

        arrayList1.retainAll(linkedList1);
        System.out.println(arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(3);

        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(1);
        linkedList2.add(2);
        linkedList2.add(3);
        linkedList2.add(4);

        arrayList2.retainAll(linkedList2);
        System.out.println(arrayList2);
    }

    private static void testRemoveAll() {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(101);
        linkedList1.add(102);
        linkedList1.add(103);

        arrayList1.removeAll(linkedList1);
        System.out.println(arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(3);

        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(1);
        linkedList2.add(2);
        linkedList2.add(3);
        linkedList2.add(4);

        arrayList2.removeAll(linkedList2);
        System.out.println(arrayList2);
    }

    private static void testContainsAll() {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(101);
        linkedList1.add(102);
        linkedList1.add(103);

        System.out.println(arrayList1.containsAll(linkedList1));

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(3);
        arrayList2.add(4);

        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(1);
        linkedList2.add(2);
        linkedList2.add(3);
        linkedList2.add(4);
        linkedList2.add(4);

        System.out.println(arrayList2.containsAll(linkedList2));
    }
}