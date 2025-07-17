package ru.academits.dik.tree_main;

import ru.academits.dik.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(13);
        tree.add(20);
        tree.add(17);
        tree.add(25);
        tree.add(18);

        System.out.println(tree.contains(100));
        System.out.println(tree);
        System.out.println(tree.getSize());
        System.out.println(tree.contains(18));

        System.out.println("Обход в глубину рекурсией:");
        tree.depthRecursionTraverse(System.out::println);

        System.out.println("Обход в глубину:");
        tree.depthTraverse(System.out::println);

        System.out.println(tree);
        System.out.println(tree.remove(8));
        System.out.println(tree);
    }
}
