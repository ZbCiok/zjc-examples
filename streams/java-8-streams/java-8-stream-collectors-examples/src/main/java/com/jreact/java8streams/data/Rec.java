package com.jreact.java8streams.data;

import java.util.List;

public class Rec {
    public record Person(int id, String name, double salary, Department department) {
    }

    public record Department(int id, String name) {
    }
}
