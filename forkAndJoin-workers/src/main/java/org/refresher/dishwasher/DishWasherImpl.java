package org.refresher.dishwasher;


public class DishWasherImpl implements DishWasher {
    public final Dish applySoap(Dish dish) {
        System.out.println("Applying Soap to dish No: " + dish.number);
        return dish;
    }


    public final Dish washWithHotWater(Dish dish) {
        System.out.println("Washing with Hot water dish No: " + dish.number);
        return dish;
    }


    public final Dish washWithColdWater(Dish dish) {
        System.out.println("Washing with Cold water dish No: " + dish.number);
        return dish;
    }

    public final Dish dryDish(Dish dish) {
        System.out.println("Drying with hot air dish No: " + dish.number);
        return dish;
    }
}
