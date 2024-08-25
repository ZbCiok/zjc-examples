package com.jreact.java8streams.data;

import java.util.Objects;

public class Department {
    int id;
    String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "Object";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return Objects.equals(name, department.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
