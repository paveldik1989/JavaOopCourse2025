package ru.academits.dik.person_main;

import ru.academits.dik.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Vasya", 1));
        persons.add(new Person("Natasha", 21));
        persons.add(new Person("Vasya", 28));
        persons.add(new Person("Egor", 39));
        persons.add(new Person("Igor", 13));
        persons.add(new Person("Igor", 78));
        persons.add(new Person("Petr", 67));
        persons.add(new Person("Elena", 18));

        //А) получить список уникальных имен
        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println(uniqueNames);

        //Б) вывести список уникальных имен в формате:
        //Имена: Иван, Сергей, Петр
        String uniqueNames1 = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", ""));
        System.out.println(uniqueNames1);

        //В) получить список людей младше 18, посчитать для них средний возраст
        List<Person> nonAdults = persons.stream().filter(x -> x.getAge() < 18)
                .toList();
        System.out.println(nonAdults);

        nonAdults.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresent(System.out::println);

        //Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> map = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(map);

//        Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(System.out::println);

//        Создать бесконечный поток корней чисел. С консоли прочитать число – сколько элементов нужно вычислить,
//        затем – распечатать эти элементы
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сколько элементов необходимо вычислить: ");
        int elementsAmount = scanner.nextInt();
        DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt)
                .limit(elementsAmount)
                .forEach(System.out::println);


        //Попробовать реализовать бесконечный поток чисел Фибоначчи
        System.out.print("Введите сколько чисел Фибоначчи необходимо вычислить: ");
        int fibonacciNumbersAmount = scanner.nextInt();

        Stream.iterate(new long[]{0, 1}, x -> new long[]{x[1], x[0] + x[1]})
                .mapToLong(x -> x[0])
                .limit(fibonacciNumbersAmount)
                .forEach(System.out::println);
    }
}