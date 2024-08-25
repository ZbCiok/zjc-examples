package com.jreact.java8streams.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingStreams {

    public void streamEmpty() {
        Stream<String> stream = Stream.empty();
        stream.forEach(System.out::println);
    }

    public void streamOf() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
        stream.forEach(System.out::println);
    }

    public void streamOfArray() {
        Stream<Integer> stream = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
        stream.forEach(System.out::println);
    }

    public void listStream() {
        List<Integer> list = new ArrayList<Integer>();

        for(int i = 1; i< 10; i++){
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);
    }

    public void streamBuilder() {
        Stream.Builder<String> builder = Stream.builder();

        Stream<String> stream = builder.add("Aaaa")
                .add("bbbb")
                .add("Cccc")
                .add("DcDd")
                .build();

        stream.forEach(System.out::println);
    }

    public void streamGenerate() {
        Stream<Integer> randomNumbers = Stream
                .generate(() -> (new Random()).nextInt(100));

        randomNumbers.limit(20).forEach(System.out::println);
    }

    public void streamIterate() {
        // create a stream using iterate
        Stream<Integer> stream
                = Stream.iterate(1,
                i -> i <= 20, i -> i * 2);

        // print Values
        stream.forEach(System.out::println);
    }

    public void StreamOfStringChars() {
        IntStream stream = "12345_abcdefg".chars();
        stream.forEach(System.out::println);
    }
}
