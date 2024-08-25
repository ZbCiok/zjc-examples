package com.jreact.java8streams.streams.collectors.GroupingBy;

import com.jreact.java8streams.data.Department;
import com.jreact.java8streams.data.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GroupingByClassifierCollector {
    List<Employee> employees = List.of(
            new Employee(1, "Alex", 30, new Department(1, "HR")),
            new Employee(2, "Alex", 36, new Department(1, "HR")),
            new Employee(3, "David", 30, new Department(1, "HR")),
            new Employee(4, "Andrew", 30, new Department(2, "Finance")),
            new Employee(5, "Edward", 31, new Department(2, "Finance")),
            new Employee(6, "Nathan",42, new Department(3, "ADMIN")),
            new Employee(7, "Frank",23 , new Department(3, "ADMIN")),
            new Employee(8, "David", 30, new Department(1, "HR")));

    // counting
    public void groupingByCounting() {
        Map<Employee, Long> nameCount = employees.stream().collect(Collectors
                .groupingBy(string -> string, Collectors.counting()));

        nameCount.forEach((name, count) -> { System.out.println(name + ":" + count); });
    }
}
