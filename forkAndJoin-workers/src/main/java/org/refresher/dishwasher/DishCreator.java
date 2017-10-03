package org.refresher.dishwasher;

import java.util.List;

public class DishCreator implements Runnable {
    final List<Dish> dishes;
    final int number;
    final String type;

    DishCreator(String type, int number, List<Dish> dishes) {
        this.dishes = dishes;
        this.number = number;
        this.type = type;
    }

    @Override
    public void run() {
        dishes.add(new Dish(type, number, false));
    }
}

