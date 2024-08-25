package com.jreact.java8streams.streams.collectors.partitioningBy;

import org.junit.jupiter.api.Test;

public class PartitioningByTests {

    PartitioningBy partitioningBy = new PartitioningBy();

    @Test public void PartitioningByPredicate() {
        partitioningBy.PartitioningByPredicate();
    }

    @Test
    public void PartitioningByPredicateDownstream() {
        partitioningBy.PartitioningByPredicateDownstream();
    }

    @Test
    public void PartitioningByPredicateCounting() {
        partitioningBy.PartitioningByPredicateCounting();
    }

}
