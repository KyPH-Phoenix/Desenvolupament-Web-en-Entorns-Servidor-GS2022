package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private Room currentRoom;
    private List<Item> itemList = new ArrayList<>();
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCoinsCount() {
        return (int) this.itemList
                .stream()
                .filter(item -> item.getClass() == Coin.class)
                .count();
    }

    public int getKeysCount() {
        return (int) this.itemList
                .stream()
                .filter(item -> item.getClass() == Key.class)
                .count();
    }

    public List<Key> getKeyList() {
        List<Key> result = new ArrayList<>();

        this.itemList
                .stream()
                .filter(item -> item.getClass() == Key.class)
                .forEach(item -> result.add((Key) item));

        return result;
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

    public void removeCoins(int price) {
        int count = 0;

        Iterator<Item> iterator = this.itemList.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getClass() == Coin.class && count < price) {
                count++;
                iterator.remove();
            }
        }
    }
}
