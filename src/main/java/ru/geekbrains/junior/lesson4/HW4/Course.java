package ru.geekbrains.junior.lesson4.HW4;


import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name="Courses")
public class Course {

    private static final String[] titles = new String[] {"Информатика","Экономика","Физика","Английский","Математика","История"};
    private static final Random rnd = new Random();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int duration;

    public Course( String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static Course create(){
        Course newC = new Course(titles[rnd.nextInt(titles.length)], rnd.nextInt(6,25));
        System.out.println(newC);
        return newC;
    }

    @Override
    public String toString() {
        return "Course {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
