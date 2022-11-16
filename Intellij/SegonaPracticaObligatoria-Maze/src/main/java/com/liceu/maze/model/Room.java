package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    private int number;
    private List<Item> items = new ArrayList<>();

    private boolean target = false;

    private Map<Maze.Directions, MapSide> sides = new HashMap<>();

    public Room(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public MapSide getSide(Maze.Directions dir) {
        return this.sides.get(dir);
    }

    public void setSide(Maze.Directions dir, MapSide ms) {
        this.sides.put(dir, ms);
    }

    public void enter(Player player) {
        // Primer agafa les monedes
        this.items
                .stream()
                .filter(item -> item.getClass() == Coin.class)
                .collect(Collectors.toList())
                .forEach(item -> {
                    player.addItem(item);
                    System.out.println("Has obtingut un ítem: " + item);
                    this.items.remove(item);
                });

        // Despres agafa les claus, si li basten les monedes
        this.items
                .stream()
                .filter(item -> item.getClass() == Key.class)
                .collect(Collectors.toList())
                .forEach(item -> {
                    Key key = (Key) item;
                    if (player.getCoins() < key.getPrice()) {
                        System.out.printf("No s'ha obtingut la clau %s.\n Monedes necesàries: %d " +
                                "\n Monedes actuals: %d\n", key, key.getPrice(), player.getCoins());
                    } else {
                        System.out.println("Has obtingut un ítem: " + item);
                        player.addItem(item);
                        this.items.remove(item);
                    }
                });
    }
}