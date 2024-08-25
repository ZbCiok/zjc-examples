package com.jreact.java8streams.streams.collectors.GroupingBy;

// https://howtodoinjava.com/java/stream/collectors-groupingby/

import java.util.List;
import java.util.Map;

import com.jreact.java8streams.data.Rec;

import static java.util.stream.Collectors.*;

public class GroupingByClassifierSupplierCollector {


    List<Rec.Person> persons = List.of(
            new Rec.Person(1, "Alex", 100d, new Rec.Department(1, "HR")),
            new Rec.Person(2, "Brian", 200d, new Rec.Department(1, "HR")),
            new Rec.Person(3, "Charles", 900d, new Rec.Department(2, "Finance")),
            new Rec.Person(4, "David", 200d, new Rec.Department(2, "Finance")),
            new Rec.Person(5, "Edward", 200d, new Rec.Department(2, "Finance")),
            new Rec.Person(6, "Frank", 800d, new Rec.Department(3, "ADMIN")),
            new Rec.Person(7, "George", 900d, new Rec.Department(3, "ADMIN")));

    // Filtering all persons with salary less than 300
    public void groupingWithFiltering() {
        Map<Rec.Department, Long> map = persons.stream()
                .collect(groupingBy(Rec.Person::department, filtering(p -> p.salary() > 300d, counting())));

        System.out.println(map);
    }
}
