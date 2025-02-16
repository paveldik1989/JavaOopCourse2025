package ru.academits.dik.shapes_main;

import ru.academits.dik.shapes.*;
import ru.academits.dik.shapes_comparators.ShapesAreaComparator;
import ru.academits.dik.shapes_comparators.ShapesPerimeterComparator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(1),
                new Circle(2),
                new Circle(3),
                new Rectangle(1, 4),
                new Rectangle(2, 4),
                new Rectangle(3, 4),
                new Square(1),
                new Square(2),
                new Square(3),
                new Triangle(0, 0, 1, 1, 1, 0),
                new Triangle(0, 0, 1, 1, 2, 0),
                new Triangle(0, 0, 1, 1, 3, 0)};

        System.out.println("Массив фигур: " + Arrays.toString(shapes));

        System.out.println("Площадь фигур:");

        for (Shape shape : shapes) {
            System.out.println(shape.getArea());
        }

        System.out.println("Периметр фигур:");

        for (Shape shape : shapes) {
            System.out.println(shape.getPerimeter());
        }

        Arrays.sort(shapes, new ShapesAreaComparator());
        System.out.println("Фигура с наибольшей площадью: " + shapes[shapes.length - 1]);
        System.out.println("Ширина этой фигуры: " + shapes[shapes.length - 1].getWidth());
        System.out.println("Высота этой фигуры: " + shapes[shapes.length - 1].getHeight());

        Arrays.sort(shapes, new ShapesPerimeterComparator());
        System.out.println("Фигура со вторым по величине периметром: " + shapes[shapes.length - 2]);
    }
}