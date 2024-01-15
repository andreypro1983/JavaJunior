package ru.geekbrains.junior.lesson2.HW2.task1;

public class Dog extends Animal{

    private String breed;
    private int maxSpeed;

    public Dog(String name, int age, String breed, int maxSpeed) {
        super(name, age);
        this.breed = breed;
        this.maxSpeed = maxSpeed;
    }

    private void makeSound(){
        System.out.println("Гав гав гав");
    }

    public void run(){
        System.out.println(getName() + " побежала");
    }

    public String dogInfo() {
        return "Собака: " + getName() + " возраст: " + getAge() + " порода: " + breed + " максимальная скорость: " + maxSpeed + " км/ч";
    }


}
