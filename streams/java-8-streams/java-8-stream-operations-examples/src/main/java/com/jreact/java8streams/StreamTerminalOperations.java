package com.jreact.java8streams;

import com.jreact.java8streams.data.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class StreamTerminalOperations {
    public void streamAnyMatch() {
        List<String> stringList = new ArrayList<String>();

        stringList.add("Java Guides");
        stringList.add("Python Guides");
        stringList.add("C Guides");

        Stream<String> stream = stringList.stream();

        boolean anyMatch = stream.anyMatch((value) -> { return value.startsWith("Java"); });
        System.out.println(anyMatch);
    }

    public void streamAllMatch() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        Predicate<String> containsDigit = s -> s.contains("\\d+") == false;
        boolean match = stream.allMatch(containsDigit);

        System.out.println(match);
    }

    public void streamNoneMatch() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        boolean answer = stream.noneMatch(str -> (str.length() == 10));
        System.out.println(answer);
    }

    public void collectToMap01() {
        Stream<String> s = Stream.of("apple", "banana", "orange");
        Map<Character, String> m = s.collect(Collectors.toMap(s1 -> s1.charAt(0), s1 -> s1));
        System.out.println(m);
    }

    // IllegalStateException - duplicate keys
    public void collectToMap02() {
        Stream<String> s = Stream.of("apple", "banana", "apricot", "orange");
        Map<Character, String> m = s.collect(Collectors.toMap(s1 -> s1.charAt(0), s1 -> s1));
        System.out.println(m);
    }

    public void collectToMap03() {
        Stream<String> s = Stream.of("apple", "banana", "apricot", "orange", "apple");
        Map<Character, String> m = s.collect(Collectors.toMap(s1 -> s1.charAt(0),
                                             s1 -> s1,
                                             (s1, s2) -> s1 + "|" + s2));
        System.out.println(m);
    }

    public void collectToMap04() {
        Stream<String> s = Stream.of("apple", "banana", "apricot", "orange", "apple");
        LinkedHashMap<Character, String> m = s.collect(
                Collectors.toMap(s1 -> s1.charAt(0),
                                 s1 -> s1,
                                 (s1, s2) -> s1 + "|" + s2,
                                  LinkedHashMap::new));
        System.out.println(m);
    }

    public void streamCount() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 7, 9, 22, 19, 18, 47, 3, 12, 29, 17, 44, 78, 99));
        long count = numbers.stream().count();
        System.out.println(count);
    }

    // Get distinct people by id
    public void streamDistinct() {
        Person doeOne = new Person(1, "John", "Doe");
        Person doeTwo = new Person(1, "John", "Doe");
        Person doeThree = new Person(1, "John", "Doe");
        Person brianOne = new Person(2, "Brian", "Clooney");
        Person brianTwo = new Person(2, "Brian", "Clooney");
        Person alex = new Person(3, "Alex", "Kolen");

        Collection<Person> list = Arrays.asList(
                alex,
                brianOne,
                brianTwo,
                doeOne,
                doeTwo,
                doeThree);

        List<Person> distinctElements = list.stream()
                .distinct()
                .collect( Collectors.toList() );

        System.out.println( distinctElements );
    }


    // Find duplicates by grouping
    private <T> Set<T> groupingFindDuplicatesS(Stream<T> stream) {
        // Group the elements along with their frequency in a map
        return stream.collect(Collectors.groupingBy(
                 Function.identity(), Collectors.counting()))
                // Convert this map into a stream
                .entrySet()
                .stream()
                // Check if frequency > 1 for duplicate elements
                .filter(m -> m.getValue() > 1)
                // Find such elements
                .map(Map.Entry::getKey)
                // And Collect them in a Set
                .collect(Collectors.toSet());
    }

    public void streamDuplicates() {
        // Initial stream
        Stream<Integer> stream = Stream.of(5, 13, 4, 21, 13, 27, 2, 59, 59, 34);
        // Print the found duplicate elements
        System.out.println(groupingFindDuplicatesS(stream));
    }

    public void streamFindFirst() {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 1);
        Optional<Integer> first = list
                .stream()
                .filter(x -> x > 1).findFirst();

        if (first.isPresent()) {
            System.out.println(first.get()); // 2
        } else {
            System.out.println("no value?");
        }
    }

    public void streamFindAny() {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 1);
        Optional<Integer> any = list.stream().filter(x -> x > 1).findAny();
        if (any.isPresent()) {
            Integer result = any.get();
            System.out.println(result);
        }
    }

    public void streamForEach() {
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Oliver");
        list.add("Jack");
        list.add("Henry");

        list.stream().forEach(value -> System.out.println(value));
    }

    public void streamMin() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(14, 98, 72, 8, 3, 1, 10, 14, 42, 97, 24));

        int minNumber = numbers.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .get();

        System.out.println(minNumber);
    }

    public void streamMax() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(14, 98, 72, 8, 3, 1, 10, 14, 42, 97, 24));
        int maxNumber = numbers.stream()
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println(maxNumber);
    }

    public void streamReduce() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 5, 7, 9));
        int result = numbers.stream().reduce(1, (num1, num2) -> num1 * num2);
        System.out.println(result);
    }

    public void streamSum() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }

    public void streamAverage() {
        IntStream stream = IntStream.of(1,2, 3, 4, 5, 6, 7, 8);
        OptionalDouble obj = stream.average();
        if (obj.isPresent()) {
            System.out.println(obj.getAsDouble());
        } else {
            System.out.println("-1");
        }
    }
}
