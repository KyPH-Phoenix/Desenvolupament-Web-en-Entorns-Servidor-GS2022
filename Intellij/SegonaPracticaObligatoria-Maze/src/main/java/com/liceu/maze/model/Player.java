package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> itemList = new ArrayList<>();
    public void setCurrentRoom(Room currentRoom) {
        System.out.println("Ets a l'habitació: " + currentRoom.getNumber());
        this.currentRoom = currentRoom;
    }

    public int getCoins() {
        return (int) this.itemList
                .stream()
                .filter(item -> item.getClass() == Coin.class)
                .count();
    }

    public int getKeys() {
        return (int) this.itemList
                .stream()
                .filter(item -> item.getClass() == Key.class)
                .count();
    }

    public void addItem(Item it) {
        this.itemList.add(it);
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
}
