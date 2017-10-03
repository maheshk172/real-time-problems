package org.refresher.dishwasher;

public class Dish {
    final String typeOfdish;
    final int number;
    private boolean clean;

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }


    public Dish(String typeOfdish, int number, boolean isDishClean) {
        this.typeOfdish = typeOfdish;
        this.number = number;
        this.clean = isDishClean;
        System.out.println("Dish created : " + this.toString());
    }

    @Override
    public String toString() {
        return "Dish{" +
                "typeOfdish='" + typeOfdish + '\'' +
                ", number=" + number +
                ", clean=" + clean +
                '}';
    }
}
