package com.jreact.java8streams.create;

import org.junit.jupiter.api.Test;

public class CreatingStreamsTests {

    CreatingStreams creatingStreams = new CreatingStreams();

    @Test
    public void streamEmpty() {
        creatingStreams.streamEmpty();
    }

    @Test
    public void streamOf() {
        creatingStreams.streamOf();
    }

    @Test
    public void streamOfArray() {
        creatingStreams.streamOfArray();
    }

    @Test
    public void listStream() {
        creatingStreams.listStream();
    }

    @Test
    public void streamBuilder() {
        creatingStreams.streamBuilder();
    }

    @Test
    public void streamGenerate() {
        creatingStreams.streamGenerate();
    }


    @Test
    public void streamIterate() {
        creatingStreams.streamIterate();
    }

    @Test
    public void StreamOfStringChars() {
        creatingStreams.StreamOfStringChars();
    }
}
