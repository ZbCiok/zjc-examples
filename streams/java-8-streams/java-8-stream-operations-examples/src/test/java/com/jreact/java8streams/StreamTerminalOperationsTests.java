package com.jreact.java8streams;

import org.junit.jupiter.api.Test;

public class StreamTerminalOperationsTests {

    StreamTerminalOperations streamTerminalOperations = new StreamTerminalOperations();

    @Test public void streamAnyMatch() {
        streamTerminalOperations.streamAnyMatch();
    }

    @Test
    public void streamAllMatch() {
        streamTerminalOperations.streamAnyMatch();
    }

    @Test
    public void streamNoneMatch() {
        streamTerminalOperations.streamNoneMatch();
    }

    @Test
    public void collectToMap01() {
        streamTerminalOperations.collectToMap01();
    }

// UNCOMMENT TO TEST. IllegalStateException - duplicate keys
//    @Test
//    public void collectToMap02() {
//        streamTerminalOperations.collectToMap02();
//    }

    @Test
    public void collectToMap03() {
        streamTerminalOperations.collectToMap03();
    }

    @Test
    public void collectToMap04() {
        streamTerminalOperations.collectToMap04();
    }

    @Test
    public void streamCount() {
        streamTerminalOperations.streamCount();
    }

    @Test
    // Get distinct people by id
    public void streamDistinct() {
        streamTerminalOperations.streamDistinct();
    }

    // Find duplicates by grouping
    @Test
    public void streamDuplicates() {
        streamTerminalOperations.streamDuplicates();
    }

    @Test
    public void streamFindFirst() {
        streamTerminalOperations.streamFindFirst();
    }

    @Test
    public void streamFindAny() {
        streamTerminalOperations.streamFindAny();
    }

    @Test
    public void streamForEach() {
        streamTerminalOperations.streamForEach();
    }

    @Test
    public void streamMin() {
        streamTerminalOperations.streamMin();
    }

    @Test
    public void streamMax() {
        streamTerminalOperations.streamMax();
    }

    @Test
    public void streamSum() {
        streamTerminalOperations.streamSum();
    }

    @Test
    public void streamAverage() {
        streamTerminalOperations.streamAverage();
    }
}
