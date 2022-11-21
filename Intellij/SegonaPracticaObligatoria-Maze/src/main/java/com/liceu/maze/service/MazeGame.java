package com.liceu.maze.service;

import com.liceu.maze.model.Maze;
import com.liceu.maze.model.Player;

public class MazeGame {
    private Maze maze;
    private Player player;
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
