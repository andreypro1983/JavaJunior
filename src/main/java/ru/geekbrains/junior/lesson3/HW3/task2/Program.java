package ru.geekbrains.junior.lesson3.HW3.task2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;

//Задача2.
// Выполнить task1 используя другие типы сериализаторов (в xml и json документы).
public class Program {
    private static final XmlMapper xmlMapper = new XmlMapper();
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    public static final String FILE_JSON = "student.json";
    public static final String FILE_XML = "student.xml";

    public static void main(String[] args) {

        //исходные данные
        System.out.println("Исходные данные:");
        Student student1 = new Student("Андрей", 40, 4.8);
        System.out.println(student1);
        System.out.println();

        // сериализация данных
        System.out.println("Сериализация данных:");
        saveStudentToFile(student1, FILE_XML);
        saveStudentToFile(student1, FILE_JSON);
        System.out.println();

        // десериализация данных
        System.out.println("Десериализация данных:");
        Student studentFromXml = loadStudentFromFile(FILE_XML);
        Student studentFromJson = loadStudentFromFile(FILE_JSON);
        System.out.println("Результат десереализации из xml:\n" + studentFromXml);
        System.out.println("Результат десереализации из json:\n" + studentFromJson);

    }

    public static Student loadStudentFromFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {


            try {
                if (fileName.endsWith(".xml")) {
                    Student studentFromXml = xmlMapper.readValue(file, xmlMapper.constructType(Student.class));
                    System.out.println("Объект успешно десериализован из файла xml формата");
                    return studentFromXml;
                } else if (fileName.endsWith(".json")) {
                    Student studentFromJson = jsonMapper.readValue(file, jsonMapper.constructType(Student.class));
                    System.out.println("Объект успешно десериализован из файла json формата");
                    return studentFromJson;
                } else {
                    System.out.println("Неизвестный формат файла");
                }
            } catch (IOException e) {
                throw new RuntimeException("Не удалось загрузить объект из файла");
            }
        }
        return null;
    }

    public static void saveStudentToFile(Student student, String fileName) {
        try {
            File file = new File(fileName);
            if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(file, student);
                System.out.println("Объект успешно сериализован в файл xml формата");
            } else if (fileName.endsWith(".json")) {
                jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                jsonMapper.writeValue(file, student);
                System.out.println("Объект успешно сериализован в файл json формата");
            } else {
                System.out.println("Неизвестный формат файла");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить объект в файл");
        }

    }

}
