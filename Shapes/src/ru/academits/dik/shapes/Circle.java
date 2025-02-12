package ru.academits.dik.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("{Круг, радиус: %f}", radius);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Circle circle = (Circle) o;
        return Double.compare(radius, circle.radius) == 0;
        //  if (o == null || getClass() != o.getClass()) return false; Идея почему то генерирует такой код, т.е. не делает проверку на равенство ссылок, это нормально?
        //  Circle circle = (Circle) o;
        //  return Double.compare(radius, circle.radius) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}