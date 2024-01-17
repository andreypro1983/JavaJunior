package ru.geekbrains.junior.lesson3.HW3.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    @JsonIgnore
    transient private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }
}
