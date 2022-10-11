package designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal m1 = mealBuilder.prepareChickeMenu();
        Meal m2 = mealBuilder.prepareVegMenu();
    }
}

class MealBuilder {
    Meal prepareVegMenu() {
        Meal m = new Meal();
        m.addItem(new VegBurger());
        m.addItem(new Pepsi());
        return m;
    }

    Meal prepareChickeMenu() {
        Meal m = new Meal();
        m.addItem(new ChickenBurger());
        m.addItem(new Coke());
        return m;
    }
}

class Meal {
    private List<Item> items = new ArrayList<>();

    void addItem(Item item) {
        items.add(item);
    }

    double getCost() {
        return items
                .stream()
                .map((i) -> i.getPrice())
                .reduce((a,b) -> b + a).get();
    }
}

interface Item {
    String getName();
    double getPrice();
    Pack getPack();
}

interface Pack {
    String pack();
}

class Wrapper implements Pack {

    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Pack {

    @Override
    public String pack() {
        return "Bottle";
    }
}

abstract class Burger implements Item {
    @Override
    public Pack getPack() {
        return new Wrapper();
    }
}

class ChickenBurger extends Burger {

    @Override
    public String getName() {
        return "Chicken Burger";
    }

    @Override
    public double getPrice() {
        return 3.55;
    }
}

class VegBurger extends Burger {

    @Override
    public String getName() {
        return "Vegan Burger";
    }

    @Override
    public double getPrice() {
        return 5.55;
    }
}

abstract class Drink implements Item {
    @Override
    public Pack getPack() {
        return new Bottle();
    }
}

class Pepsi extends Drink {

    @Override
    public String getName() {
        return "Pepsi";
    }

    @Override
    public double getPrice() {
        return 1.20;
    }
}

class Coke extends Drink {

    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
}