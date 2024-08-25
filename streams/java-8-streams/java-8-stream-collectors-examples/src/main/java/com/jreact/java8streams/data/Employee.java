package com.jreact.java8streams.data;

import java.util.Objects;

public class Employee {
    int id;
    String name;
    int age;
    Department department;

    public Employee(int id, String name, int age, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return name + "Object";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
