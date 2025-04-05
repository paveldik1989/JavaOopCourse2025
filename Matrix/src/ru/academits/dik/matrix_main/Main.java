package ru.academits.dik.matrix_main;

import ru.academits.dik.matrix.Matrix;
import ru.academits.dik.vector.Vector;

public class Main {
    public static void main(String[] args) {
        testConstructors();
        testGetSize();
        testGetSetRowColumn();
        testTranspose();
        testMultiplicationByScalar();
        testAddSubtract();
        testGetDeterminant();
        testMultiplication();
        testMultiplicationByVector();
    }

    public static void testConstructors() {
        System.out.println("Тест конструкторов:");

        try {
            Matrix matrix1 = new Matrix(-2, 2);
            System.out.println(matrix1);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            Matrix matrix1 = new Matrix(2, -2);
            System.out.println(matrix1);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            double[][] matrix = {{}, {}};
            Matrix matrix1 = new Matrix(matrix);
            System.out.println(matrix1);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        Matrix matrix3 = new Matrix(3, 2);
        System.out.println(matrix3);

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4);

        double[][] matrix = {{1}, {1, 1}, {1, 1, 1}};
        Matrix matrix5 = new Matrix(matrix);
        System.out.println(matrix5);

        Matrix matrix6 = new Matrix(matrix5);
        System.out.println(matrix6);
    }

    public static void testGetSize() {
        System.out.println("Тест получения размера:");

        Matrix matrix1 = new Matrix(3, 2);
        System.out.println(matrix1);
        System.out.println(matrix1.getRowsAmount());
        System.out.println(matrix1.getColumnsAmount());

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix2 = new Matrix(vectors);
        System.out.println(matrix2);
        System.out.println(matrix2.getRowsAmount());
        System.out.println(matrix2.getColumnsAmount());
    }

    public static void testGetSetRowColumn() {
        System.out.println("Тест получения и задания строк и столбцов:");

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix1 = new Matrix(vectors);
        System.out.println(matrix1.getRow(1));
        System.out.println(matrix1.getColumn(1));

        matrix1.setRow(0, new Vector(new double[]{2, 2, 2, 2, 2}));
        System.out.println(matrix1);

        matrix1.setColumn(0, new Vector(new double[]{3, 3, 3}));
        System.out.println(matrix1);
    }

    public static void testTranspose() {
        System.out.println("Тест транспонирования:");

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix1 = new Matrix(vectors);
        System.out.println(matrix1);
        matrix1.transpose();
        System.out.println(matrix1);
    }

    public static void testMultiplicationByScalar() {
        System.out.println("Тест умножения на скаляр:");

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix1 = new Matrix(vectors);
        System.out.println(matrix1);

        matrix1.multiplyByScalar(20);
        System.out.println(matrix1);
    }

    public static void testAddSubtract() {
        System.out.println("Тест сложения и вычитания:");

        Vector[] vectors = {new Vector(new double[]{1}), new Vector(new double[]{1, 1}), new Vector(new double[]{1, 1, 1, 1, 1})};
        Matrix matrix1 = new Matrix(vectors);
        Matrix matrix2 = new Matrix(vectors);
        matrix1.add(matrix2);
        System.out.println(matrix1);

        matrix1.multiplyByScalar(20);
        System.out.println(matrix1);
        System.out.println(matrix2);

        matrix1.subtract(matrix2);
        System.out.println(matrix1);
        System.out.println(Matrix.getSum(matrix1, matrix2));
        System.out.println(Matrix.getDifference(matrix1, matrix2));
    }

    public static void testGetDeterminant() {
        System.out.println("Тест расчета определителя:");

        Matrix matrix1 = new Matrix(new double[][]{{2, 3, 5}, {7, 11, 13}, {17, 19, 23}});
        System.out.println(matrix1.getDeterminant());
        Matrix matrix2 = new Matrix(new double[][]{{0, 0, 1}, {0, 1, 0}, {1, 0, 0}});
        System.out.println(matrix2.getDeterminant());
    }

    public static void testMultiplication() {
        System.out.println("Тест умножения матриц:");
        Vector[] vectors1 = {new Vector(new double[]{2, 1}), new Vector(new double[]{-3, 0}), new Vector(new double[]{4, -1})};
        Vector[] vectors2 = {new Vector(new double[]{5, -1, 6}), new Vector(new double[]{-3, 0, 7})};
        Matrix matrix1 = new Matrix(vectors1);
        Matrix matrix2 = new Matrix(vectors2);

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(Matrix.getProduct(matrix1, matrix2));
    }

    public static void testMultiplicationByVector() {
        System.out.println("Тест умножения матрицы на вектор:");

        Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});

        try {
            Vector vector = new Vector(new double[]{1, 1});
            System.out.println(matrix.multiplyByVector(vector));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        Vector vector = new Vector(new double[]{1, 2, 3});
        System.out.println(matrix.multiplyByVector(vector));
    }
}