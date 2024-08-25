package com.jreact.java8streams.streams.collectors.summing;

import org.junit.jupiter.api.Test;

public class SummingTests {

    Summing summing = new Summing();

    @Test
    public void summingDouble() {
        summing.summingDouble();
    }

    @Test
    public void summingInt() {
        summing.summingInt();
    }

    @Test
    public void summingLong() {
        summing.summingLong();
    }

}
