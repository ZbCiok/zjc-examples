package com.jreact.java8streams.streams.collectors.GroupingBy;

import org.junit.jupiter.api.Test;

public class GroupingByClassifierCollectorTests {

    GroupingByClassifierCollector groupingByClassifierCollector = new GroupingByClassifierCollector();

    @Test
    public void groupingByCounting() {
        groupingByClassifierCollector.groupingByCounting();
    }
}
