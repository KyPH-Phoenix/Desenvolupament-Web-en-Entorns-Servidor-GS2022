package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Key implements Item {
    private List<Door> doors = new ArrayList<>();
    private String name;
    private int price;

    public Key(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void addDoor(Door door) {
        this.doors.add(door);
    }

    public boolean opensDoor(Door door) {
        return doors
                .stream()
                .anyMatch(door1 -> door1 == door);
    }

    @Override
    public String toString() {
        return "Key{" +
                "name='" + name + '\'' +
                '}';
    }
}







