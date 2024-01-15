package ru.geekbrains.junior.lesson2.HW2.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//Задача 1:
//        Создайте абстрактный класс "Animal" с полями "name" и "age".
//        Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
//        Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
//        Выведите на экран информацию о каждом объекте.
//        Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
public class Program {
    public static void main(String[] args) {

        Animal[] animals = generateArrayAnimals();
        for (int i = 0; i < animals.length; i++) {
            try {
                System.out.println("Animal_№" + (i + 1) + ":");

                // Выведение на экран информацию о каждом объекте.
                printAnimalInfo(animals[i]);

                System.out.println("Результат вызова метода makeSound на объекте:");
                // Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует
                execMakeSound(animals[i]);
                System.out.println();
            }catch (NoSuchMethodException e){
                System.out.println("Вызываемый метод не обнаружен");
                System.out.println();
            }catch (Exception e) {
                System.out.println("Что то пошло не так");
            }
        }
    }

    static Animal[] generateArrayAnimals() {
        int count = 5;
        Animal[] animals = new Animal[count];
        animals[0] = new Cat("Маруся", 3, "зеленые", "Василий");
        animals[1] = new Dog("Трезор", 1, "колли", 30);
        animals[2] = new Dog("Бесси", 7, "пудель", 10);
        animals[3] = new Cat("Честер", 2, "серые", "Максим");
        animals[4] = new Cat("Симба", 10, "мейн-кун", "Станислав");
        return animals;
    }

    static void printAnimalInfo(Object obj) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> clazz = obj.getClass();
        if (clazz.getSimpleName().equals("Cat")) {
            Method method = clazz.getMethod("catInfo");
            System.out.println(method.invoke(obj));
        } else if (clazz.getSimpleName().equals("Dog")) {
            Method method = clazz.getMethod("dogInfo");
            System.out.println(method.invoke(obj));
        } else {
            System.out.println("Неизвестное животное");
        }
    }

    static void execMakeSound(Object obj) throws
            InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = obj.getClass();
        Method makeSound = clazz.getDeclaredMethod("makeSound");
        makeSound.setAccessible(true);
        makeSound.invoke(obj);
    }

}
