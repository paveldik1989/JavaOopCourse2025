package ru.academits.dik.tree_main;

import ru.academits.dik.tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.add(8);
        tree1.add(3);
        tree1.add(10);
        tree1.add(1);
        tree1.add(6);
        tree1.add(14);
        tree1.add(4);
        tree1.add(7);
        tree1.add(13);
        tree1.add(20);
        tree1.add(17);
        tree1.add(25);
        tree1.add(18);

        System.out.println(tree1.contains(100));
        System.out.println(tree1);
        System.out.println(tree1.getSize());
        System.out.println(tree1.contains(18));

        System.out.println("Обход в глубину рекурсией:");
        tree1.depthRecursionTraverse(System.out::println);

        System.out.println("Обход в глубину:");
        tree1.depthTraverse(System.out::println);

        System.out.println("Проверка удаления:");

        for (int i = 0; i < 26; i++) {
            BinaryTree<Integer> tree2 = new BinaryTree<>();
            tree2.add(8);
            tree2.add(3);
            tree2.add(10);
            tree2.add(1);
            tree2.add(6);
            tree2.add(14);
            tree2.add(4);
            tree2.add(7);
            tree2.add(13);
            tree2.add(20);
            tree2.add(17);
            tree2.add(25);
            tree2.add(18);

            System.out.println(tree2);

            if (tree2.remove(i)) {
                System.out.println(tree2 + " Удаляемый элемент: " + i);
            }
        }
    }
}