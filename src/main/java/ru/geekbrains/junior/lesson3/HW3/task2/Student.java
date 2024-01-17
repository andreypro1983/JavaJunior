package ru.geekbrains.junior.lesson3.HW3.task2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.*;

public class Student implements Externalizable {
    private String name;
    private int age;
    @JsonIgnore
    transient private double gpa;

    public Student() {
    }

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeDouble(gpa);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        gpa = in.readDouble();
    }
}
