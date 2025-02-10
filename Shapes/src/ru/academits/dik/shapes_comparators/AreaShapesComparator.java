package ru.academits.dik.shapes_comparators;

import ru.academits.dik.shapes.Shape;

import java.util.Comparator;

public class AreaShapesComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}
