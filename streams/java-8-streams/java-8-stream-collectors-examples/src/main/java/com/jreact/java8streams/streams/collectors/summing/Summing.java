package com.jreact.java8streams.streams.collectors.summing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Summing {

    public void summingDouble() {
        List<Double> numbers = Arrays.asList(1.01, 2.0, 3.056, 5.5, 10.3, 40.23, 21.178);
        Double sum = numbers.stream().collect(Collectors.summingDouble(Double::doubleValue));
        System.out.println(sum);
    }

    public void summingInt() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer sum = numbers.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum: " + sum);
    }

    public void summingLong() {
        List<Long> numbers = Arrays.asList(2L, 1156L, 163L, 497L, 7L);
        Long sum = numbers.stream().collect(Collectors.summingLong(Long::longValue));
        System.out.println(sum);
    }

}
