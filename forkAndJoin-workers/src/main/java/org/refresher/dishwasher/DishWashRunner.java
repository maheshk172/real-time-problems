package org.refresher.dishwasher;

public class DishWashRunner implements Runnable {

    final Washer dishWasher;
    final Dish dish;

    public DishWashRunner(Washer dishWasher, Dish dish) {
        this.dishWasher = dishWasher;
        this.dish = dish;
    }

    @Override
    public void run() {
        try {
            this.dishWasher.washDish(dish);
        } catch(InterruptedException exec) {
            exec.printStackTrace();
            System.out.println("Some error occured while cleaning dish : " + this.dish.toString());
        }
    }
}
