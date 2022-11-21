package com.liceu.maze.model;

public class Door implements MapSide {
    private Room r1, r2;
    private boolean open = false;

    public Door(Room r1, Room r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    public String open() {
        if (this.isOpen()) {
            return "La porta ja està oberta";
        }

        this.open = true;
        return "Has obert la porta";
    }

    public boolean isOpen() {
        return this.open;
    }

    @Override
    public String enter(Player player) {
         //COdigo original

        if (this.open) {
            Room r = getOtherRoom(player.getCurrentRoom());
            player.setCurrentRoom(r);
            return "";
        } else {
            return "La porta està tancada";
        }
    }

    private Room getOtherRoom(Room currentRoom) {
        if (r1.getNumber() == currentRoom.getNumber()) {
            return r2;
        }
        return r1;
    }
}
