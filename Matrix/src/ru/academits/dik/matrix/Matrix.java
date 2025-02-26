package ru.academits.dik.matrix;

import ru.academits.dik.vector.Vector;

import java.util.Arrays;

public class Matrix {
    Vector[] vectors;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть больше 0, а вы ввели: " + n);
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть больше 0, а вы ввели: " + m);
        }

        vectors = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Vector[] vectors) {
        int maxVectorSize = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxVectorSize) {
                maxVectorSize = vector.getSize();
            }
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(maxVectorSize);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                this.vectors[i].setComponent(j, vectors[i].getComponent(j));
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.getRowsAmount(), matrix.getColumnsAmount());

        for (int i = 0; i < matrix.getRowsAmount(); i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int maxLength = 0;

        for (double[] row : matrix) {
            if (row.length > maxLength) {
                maxLength = row.length;
            }
        }

        vectors = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            vectors[i] = new Vector(maxLength, matrix[i]);
        }
    }


    public int getRowsAmount() {
        return vectors.length;
    }

    public int getColumnsAmount() {
        return vectors[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex >= this.getRowsAmount()) {
            throw new IllegalArgumentException("Индекс строки не может быть больше или равен количеству строк в матрице." +
                    " Количество строк в матрице" + this.getRowsAmount() + ", а вы ввели: " + rowIndex);
        }

        return new Vector(vectors[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (rowIndex >= this.getRowsAmount()) {
            throw new IllegalArgumentException("Индекс строки не может быть больше или равен количеству строк в матрице." +
                    " Количество строк в матрице" + this.getRowsAmount() + ", а вы ввели: " + rowIndex);
        }

        vectors[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex >= this.getColumnsAmount()) {
            throw new IllegalArgumentException("Индекс столбца не может быть больше или равен количеству столбцов в матрице." +
                    " Количество столбцов в матрице" + this.getColumnsAmount() + ", а вы ввели: " + columnIndex);
        }


        Vector column = new Vector(this.getRowsAmount());

        for (int i = 0; i < this.getRowsAmount(); i++) {
            column.setComponent(i, vectors[i].getComponent(columnIndex));
        }

        return column;
    }

    public void setColumn(int columnIndex, Vector column) {
        if (columnIndex >= this.getColumnsAmount()) {
            throw new IllegalArgumentException("Индекс столбца не может быть больше или равен количеству столбцов в матрице." +
                    " Количество столбцов в матрице" + this.getColumnsAmount() + ", а вы ввели: " + columnIndex);
        }

        for (int i = 0; i < this.getRowsAmount(); i++) {
            vectors[i].setComponent(columnIndex, column.getComponent(i));
        }
    }

    public void transpose() {
        Vector[] columns = new Vector[this.getColumnsAmount()];

        for (int i = 0; i < this.getColumnsAmount(); i++) {
            columns[i] = new Vector(getColumn(i));
        }

        vectors = columns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : vectors) {
            vector.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        checkRowsAndColumnsAmount(this, matrix);

        for (int i = 0; i < this.getRowsAmount(); i++) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkRowsAndColumnsAmount(this, matrix);

        for (int i = 0; i < this.getRowsAmount(); i++) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    private static void checkRowsAndColumnsAmount(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Колчиство строк в матрицах должно быть одинаковым." +
                    " Количество строк в матрице 1 " + matrix1.getRowsAmount() + ". Количество строк в матрице 2 " + matrix2.getRowsAmount());
        }

        if (matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Колчиство столбцов в матрицах должно быть одинаковым." +
                    " Количество столбцов в матрице 1 " + matrix1.getColumnsAmount() + ". Количество столбцов в матрице 2 " + matrix2.getColumnsAmount());
        }
    }

    public double getDeterminant() {
        if (this.getRowsAmount() != this.getColumnsAmount()) {
            throw new IllegalArgumentException("Для вычисления определителя количество строк должно быть равно количеству столбцов.");
        }

        Matrix matrix = new Matrix(this);
        int sign = 1;

        for (int i = 0; i < matrix.getRowsAmount() - 1; i++) {
            int j = i;

            while (matrix.vectors[i].getComponent(i) == 0) {
                j++;

                if (j == matrix.getRowsAmount()) {
                    return 0;
                }

                if (matrix.vectors[j].getComponent(i) != 0) {
                    swapRows(matrix, i, j);
                    sign *= -1;
                    break;
                }
            }

            subtractRow(matrix, i);
        }

        double determinant = 1;

        for (
                int i = 0; i < matrix.getRowsAmount(); i++) {
            determinant *= matrix.vectors[i].getComponent(i);
        }

        System.out.println(matrix);
        return determinant * sign;
    }

    private static void swapRows(Matrix matrix, int rowIndex1, int rowIndex2) {
        Vector temp = matrix.vectors[rowIndex1];
        matrix.vectors[rowIndex1] = matrix.vectors[rowIndex2];
        matrix.vectors[rowIndex2] = temp;
    }

    private static void subtractRow(Matrix matrix, int rowIndex) {
        for (int i = rowIndex + 1; i < matrix.getRowsAmount(); i++) {
            Vector vectorToSubtract = new Vector(matrix.vectors[rowIndex]);
            vectorToSubtract.multiplyByScalar(matrix.vectors[i].getComponent(rowIndex) / matrix.vectors[rowIndex].getComponent(rowIndex));
            matrix.vectors[i].subtract(vectorToSubtract);
        }
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        Matrix multipliedMatrix = new Matrix(matrix1.getRowsAmount(), matrix2.vectors[0].getSize());

        for (int i = 0; i < matrix1.getRowsAmount(); i++) {
            for (int j = 0; j < matrix2.getColumnsAmount(); j++) {
                multipliedMatrix.vectors[i].setComponent(j, Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }
        return multipliedMatrix;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getColumnsAmount()) {
            throw new IllegalArgumentException("Размер вектора должен быть равен количеству столбцов в матрице." +
                    "Количество элементов в векторе: " + vector.getSize() + ". Количество столбцов в матрице: " + this.getColumnsAmount());
        }

        Vector resultVector = new Vector(vector.getSize());

        for (int i = 0; i < vector.getSize(); i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(vector, vectors[i]));
        }

        return resultVector;
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        checkRowsAndColumnsAmount(matrix1, matrix2);

        Matrix sumMatrix = new Matrix(matrix1);
        sumMatrix.add(matrix2);
        return sumMatrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        checkRowsAndColumnsAmount(matrix1, matrix2);

        Matrix subrtactedMatrix = new Matrix(matrix1);
        subrtactedMatrix.subtract(matrix2);
        return subrtactedMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Arrays.toString(vectors));
        stringBuilder.setCharAt(0, '{');
        stringBuilder.setCharAt(stringBuilder.length() - 1, '}');

        return stringBuilder.toString();
    }
}