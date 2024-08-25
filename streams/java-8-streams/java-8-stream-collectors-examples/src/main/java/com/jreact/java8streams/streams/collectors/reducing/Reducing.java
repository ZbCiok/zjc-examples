package com.jreact.java8streams.streams.collectors.reducing;

import com.jreact.java8streams.data.Student;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Reducing {

    private final List<Student> students = Arrays.asList(
            new Student("John Smith", "Miami", 7.38, 19),
            new Student("Mike Miles", "New York", 8.4, 21),
            new Student("Michael Peterson", "New York", 7.5, 20),
            new Student("James Robertson", "Miami", 9.1, 20),
            new Student("Joe Murray", "New York", 7.9, 19),
            new Student("Kyle Miller", "Miami", 9.83, 20)
    );

    public void reducingOp01() {
        Stream<Integer> s = Stream.of(5, 10, 20, 50);
        Integer i = s.collect(Collectors.reducing((integer, integer2)
                        -> integer2 - integer)).orElse(-1);

        System.out.println(i);
    }

    public void reducingOp02() {
        Map<String, Optional<Student>> reduceByCityAvgGrade = students.stream()
                .collect(Collectors
                        .groupingBy(Student::getCity,
                                Collectors.reducing(BinaryOperator
                                        .maxBy(Comparator
                                                .comparing(Student::getAvgGrade)))));
        System.out.println(reduceByCityAvgGrade);
    }

    public void reducingOpId01() {
        Stream<Integer> s = Stream.of(5, 10, 20, 50);
        Integer i = s.collect(Collectors.reducing(1, (integer, integer2)
                -> integer2 * integer));
        System.out.println(i);
    }

    public void reducingOpId02() {
        Map<String, Student> reduceByCityAvgGrade = students.stream()
                .collect(Collectors
                        .groupingBy(Student::getCity,
                                Collectors.reducing(new Student("x", "x", 0.0, 0),
                                        BinaryOperator.maxBy(Comparator
                                                .comparing(Student::getAvgGrade)))));
        System.out.println(reduceByCityAvgGrade);
    }

    public void reducingOpIdFun01() {
        Stream<Integer> s = Stream.of(5, 10, 20, 50).parallel();
        String str = s.collect(Collectors.reducing(
                "",
                x -> Integer.toString(x),
                (s1, s2) -> s1 + s2));
        System.out.println(str);
    }

    public void reducingOpIdFun02() {
        double largestAverageGrade = students.stream()
                .collect(Collectors.reducing(0.0, Student::getAvgGrade,
                        BinaryOperator.maxBy(Comparator.comparingDouble(value -> value))));
        System.out.println(largestAverageGrade);
    }
}
