package ru.academits.dik.lambdas_main;

import ru.academits.dik.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Vasya", 1),
                new Person("Natasha", 21),
                new Person("Vasya", 28),
                new Person("Egor", 39),
                new Person("Igor", 13),
                new Person("Igor", 78),
                new Person("Petr", 67),
                new Person("Elena", 18)
        );

        // А) получить список уникальных имен
        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.print("Список уникальных имен: ");
        System.out.println(uniqueNames);

        // Б) вывести список уникальных имен в формате:
        // Имена: Иван, Сергей, Петр
        String formatedNames = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", ""));
        System.out.print("Список уникальных имен в указанном формате: ");
        System.out.println(formatedNames);

        // В) получить список людей младше 18, посчитать для них средний возраст
        List<Person> nonAdults = persons.stream()
                .filter(x -> x.getAge() < 18)
                .toList();

        if (nonAdults.isEmpty()) {
            System.out.println("Нет людей младше 18 лет");
        } else {
            System.out.print("Список людей младше 18 лет: ");
            System.out.println(nonAdults);
            System.out.print("Средний возраст людей младше 18 лет: ");

            nonAdults.stream()
                    .mapToInt(Person::getAge)
                    .average()
                    .ifPresent(System.out::println);
        }

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> namesAverageAges = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.print("Map, в котором ключи – имена, а значения – средний возраст: ");
        System.out.println(namesAverageAges);

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        System.out.println("Люди, возраст которых от 20 до 45 в порядке убывания возраста:");
        persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(System.out::println);

        // Создать бесконечный поток корней чисел. С консоли прочитать число – сколько элементов нужно вычислить,
        // затем – распечатать эти элементы
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сколько корней чисел необходимо вычислить: ");
        int squareRootsAmount = scanner.nextInt();

        System.out.println("Корни:");
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(squareRootsAmount)
                .forEach(System.out::println);

        // Попробовать реализовать бесконечный поток чисел Фибоначчи
        System.out.print("Введите сколько чисел Фибоначчи необходимо вычислить: ");
        int fibonacciNumbersAmount = scanner.nextInt();

        System.out.println("Числа Фибоначчи:");

        Stream.iterate(new long[]{0, 1}, x -> {
                    long temp = x[0];
                    x[0] = x[1];
                    x[1] += temp;
                    return x;
                })
                .mapToLong(x -> x[0])
                .limit(fibonacciNumbersAmount)
                .forEach(System.out::println);
    }
}