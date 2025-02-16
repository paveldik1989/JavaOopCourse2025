package ru.academits.dik.vector;

import java.util.Arrays;

public class Vector {
    double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, а вы указали: " + n);
        }
        this.array = new double[n];
    }

    public Vector(Vector vector) {
        this.array = Arrays.copyOf(vector.array, vector.array.length);
    }

    public Vector(double[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        this(n);
        System.arraycopy(array, 0, this.array, 0, Math.min(n, array.length));
    }

    public int getSize() {
        return array.length;
    }

    public void add(Vector vector) {
        if (array.length >= vector.getSize()) {
            for (int i = 0; i < vector.getSize(); i++) {
                array[i] += vector.array[i];
            }
        } else {
            double[] addedArray = new double[vector.getSize()];
            for (int i = 0; i < array.length; i++) {
                addedArray[i] = array[i] + vector.array[i];
            }
            System.arraycopy(vector.array, array.length, addedArray, array.length, vector.getSize() - array.length);
            array = addedArray;
        }
    }

    public void subtract(Vector vector) {
        if (array.length >= vector.getSize()) {
            for (int i = 0; i < vector.getSize(); i++) {
                array[i] -= vector.array[i];
            }
        } else {
            double[] subtractedArray = new double[vector.getSize()];
            for (int i = 0; i < array.length; i++) {
                subtractedArray[i] = array[i] - vector.array[i];
            }
            for (int i = array.length; i < vector.getSize(); i++) {
                subtractedArray[i] = -vector.array[i];
            }
//            System.arraycopy(vector.array, array.length, subtractedArray, array.length, vector.getSize() - array.length);
            array = subtractedArray;
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;
        for (double vectorComponent : array) {
            squaresSum += Math.pow(vectorComponent, 2);
        }
        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException(String.format("Индекс должен быть в диапазоне 0-%d, а вы указали: %d", array.length - 1, index));
        }
        return array[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException(String.format("Индекс должен быть в диапазоне 0-%d, а вы указали: %d", array.length - 1, index));
        }
        array[index] = component;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorSum = new Vector(vector1);
        vectorSum.add(vector2);
        return vectorSum;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorSubtract = new Vector(vector1);
        vectorSubtract.subtract(vector2);
        return vectorSubtract;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); i++) {
            scalarProduct += vector1.getComponent(i) * vector2.getComponent(i);
        }
        return scalarProduct;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Arrays.toString(array));
        stringBuilder.delete(0, 1);
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append('}');
        stringBuilder.insert(0, '{');

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Vector v = (Vector) o;

        if (array.length != v.getSize()) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            if (Double.compare(array[i], v.getComponent(i)) != 0) {
                return false;
            }
        }

        return true;
    }
}