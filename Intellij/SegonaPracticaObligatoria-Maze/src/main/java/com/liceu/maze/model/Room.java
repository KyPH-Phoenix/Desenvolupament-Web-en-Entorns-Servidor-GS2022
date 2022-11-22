package com.liceu.maze.model;

import com.liceu.maze.exceptions.NonexistentCoinException;
import com.liceu.maze.exceptions.NonexistentKeyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean haveKey() {
        return this.items
                .stream()
                .anyMatch(item -> item.getClass() == Key.class);
    }

    public boolean haveCoin() {
        return this.items
                .stream()
                .anyMatch(item -> item.getClass() == Coin.class);
    }

    public void getCoin(Player player) {
        Coin coin = (Coin) this.items
                .stream()
                .filter(item -> item.getClass() == Coin.class)
                .findFirst()
                .orElse(null);

        if (coin == null) {
            throw new NonexistentCoinException();
        }

        player.addItem(coin);
        this.items.remove(coin);
    }

    public String getKey(Player player) {
        Key key = (Key) this.items
                .stream()
                .filter(item -> item.getClass() == Key.class)
                .findFirst()
                .orElse(null);

        if (key == null) {
            throw new NonexistentKeyException();
        };

        if (player.getCoinsCount() < key.getPrice()) return String.format("Necesites %d monedes per agafar la clau",
                key.getPrice());

        player.removeCoins(key.getPrice());
        player.addItem(key);
        this.items.remove(key);

        return "Has otingut una clau";
    }
}
