package ru.academits.dik.matrix;

import ru.academits.dik.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsAmount, int columnsAmount) {
        if (rowsAmount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0. Передано значение: " + rowsAmount);
        }

        if (columnsAmount <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть > 0. Передано значение: " + columnsAmount);
        }

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            rows[i] = new Vector(columnsAmount);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0. Передано значение: 0");
        }

        int maxVectorSize = 0;

        for (Vector row : rows) {
            if (row.getSize() > maxVectorSize) {
                maxVectorSize = row.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxVectorSize);
            this.rows[i].add(rows[i]);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsAmount()];

        for (int i = 0; i < matrix.getRowsAmount(); i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0. Передано значение: 0");
        }

        int maxLength = 0;

        for (double[] row : components) {
            if (row.length > maxLength) {
                maxLength = row.length;
            }
        }

        if (maxLength == 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть > 0. Передано значение: 0");
        }

        rows = new Vector[components.length];

        for (int i = 0; i < components.length; i++) {
            rows[i] = new Vector(maxLength, components[i]);
        }
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        checkRowIndex(rowIndex);

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        checkRowIndex(rowIndex);

        if (row.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Размер вектора должен быть равен количеству столбцов." +
                    " Размер вектора: " + row.getSize() + ". Количество столбцов: " + getColumnsAmount());
        }

        rows[rowIndex] = new Vector(row);
    }

    private void checkRowIndex(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= getRowsAmount()) {
            throw new IndexOutOfBoundsException("Индекс строки должен быть больше или равен 0 и меньше количества строк в матрице." +
                    " Количество строк в матрице: " + getRowsAmount() + ". Передано значение: " + rowIndex);
        }
    }

    private void checkColumnIndex(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Индекс столбца должен быть больше или равен 0 и меньше количества столбцов в матрице." +
                    " Количество столбцов в матрице: " + getColumnsAmount() + ". Передано значение: " + columnIndex);
        }
    }

    public Vector getColumn(int columnIndex) {
        checkColumnIndex(columnIndex);

        Vector column = new Vector(getRowsAmount());

        for (int i = 0; i < getRowsAmount(); i++) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public void setColumn(int columnIndex, Vector column) {
        checkColumnIndex(columnIndex);

        if (column.getSize() != getRowsAmount()) {
            throw new IllegalArgumentException("Размер вектора должен быть равен количеству строк." +
                    " Размер вектора: " + column.getSize() + ". Количество строк: " + getRowsAmount());
        }

        for (int i = 0; i < getRowsAmount(); i++) {
            rows[i].setComponent(columnIndex, column.getComponent(i));
        }
    }

    public void transpose() {
        Vector[] columns = new Vector[getColumnsAmount()];

        for (int i = 0; i < getColumnsAmount(); i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < getRowsAmount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < getRowsAmount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static void checkMatricesSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Количество строк в матрицах должно быть одинаковым." +
                    " Количество строк в матрице 1 = " + matrix1.getRowsAmount() + ". Количество строк в матрице 2 = " + matrix2.getRowsAmount());
        }

        if (matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Количество столбцов в матрицах должно быть одинаковым." +
                    " Количество столбцов в матрице 1 = " + matrix1.getColumnsAmount() + ". Количество столбцов в матрице 2 = " + matrix2.getColumnsAmount());
        }
    }

    public double getDeterminant() {
        if (getRowsAmount() != getColumnsAmount()) {
            throw new IllegalStateException("Для вычисления определителя количество строк должно быть равно количеству столбцов.");
        }

        Matrix matrix = new Matrix(this);
        int sign = 1;
        final double EPSILON = 1.0E-10;

        for (int i = 0; i < matrix.getRowsAmount() - 1; i++) {
            int j = i + 1;

            while (Math.abs(matrix.rows[i].getComponent(i)) <= EPSILON) {
                if (j == matrix.getRowsAmount()) {
                    return 0;
                }

                if (Math.abs(matrix.rows[j].getComponent(i)) > EPSILON) {
                    swapRows(matrix, i, j);
                    sign *= -1;
                    break;
                }

                j++;
            }

            subtractRow(matrix, i);
        }

        double determinant = 1;

        for (int i = 0; i < matrix.getRowsAmount(); i++) {
            determinant *= matrix.rows[i].getComponent(i);
        }

        return determinant * sign;
    }

    private static void swapRows(Matrix matrix, int rowIndex1, int rowIndex2) {
        Vector temp = matrix.rows[rowIndex1];
        matrix.rows[rowIndex1] = matrix.rows[rowIndex2];
        matrix.rows[rowIndex2] = temp;
    }

    private static void subtractRow(Matrix matrix, int rowIndex) {
        for (int i = rowIndex + 1; i < matrix.getRowsAmount(); i++) {
            Vector rowToSubtract = new Vector(matrix.rows[rowIndex]);
            rowToSubtract.multiplyByScalar(matrix.rows[i].getComponent(rowIndex) / matrix.rows[rowIndex].getComponent(rowIndex));
            matrix.rows[i].subtract(rowToSubtract);
        }
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Количество столбцов в первой матрице должно равняться количеству строк во второй матрице. " +
                    "Количество столбцов в первой матрице: " + matrix1.getColumnsAmount() + ". Количество строк во второй матрице: " + matrix2.getRowsAmount());
        }

        Matrix multipliedMatrix = new Matrix(matrix1.getRowsAmount(), matrix2.getColumnsAmount());
        final int rowsAmount = matrix1.getRowsAmount();
        final int columnsAmount = matrix2.getColumnsAmount();

        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < columnsAmount; j++) {
                multipliedMatrix.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return multipliedMatrix;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Размер вектора должен быть равен количеству столбцов в матрице. " +
                    "Количество элементов в векторе: " + vector.getSize() + ". Количество столбцов в матрице: " + getColumnsAmount());
        }

        Vector resultVector = new Vector(getRowsAmount());

        for (int i = 0; i < getRowsAmount(); i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(vector, rows[i]));
        }

        return resultVector;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix sumMatrix = new Matrix(matrix1);
        sumMatrix.add(matrix2);
        return sumMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix differenceMatrix = new Matrix(matrix1);
        differenceMatrix.subtract(matrix2);
        return differenceMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Arrays.toString(rows));
        stringBuilder.setCharAt(0, '{');
        stringBuilder.setCharAt(stringBuilder.length() - 1, '}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            return false;
        }

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }
}