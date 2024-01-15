package ru.geekbrains.junior.lesson2.HW2.task1;

public class Cat extends Animal{
    private String eyeColor;
    private String owner;

    public Cat(String name, int age, String eyeColor, String owner) {
        super(name, age);
        this.eyeColor = eyeColor;
        this.owner = owner;
    }

    public void play(){
        System.out.println(getName() + " начала играть с "+ owner );
    }

    private void makeSound(){
        System.out.println("Мяу мяу");
    }


    public String catInfo() {
        return "Кошка: " + getName() + " возраст: " + getAge() + " цвет глаз: " + eyeColor + " владелец: " + owner;
    }
}
