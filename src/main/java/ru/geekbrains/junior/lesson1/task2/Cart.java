package ru.geekbrains.junior.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    private boolean proteins = false;
    private boolean fats = false;
    private boolean carbohydrates = false;


    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    private boolean isProteins() {
        return proteins;
    }

    private boolean isFats() {
        return fats;
    }

    private boolean isCarbohydrates() {
        return carbohydrates;
    }

    private void setProteins(boolean proteins) {
        this.proteins = proteins;
    }

    private void setFats(boolean fats) {
        this.fats = fats;
    }

    private void setCarbohydrates(boolean carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void cardBalancing() {

        if (foodstuffs.isEmpty()) {
            System.out.println("Корзина пуста. Балансировка не проводилась");
            return;
        }

        proteins = !foodstuffs.stream()
                .filter(Food::getProteins)
                .toList().isEmpty();

        fats = !foodstuffs.stream()
                .filter(Food::getFats)
                .toList().isEmpty();

        carbohydrates = !foodstuffs.stream()
                .filter(Food::getCarbohydrates)
                .toList().isEmpty();

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        Collection<T> things = market.getThings(clazz);

        things.stream().filter(x -> !this.isProteins())
                .filter(Food::getProteins)
                .limit(1)
                .forEach(x -> {
                    foodstuffs.add(x);
                    this.setProteins(true);
                });

        things.stream().filter(x -> !this.isFats())
                .filter(Food::getFats)
                .limit(1)
                .forEach(x -> {
                    foodstuffs.add(x);
                    this.setFats(true);
                });

        things.stream().filter(x -> !this.isCarbohydrates())
                .filter(Food::getCarbohydrates)
                .limit(1)
                .forEach(x -> {
                    foodstuffs.add(x);
                    this.setCarbohydrates(true);
                });

        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }
//    public void cardBalancing()
//    {
//        boolean proteins = false;
//        boolean fats = false;
//        boolean carbohydrates = false;
//
//        for (var food : foodstuffs)
//        {
//            if (!proteins && food.getProteins())
//                proteins = true;
//            else
//            if (!fats && food.getFats())
//                fats = true;
//            else
//            if (!carbohydrates && food.getCarbohydrates())
//                carbohydrates = true;
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//        {
//            System.out.println("Корзина уже сбалансирована по БЖУ.");
//            return;
//        }
//
//        for (var thing : market.getThings(clazz))
//        {
//            if (!proteins && thing.getProteins())
//            {
//                proteins = true;
//                foodstuffs.add(thing);
//            }
//            else if (!fats && thing.getFats())
//            {
//                fats = true;
//                foodstuffs.add(thing);
//            }
//            else if (!carbohydrates && thing.getCarbohydrates())
//            {
//                carbohydrates = true;
//                foodstuffs.add(thing);
//            }
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//            System.out.println("Корзина сбалансирована по БЖУ.");
//        else
//            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
//
//    }

    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }


    public void printFoodstuffs() {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
