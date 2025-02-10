package ru.academits.dik.shapes_main;

import ru.academits.dik.shapes.*;
import ru.academits.dik.shapes_comparators.AreaShapesComparator;
import ru.academits.dik.shapes_comparators.PerimeterShapesComparator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape circle1 = new Circle(1);
        Shape circle2 = new Circle(2);
        Shape circle3 = new Circle(3);
        Shape rectangle1 = new Rectangle(1, 4);
        Shape rectangle2 = new Rectangle(2, 4);
        Shape rectangle3 = new Rectangle(3, 4);
        Shape square1 = new Square(1);
        Shape square2 = new Square(2);
        Shape square3 = new Square(3);
        Shape triangle1 = new Triangle(0, 0, 1, 1, 1, 0);
        Shape triangle2 = new Triangle(0, 0, 1, 1, 2, 0);
        Shape triangle3 = new Triangle(0, 0, 1, 1, 3, 0);

        Shape[] shapes = {circle1, circle2, circle3, rectangle1, rectangle2, rectangle3, square1, square2, square3,
                triangle1, triangle2, triangle3};

        System.out.println("Массив фигур: " + Arrays.toString(shapes));

        System.out.println("Площадь фигур: ");
        for (Shape shape : shapes) {
            System.out.println(shape.getArea());
        }

        System.out.println("Периметр фигур: ");
        for (Shape shape : shapes) {
            System.out.println(shape.getPerimeter());
        }

        Arrays.sort(shapes, new AreaShapesComparator());
        System.out.println("Фигура с наибольшей площадью: " + shapes[shapes.length - 1]);

        Arrays.sort(shapes, new PerimeterShapesComparator());
        System.out.println("Фигура со вторым по величине периметром: " + shapes[shapes.length - 2]);
    }
}