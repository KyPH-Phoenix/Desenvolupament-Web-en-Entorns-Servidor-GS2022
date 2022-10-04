package builder;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Main {

}

class Meal {
    private List<Item> items = new ArrayList<>();

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

abstract class Drink implements Item {
    @Override
    public Pack getPack() {
        return new Wrapper();
    }
}

class Coke extends Drink {
    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public double getPrice() {
        return 1.10;
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
        return 4.10;
    }
}