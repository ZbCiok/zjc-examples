package com.jreact.java8streams.streams.collectors.summarizing;

import org.junit.jupiter.api.Test;

public class SummarizingTests {

    Summarizing summarizing = new Summarizing();

    @Test
    public void summarizingDouble() {
        summarizing.summarizingDouble();
    }

    @Test
    public void summarizingInt() {
        summarizing.summarizingInt();
    }

    @Test
    public void summarizingLong() {
        summarizing.summarizingLong();
    }
}
