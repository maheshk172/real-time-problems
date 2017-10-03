package org.refresher.dishwasher;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.refresher.dishwasher.DishCreator;
import org.refresher.dishwasher.DishWasherImpl;
import org.refresher.dishwasher.Washer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DishWasherTest {
    Washer washer = new DishWasherImpl();

    @Before
    public void setUp() throws Exception {
    }

    public List<Dish> getUnCleanDishes(String type, int noOfDishes) {
        List<Dish> dishes = new ArrayList<>(noOfDishes);
        for (int i = 0; i < noOfDishes; i++) {
            dishes.add(new Dish(type, i + 1, false));
        }
        return dishes;
    }

    public synchronized  List<Dish>  getUnCleanDishesWithSingleThreadExecutor(String type, int noOfDishes) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return buildDishes(executor, type, noOfDishes);
    }

    public List<Dish> getUnCleanDishesWithCachedThreadPool(String type, int noOfDishes) {
        ExecutorService executor = Executors.newCachedThreadPool();
        return buildDishes(executor, type, noOfDishes);
   }

    public List<Dish> getUnCleanDishesWithFixedThreadPool(String type, int noOfDishes) {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        return buildDishes(executor, type, noOfDishes);
    }

    public List<Dish> buildDishes(ExecutorService executor, String type, int noOfDishes) {
        List<Dish> dishes = new ArrayList<>(noOfDishes);

        for (int i = 0; i < noOfDishes; i++) {
            executor.execute(new DishCreator("plastic", i, dishes));
        }
        executor.shutdown();

        return dishes;
    }






/**
    @Test
    @Ignore
    public void test_whenIWashSomeDishAllMethodsForCleaningAreFollowed() throws InterruptedException {
        Dish dish1 = new Dish("Plastic", 1, false);
        Dish dish2 = new Dish("Plastic", 2, false);

        washer.washDish(dish1);
        washer.washDish(dish2);
    }

    @Test
    @Ignore
    public void test_createThousandsOfDishesWithNormalLoop(){
        List<Dish> dishes = this.getUnCleanDishes("PLASTIC", 1000000);
    }

    @Test
    @Ignore
    public void test_createDishesWithCachedPoolExecutor(){
        //very bad performance
        //List<Dish> dishes = this.getUnCleanDishesWithCachedThreadPool("PLASTIC", 1000000);
    }

    @Test
    public void test_createDishesWithSingleThreadPoolExecutor(){
        List<Dish> dishes = this.getUnCleanDishesWithSingleThreadExecutor("PLASTIC", 1000000);
    }

    @Test
    public void test_createDishesWithFixedThreadPoolExecutor(){
        List<Dish> dishes = this.getUnCleanDishesWithFixedThreadPool("PLASTIC", 1000000);
    }*/

    @Test
    @Ignore
    public void test_createOneThousandThreadsAndcleanThem() {
        List<Dish> dishes = this.getUnCleanDishesWithSingleThreadExecutor("PLASTIC", 100);
        Washer washer = new DishWasherImpl();
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // This just does not work the way I expect,
        // It does not processs all elements provided, have I hit a AntiPattern anywhere
        //dishes.stream().forEach(dish -> executor.execute(new DishWashRunner(washer, dish)));

        //This works fine though
        dishes.stream().forEach(dish -> {
            try {
                washer.washDish(dish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void test_createOneThousandThreadsAndcleanThemUsingParellelStreams() {
        List<Dish> dishes = this.getUnCleanDishesWithSingleThreadExecutor("PLASTIC", 100);
        Washer washer = new DishWasherImpl();
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // Again like normal Stream, it does not work the way I want
        //dishes.parallelStream().forEachOrdered(dish -> executor.execute(new DishWashRunner(washer, dish)));


        dishes.parallelStream().forEachOrdered(dish -> {
            try {
                washer.washDish(dish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }




}
