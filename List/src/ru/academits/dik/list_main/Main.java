package ru.academits.dik.list_main;

import ru.academits.dik.list.List;

public class Main {
    public static void main(String[] args) {
        testGetSize();
        testAddFirst();
        testAdd();
        testGetFirst();
        testRemoveFirst();
        testRemove();
        testGet();
        testSet();
        testRemoveByValue();
        testReverse();
        testCopy();
    }

    public static void testGetSize() {
        System.out.println("Тест получения размера:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println("Размер списка: " + list.getSize());
        System.out.println();
    }

    public static void testAddFirst() {
        System.out.println("Тест добавления первого элемента:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println(list);
        System.out.println();
    }

    public static void testAdd() {
        System.out.println("Тест добавления элемента по индексу:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        System.out.println("Исходный список: " + list);
        int index = 2;
        System.out.println("Индекс для добавления элемента: " + index);

        list.add(index, 327);

        System.out.println(list);
        System.out.println();
    }

    public static void testGetFirst() {
        System.out.println("Тест получения первого элемента:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println(list.getFirst());
        System.out.println();
    }

    public static void testRemoveFirst() {
        System.out.println("Тест удаления первого элемента:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.removeFirst();

        System.out.println(list);
        System.out.println();
    }

    public static void testRemove() {
        System.out.println("Тест удаления элемента по индексу:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        System.out.println("Исходный список: " + list);
        int index = 2;
        System.out.println("Индекс для удаления элемента: " + index);

        System.out.println("Удален элемент: " + list.remove(index));

        System.out.println(list);
        System.out.println();
    }

    public static void testGet() {
        System.out.println("Тест получения элемента по индексу:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println(list.getValue(1));
        System.out.println();
    }

    public static void testSet() {
        System.out.println("Тест изменения значения элемента по индексу:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        System.out.println("Изменен элемент: " + list.setValue(1, 327));

        System.out.println(list);
        System.out.println();
    }

    public static void testRemoveByValue() {
        System.out.println("Тест удаления значения элемента по значению:");

        List<Integer> list1 = new List<>();
        list1.addFirst(1);
        list1.addFirst(2);
        list1.addFirst(3);
        list1.addFirst(4);
        list1.addFirst(null);
        System.out.println(list1);

        Integer x1 = 327;
        System.out.println("Удаление значения " + x1 + ", " + list1.remove(x1));
        System.out.println(list1);

        x1 = 2;
        System.out.println("Удаление значения " + x1 + ", " + list1.remove(x1));
        System.out.println(list1);

        List<Integer> list2 = new List<>();
        list2.addFirst(1);
        System.out.println(list2);
        Integer x2 = 1;

        System.out.println(list2.remove(x2));
        System.out.println(list2);
    }

    public static void testReverse() {
        System.out.println("Тест разворота списка:");
        List<Integer> list1 = new List<>();
        System.out.println("Исходный список: " + list1);
        list1.reverse();
        System.out.println("Развернутый список: " + list1);

        List<Integer> list2 = new List<>();
        list2.addFirst(1);
        list2.addFirst(2);
        list2.addFirst(3);
        list2.addFirst(4);
        list2.addFirst(5);
        System.out.println("Исходный список: " + list2);

        list2.reverse();
        System.out.println("Развернутый список: " + list2);
        System.out.println();
    }

    public static void testCopy() {
        System.out.println("Тест копирования списка:");

        List<Integer> list = new List<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println(list);
        List<Integer> coppiedList = list.copy();

        list.removeFirst();

        System.out.println(coppiedList);
    }
}