package builder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = Pedido.pedirMenuVegano(new Cocacola());

        System.out.println(menu);
    }
}

class Pedido {
    public static Menu pedirMenuVegano(Bebida bebida) {
        MenuBuilder menuBuilder = new MenuBuilder();

        menuBuilder.setBebida(bebida);
        menuBuilder.setBurger(new HamburguesaVegana());
        menuBuilder.setNombre("Menu Vegano");

        return menuBuilder.getResult();
    }
}

class MenuBuilder {
    private String nombre;
    private Hamburguesa burger;
    private Bebida bebida;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBurger(Hamburguesa burger) {
        this.burger = burger;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Menu getResult() {
        Menu menu = new Menu();

        menu.addComponente(this.burger);
        menu.addComponente(this.bebida);
        menu.setNombreMenu(this.nombre);

        return menu;
    }
}

class Menu {
    private String nombreMenu;
    private List<Item> componentes = new ArrayList<>();

    public void addComponente(Item item) {
        this.componentes.add(item);
    }

    public List<Item> getComponentes() {
        return componentes;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public double getTotal() {
        double resultado = 0;

        for (Item item : this.componentes) {
            resultado += item.getPrecio();
        }

        return resultado;
    }

    @Override
    public String toString() {
        String resultado = this.nombreMenu + "\nComponentes:";
        for (Item item : this.componentes) resultado += "\n    " + item;
        resultado += String.format("\n    Total: €%.2f", this.getTotal());
        return resultado;
    }
}

interface Envoltorio {
    String getNombre();
}

class Caja implements Envoltorio {

    @Override
    public String getNombre() {
        return "Caja";
    }
}

class Vaso implements Envoltorio {

    @Override
    public String getNombre() {
        return "Vaso";
    }
}

interface Item {
    String getNombre();
    double getPrecio();
    Envoltorio getEnvoltorio();
}

abstract class Hamburguesa implements Item {

    @Override
    public Envoltorio getEnvoltorio() {
        return new Caja();
    }

    @Override
    public String toString() {
        return String.format("%s empaquetada en %s. Precio €%.2f", this.getNombre(),
                this.getEnvoltorio().getNombre(), this.getPrecio());
    }
}

class HamburguesaVegana extends Hamburguesa {

    @Override
    public String getNombre() {
        return "Hamburguesa Vegana";
    }

    @Override
    public double getPrecio() {
        return 4.10;
    }
}

class HamburguesaPollo extends Hamburguesa {

    @Override
    public String getNombre() {
        return "Hamburguesa de Pollo";
    }

    @Override
    public double getPrecio() {
        return 3.55;
    }
}

abstract class Bebida implements Item {

    @Override
    public Envoltorio getEnvoltorio() {
        return new Vaso();
    }

    @Override
    public String toString() {
        return String.format("%s servida en %s. Precio €%.2f", this.getNombre(),
                this.getEnvoltorio().getNombre(), this.getPrecio());
    }
}

class Cocacola extends Bebida {

    @Override
    public String getNombre() {
        return "Cocacola";
    }

    @Override
    public double getPrecio() {
        return 1.50;
    }
}

class Fanta extends Bebida {

    @Override
    public String getNombre() {
        return "Fanta";
    }

    @Override
    public double getPrecio() {
        return 1.20;
    }
}