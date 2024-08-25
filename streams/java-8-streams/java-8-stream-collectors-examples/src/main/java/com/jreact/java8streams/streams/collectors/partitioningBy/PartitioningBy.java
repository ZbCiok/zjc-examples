package com.jreact.java8streams.streams.collectors.partitioningBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {

    public void PartitioningByPredicate() {
        List<String> names = Arrays.asList("zjc", "stream", "collector", "map", "mapping");
        Map<Boolean, List<String>> partitionByNameLength = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 3));

        System.out.println(partitionByNameLength);
    }

    public void PartitioningByPredicateDownstream() {
        List<String> names = Arrays.asList("zjc", "stream", "collector", "map", "mapping");
        Map<Boolean, List<String>> partitionByNameLength = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 3,
                        Collectors.mapping(String::toUpperCase, Collectors.toList())));

        System.out.println(partitionByNameLength);
    }

    public void PartitioningByPredicateCounting() {
        List<Double> dbs = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        Map<Boolean, Long> partitionByAvgGrade = dbs.stream()
                .collect(Collectors.partitioningBy(db-> db > 3.0,
                        Collectors.counting()));

        System.out.println(partitionByAvgGrade);
    }
}
