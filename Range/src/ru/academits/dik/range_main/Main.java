package ru.academits.dik.range_main;

import ru.academits.dik.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите точку начала отрезка: ");
        double from = scanner.nextDouble();

        System.out.print("Введите точку конца отрезка: ");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.println("Вами задан отрезок: " + range);
        System.out.println("Длина отрезка: " + range.getLength());

        System.out.print("Введите точку для проверки находится ли она внутри отрезка: ");
        double point = scanner.nextDouble();

        System.out.println("Лежит ли точка внутри отрезка: " + range.isInside(point));

        System.out.print("Введите новое значение начала отрезка: ");
        from = scanner.nextDouble();

        range.setFrom(from);

        System.out.print("Введите новое значение конца отрезка: ");
        to = scanner.nextDouble();

        range.setTo(to);

        System.out.println("Теперь отрезок выглядит так: " + range);
        System.out.println("Начало отрезка: " + range.getFrom());
        System.out.println("Конец отрезка: " + range.getTo());
    }
}