package com.jreact.java8streams.streams.collectors.joining;

import org.junit.jupiter.api.Test;

public class JoiningTests {

    Joining joining = new Joining();
    @Test
    public void streamJoining() {
        joining.streamJoining();
    }

    @Test
    public void streamJoiningDelimiter() {
        joining.streamJoiningDelimiter();
    }

    @Test
    public void streamJoiningDelimiterChars() {
        joining.streamJoiningDelimiterChars();
    }
}
