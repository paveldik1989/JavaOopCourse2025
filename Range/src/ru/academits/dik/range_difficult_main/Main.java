package ru.academits.dik.range_difficult_main;

import ru.academits.dik.range_difficult.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range[][] testPairsRanges = {
                {new Range(1, 2), new Range(3, 4)},
                {new Range(1, 3), new Range(2, 4)},
                {new Range(1, 4), new Range(2, 3)},
                {new Range(3, 4), new Range(1, 2)},
                {new Range(2, 4), new Range(1, 3)},
                {new Range(2, 3), new Range(1, 4)}
        };

        for (Range[] testPairRanges : testPairsRanges) {
            System.out.println("Тестовые  отрезки 1 и 2: " + Arrays.toString(testPairRanges));
            System.out.println("Пересечение отрезков: " + testPairRanges[0].getIntersection(testPairRanges[1]));
            System.out.println("Объединение отрезков: " + Arrays.toString(testPairRanges[0].getUnion(testPairRanges[1])));
            System.out.println("Вычитание из отрезка 1 отрезка 2: " + Arrays.toString(testPairRanges[0].getDifference(testPairRanges[1])));
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите точку начала отрезка 1: ");
        double from1 = scanner.nextDouble();

        System.out.print("Введите точку конца отрезка 1: ");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.print("Введите точку начала отрезка 2: ");
        double from2 = scanner.nextDouble();

        System.out.print("Введите точку конца отрезка 2: ");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        Range rangesIntersection = range1.getIntersection(range2);
        Range[] rangesUnion = range1.getUnion(range2);
        Range[] rangesDifference = range1.getDifference(range2);

        System.out.println("Отрезок1: " + range1);
        System.out.println("Отрезок2: " + range2);
        System.out.println("Пересечение отрезков: " + rangesIntersection);
        System.out.println("Объединение отрезков: " + Arrays.toString(rangesUnion));
        System.out.println("Вычитание из отрезка 1 отрезка 2: " + Arrays.toString(rangesDifference));
    }
}