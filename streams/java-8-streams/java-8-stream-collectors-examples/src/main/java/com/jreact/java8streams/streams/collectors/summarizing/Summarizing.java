package com.jreact.java8streams.streams.collectors.summarizing;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Summarizing {

    public void summarizingDouble() {
        List<Double> doubleList = Arrays.asList(0.002, 23.43, 23.32, 8.76567);
        System.out.println("Contents of the list - " + doubleList);
        Stream<Double> doubleStream = doubleList.stream();
        DoubleSummaryStatistics doubleSummaryStatistics = doubleStream.collect(Collectors.summarizingDouble(e -> e));
        System.out.println("Summary statistics of the stream - " + doubleSummaryStatistics);
    }

    public void summarizingInt() {
        List<Integer> integerList = Arrays.asList(23, 23, 8);
        System.out.println("Contents of the list - " + integerList);
        Stream<Integer> integerStream = integerList.stream();
        IntSummaryStatistics intSummaryStatistics = integerStream.collect(Collectors.summarizingInt(e -> e));
        System.out.println("Summary statistics of the stream - " + intSummaryStatistics);
    }

    public void summarizingLong() {
        List<Long> longList = Arrays.asList(2343L, 2332L, 876567L);
        System.out.println("Contents of the list - " + longList);
        Stream<Long> longStream = longList.stream();
        LongSummaryStatistics longSummaryStatistics = longStream.collect(Collectors.summarizingLong(e -> e));
        System.out.println("Summary statistics of the stream - " + longSummaryStatistics);
    }
}
