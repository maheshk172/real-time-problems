package org.refresher.dishwasher;


public interface DishWasher extends Washer {

    default public void washDish(Dish dish) throws InterruptedException {
        dish = this.applySoap(dish);
        dish = this.washWithHotWater(dish);
        dish = this.washWithColdWater(dish);
        dish = this.dryDish(dish);
        dish.setClean(true);
        System.out.println("Cleaned Dish with : " + dish.toString());
    }

    Dish applySoap(Dish dish);
    Dish washWithHotWater(Dish dish);
    Dish washWithColdWater(Dish dish);
    Dish dryDish(Dish dish);
}
