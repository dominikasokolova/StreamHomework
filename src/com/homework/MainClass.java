package com.homework;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args) {
        //find out sum of all numbers between 1 and 10000 that are dividable by both 3 and 5 but not dividable by 7
        int sum = Stream.iterate(1, N -> N + 1 )
                .limit(10000)
                .filter(N -> (N % 5 == 0) && (N % 3 == 0) && (N % 7 != 0))
                .mapToInt(N -> N)
                .sum();
        System.out.println("Sum of numbers is: " + sum);

        //print first 100 even numbers that are not dividable by 8
        List<Integer> intList = Stream.iterate(1, N -> N + 1)
                                    .filter(N -> (N % 2 == 0) && (N % 8 != 0))
                                    .limit(100)
                                    .collect(Collectors.toList());
        System.out.println("First 100 numbers: " + intList);

        //print names (in alphabetic order) of all books that have price smaller than 100
        System.out.println("Book names: " + getBookNames());

        //find out how many books have name shorter than 5 characters
        System.out.println("Number of books with short name: " + getShortNames());

        //find out what is the average price of the book in the list
        System.out.println("Average price: " + getAveragePrice());

        //find out if all books in list are cheaper than 500
        System.out.println("Books are cheaper than 500: " + areCheaper());
    }

    public static List<String> getBookNames() {
        return getBooksList()
                .filter(N -> N.getPrice() < 100)
                .map(N -> N.getName())
                .sorted()
                .collect(Collectors.toList());
    }

    public static double getShortNames() {
        return getBooksList()
                    .filter(N -> N.getName().length() < 5)
                    .count();
    }

    public static double getAveragePrice() {
        return getBooksList().mapToDouble(N -> N.getPrice()).average().getAsDouble();
    }

    public static boolean areCheaper() {
        return getBooksList()
                .mapToInt(N -> N.getPrice())
                .allMatch(N -> N < 500);
    }

    public static Stream<Book> getBooksList() {
        return Stream.of(new Book("Abba", 150), new Book("Clion", 50), new Book("Book", 70),
                new Book("Hello world", 600), new Book("Java", 1000), new Book("SQL", 20));
    }
}
