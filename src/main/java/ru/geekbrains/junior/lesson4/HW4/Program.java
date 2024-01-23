package ru.geekbrains.junior.lesson4.HW4;

//Создайте базу данных (например, CourcesDB).
//        В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
//        Настройте Hibernate для работы с вашей базой данных.
//        Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
//        Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
//        Убедитесь, что каждая операция выполняется в отдельной транзакции.

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Program {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Course newCourse = Course.create();
        addData(sessionFactory, newCourse);
        Course gettingCourse = getData(sessionFactory, newCourse);
        System.out.println("Получены данные: " + gettingCourse);
        updateData(sessionFactory, gettingCourse);
        Course updatedCourse = getData(sessionFactory, gettingCourse);
        System.out.println("Обновленные данные: " + updatedCourse);

        Course newCourse1 = Course.create();
        addData(sessionFactory, newCourse1);
        deleteData(sessionFactory, newCourse1);
    }

    /***
     * Добавление данных в бд
     * @param sessionFactory Объект класса SessionFactory
     * @param course Объект класса Cource
     */
    private static void addData(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.save(course);
            t.commit();
            System.out.println("Данные успешно добавлены в бд");
        }
    }

    /***
     * Чтение данных из бд
     * @param sessionFactory Объект класса SessionFactory
     * @param course Объект класса Cource
     */
    private static Course getData(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Course getCourse = session.get(Course.class, course.getId());
            System.out.println(getCourse);
            t.commit();
            System.out.println("Данные успешно получены из бд");
            return getCourse;
        }
    }

    /***
     * Обновление данных в бд
     * @param sessionFactory Объект класса SessionFactory
     * @param course Объект класса Cource
     */
    private static void updateData(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            course.setDuration(course.getDuration() - 5);
            session.update(course);
            t.commit();
            System.out.println("Данные успешно обновлены в бд");
        }
    }

    /***
     * Удаление данных из бд
     * @param sessionFactory Объект класса SessionFactory
     * @param course Объект класса Cource
     */
    private static void deleteData(SessionFactory sessionFactory, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.delete(course);
            t.commit();
            System.out.println("Данные успешно удалены в бд");
        }
    }


}
