package ru.geekbrains.junior.lesson3.HW3.task1;

import java.io.*;

//Задача 1.
//Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
//        Обеспечьте поддержку сериализации для этого класса.
//        Создайте объект класса Student и инициализируйте его данными.
//        Сериализуйте этот объект в файл.
//        Десериализуйте объект обратно в программу из файла.
//        Выведите все поля объекта, включая GPA, и ответьте на вопрос,
//        почему значение GPA не было сохранено/восстановлено.
public class Program {
    public static final String FILE_BIN = "student.bin";

    public static void main(String[] args) {

        System.out.println("Исходные данные:");
        Student student1 = new Student("Андрей", 40, 4.8d);
        System.out.println(student1);
        System.out.println();

        System.out.println("Сериализация данных:");
        saveToByteFile(student1);
        System.out.println();

        System.out.println("Десериализация данных:");
        Student student2 = loadFromByteFile();

        System.out.println("Результат десереализации из xml:\n" + student2);
        System.out.println("GPA не сохранилось, так как у поля признак transient");


    }

    public static void saveToByteFile(Student student) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_BIN))) {
            out.writeObject(student);
            System.out.println("Объект успешно сериализован");

        } catch (IOException e) {
            throw new RuntimeException("Не удалось сериализовать объект");
        }
    }


    public static Student loadFromByteFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_BIN))) {
            Student student = (Student) in.readObject();
            System.out.println("Объект успешно десериализован");
            return student;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не удалось найти файл для десериализации объекта");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода-вывода");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось найти класс для десериализации объекта");
        }
    }
}
