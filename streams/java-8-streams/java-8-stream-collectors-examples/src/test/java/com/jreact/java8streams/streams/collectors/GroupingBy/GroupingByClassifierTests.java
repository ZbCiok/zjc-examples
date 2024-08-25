package com.jreact.java8streams.streams.collectors.GroupingBy;

import org.junit.jupiter.api.Test;
public class GroupingByClassifierTests
{

    GroupingByClassifier groupingByClassifier = new GroupingByClassifier();

    @Test
    //Grouping all employees by age
    public void groupByAge() {
        groupingByClassifier.groupingByAge();
    }

    @Test
    // Grouping all employees by department
    public void groupByDepartment() {
        groupingByClassifier.groupingByDepartment();
    }

    @Test
    public void groupByEmployeeLength() {
        groupingByClassifier.groupingByEmployeeLength();
    }

    @Test
    public void groupByName() {
        groupingByClassifier.groupingByName();
    }
}
