package com.jreact.java8streams.data;

public class Student {
    private String name;
    private String city;
    private double avgGrade;
    private int age;

    public Student(String name, String city, Double avgGrade, int age) {
        this.name = name;
        this.city = city;
        this.avgGrade = avgGrade;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", avgGrade=" + avgGrade +
                ", age=" + age +
                '}';
    }
}
