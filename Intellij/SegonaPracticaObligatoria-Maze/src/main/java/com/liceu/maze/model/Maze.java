package com.liceu.maze.model;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    public enum Directions {
        NORTH, SOUTH, EAST, WEST
    }
    private String name;
    private int id;
    private Map<Integer, Room> rooms = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(int nroom, Room room) {
        this.rooms.put(nroom, room);
    }

    public Room getRoom(int nroom) {
        return this.rooms.get(nroom);
    }
}
