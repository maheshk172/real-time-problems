package org.refresher.dishwasher;

@FunctionalInterface
public interface Washer {
    void washDish(Dish dish) throws InterruptedException;
}
