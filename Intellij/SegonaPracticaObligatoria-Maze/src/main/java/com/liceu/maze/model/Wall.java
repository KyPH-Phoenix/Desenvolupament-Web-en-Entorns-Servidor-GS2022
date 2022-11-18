package com.liceu.maze.model;

public class Wall implements MapSide {
    @Override
    public String enter(Player player) {
        return "No pots passar a través d'una paret";
    }
}
