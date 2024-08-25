package com.jreact.java8streams;

import org.junit.jupiter.api.Test;

public class StreamIntermediateOperationsTests {
    StreamIntermediateOperations streamIntermediateOperations = new StreamIntermediateOperations();

    @Test
    public void concatStringStreams() {
        streamIntermediateOperations.concatStringStreams();
    }

    @Test
    public void concatLongStreams() {
        streamIntermediateOperations.concatLongStreams();
    }

    @Test
    public void concatCollectionsStreams() {
        streamIntermediateOperations.concatCollectionsStreams();
    }

    @Test
    public void streamFilter() {
        streamIntermediateOperations.streamFilter();
    }

    @Test
    public void streamMap() {
        streamIntermediateOperations.streamMap();
    }

    @Test
    public void streamFlatMap() {
        streamIntermediateOperations.streamFlatMap();
    }

    @Test
    public void streamDistinct() {
        streamIntermediateOperations.streamDistinct();
    }

    @Test
    public void streamLimit() {
        streamIntermediateOperations.streamLimit();
    }

    // There will be no output.
    @Test
    public void streamPeek01() {
        streamIntermediateOperations.streamPeek01();
    }

    @Test
    public void streamPeek02() {
        streamIntermediateOperations.streamPeek02();
    }

    @Test
    public void streamPeek03() {
        streamIntermediateOperations.streamPeek03();
    }

    @Test
    public void streamSkip() {
        streamIntermediateOperations.streamSkip();
    }

    @Test
    public void streamSorted() {
        streamIntermediateOperations.streamSorted();
    }
}
