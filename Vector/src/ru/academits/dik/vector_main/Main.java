package ru.academits.dik.vector_main;

import ru.academits.dik.vector.Vector;

public class Main {
    public static void main(String[] args) {
        testConstructors();
        testAdd();
        testSubtract();
        testGetSetComponent();
        testEquals();
        testGetScalarProduct();
        testGetSize();
        testGetLength();
        testMultiplyByScalar();
        testReverse();
    }

    public static void testConstructors() {
        System.out.println("Тест создания векторов:");

        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{1, 1, 1});
        Vector vector4 = new Vector(10, new double[]{1, 1, 1});
        Vector vector5 = new Vector(2, new double[]{1, 1, 1});

        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector5);

        try {
            Vector vector6 = new Vector(-1);
            System.out.println(vector6);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    public static void testAdd() {
        System.out.println("Тест сложения векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        Vector vector2 = new Vector(new double[]{1, 1, 1});
        vector2.add(vector1);
        System.out.println(vector2);

        Vector vector3 = new Vector(new double[]{1, 1, 1, 1});
        vector3.add(vector1);
        System.out.println(vector3);

        Vector vector4 = new Vector(new double[]{1, 1});
        vector4.add(vector1);
        System.out.println(vector4);

        vector1.add(vector1);
        System.out.println(vector1);

        Vector vector5 = new Vector(new double[]{1, 1, 1});
        Vector vector6 = new Vector(new double[]{2, 2, 2});
        System.out.println(Vector.getSum(vector5, vector6));
    }

    public static void testSubtract() {
        System.out.println("Тест вычитания векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        Vector vector2 = new Vector(new double[]{1, 1, 1});
        vector2.subtract(vector1);
        System.out.println(vector2);

        Vector vector3 = new Vector(new double[]{1, 1, 1, 1});
        vector3.subtract(vector1);
        System.out.println(vector3);

        Vector vector4 = new Vector(new double[]{1, 1});
        vector4.subtract(vector1);
        System.out.println(vector4);

        vector1.subtract(vector1);
        System.out.println(vector1);

        Vector vector5 = new Vector(new double[]{1, 1, 1});
        Vector vector6 = new Vector(new double[]{2, 2, 2});
        System.out.println(Vector.getDifference(vector5, vector6));
    }

    public static void testGetSetComponent() {
        System.out.println("Тест получения и задания компонента:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});

        try {
            vector1.getComponent(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        System.out.println(vector1.getComponent(0));

        try {
            vector1.setComponent(-1, 9999);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        vector1.setComponent(0, 100500);
        System.out.println(vector1);
    }

    public static void testEquals() {
        System.out.println("Тест равенства векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        Vector vector2 = new Vector(new double[]{1, 1, 1});
        Vector vector3 = new Vector(new double[]{1, 1, 1, 1});
        Vector vector4 = new Vector(new double[]{2, 2, 2});

        System.out.println(vector1.equals(vector1));
        System.out.println(vector1.equals(vector2));
        System.out.println(vector2.equals(vector1));
        System.out.println(vector1.equals(vector3));
        System.out.println(vector3.equals(vector1));
        System.out.println(vector1.equals(vector4));
        System.out.println(vector4.equals(vector1));
    }

    public static void testGetScalarProduct() {
        System.out.println("Тест скалярного произведения векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        Vector vector2 = new Vector(new double[]{2, 2, 2});
        System.out.println(Vector.getScalarProduct(vector1, vector2));

        Vector vector3 = new Vector(new double[]{1, 1, 1, 1});
        System.out.println(Vector.getScalarProduct(vector2, vector3));
    }

    public static void testGetSize() {
        System.out.println("Тест получения размерности векторов:");

        Vector vector1 = new Vector(1);
        System.out.println(vector1.getSize());
    }

    public static void testGetLength() {
        System.out.println("Тест получения длины векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        System.out.println(vector1.getLength());

        Vector vector2 = new Vector(new double[]{1, 1, 1, 1});
        System.out.println(vector2.getLength());
    }

    public static void testMultiplyByScalar() {
        System.out.println("Тест умножения вектора на скаляр:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        vector1.multiplyByScalar(2);
        System.out.println(vector1);
    }

    public static void testReverse() {
        System.out.println("Тест разворота векторов:");

        Vector vector1 = new Vector(new double[]{1, 1, 1});
        vector1.reverse();
        System.out.println(vector1);
    }
}