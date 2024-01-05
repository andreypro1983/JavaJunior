package ru.geekbrains.junior.lesson1.HW1;

import java.util.Arrays;
import java.util.List;

//Напишите программу, которая использует Stream API для обработки списка чисел.
// Программа должна вывести на экран среднее значение всех четных чисел в списке.
public class Task1 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(14, 2, 8, 3, 7, 15);
        System.out.println(averageEvenNumbers(list));
    }

    /***
     * среднее значение всех четных чисел в списке
     * @param list исходных список чисел
     * @return double
     */
    static double averageEvenNumbers(List<Integer> list) {
        return list.stream().filter(x -> x % 2 == 0)
                .mapToDouble(x -> x).average().orElse(0);
    }
}
