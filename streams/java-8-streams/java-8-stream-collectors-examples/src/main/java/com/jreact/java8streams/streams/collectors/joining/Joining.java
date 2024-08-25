package com.jreact.java8streams.streams.collectors.joining;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Joining {
    public void streamJoining() {
        List<String> stringList = Arrays.asList("zjc", "streams", "collectors");
        System.out.println("Stream before modification - " + stringList);

        Stream<String> stringStream = stringList.stream();
        // concat the elements of the list using the joining method
        String concatenatedString = stringStream.map(String::toUpperCase).collect(Collectors.joining());

        // result after the concatenation
        System.out.println(concatenatedString);
    }

    public void streamJoiningDelimiter() {
        List<String> stringList = Arrays.asList("zjc", "streams", "collectors");
        System.out.println("Stream before modification - " + stringList);

        Stream<String> stringStream = stringList.stream();
        // delimiter to use
        String delimiter = "-";
        // concat the elements of the list using the joining method
        String concatenatedString = stringStream.map(String::toUpperCase).collect(Collectors.joining(delimiter));

        // result after the concatenation
        System.out.println(concatenatedString);
    }

    public void streamJoiningDelimiterChars() {
        List<String> stringList = Arrays.asList("zjc", "streams", "collectors");
        System.out.println("Stream before modification - " + stringList);

        Stream<String> stringStream = stringList.stream();

        // delimiter to use
        String delimiter = "-";

        // prefix to use
        String prefix = "prefix-";

        // suffix to use
        String suffix = "-suffix";

        // concat the elements of the list using the joining method
        String concatenatedString = stringStream.map(String::toUpperCase).collect(Collectors.joining(delimiter, prefix, suffix));

        // result after the concatenation
        System.out.println(concatenatedString);
    }
}
