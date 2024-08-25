package com.jreact.java8streams.streams.collectors.GroupingBy;

import com.jreact.java8streams.data.Department;
import com.jreact.java8streams.data.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// These examples use groupingBy(classifier) method.
public class GroupingByClassifier {
    List<Employee> employees = List.of(
            new Employee(1, "Alex", 30, new Department(1, "HR")),
            new Employee(2, "Alex", 36, new Department(1, "HR")),
            new Employee(3, "David", 30, new Department(1, "HR")),
            new Employee(4, "Andrew", 30, new Department(2, "Finance")),
            new Employee(5, "Edward", 31, new Department(2, "Finance")),
            new Employee(6, "Nathan",42, new Department(3, "ADMIN")),
            new Employee(7, "Frank",23 , new Department(3, "ADMIN")));


    // Grouping all employees by age
    public void groupingByAge() {
        Map<Integer, List<Employee>> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge));

        System.out.println(result);
    }

    // Grouping all employees by department
    public void groupingByDepartment() {
        Map<Department, List<Employee>> result = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(result);
    }

    public void groupingByEmployeeLength() {
        Map<String, List<Employee>> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getName));

        result.forEach((k, v) -> System.out.println("key.length = " + k.length() + ", key = " + k));
    }

    public void groupingByName() {
        Map<String, List<Employee>> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getName));

        System.out.println(result);
    }
}

