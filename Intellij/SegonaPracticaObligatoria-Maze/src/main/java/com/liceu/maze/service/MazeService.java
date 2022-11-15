package com.liceu.maze.service;

import com.liceu.maze.model.*;
import com.liceu.maze.util.MazeBuilder;
import com.liceu.maze.util.StandardMazeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MazeService {
    static List<MazeGame> gameList = new ArrayList<>();

    public List<Maze> getMaps() {
        Maze maze1 = createMaze1();

        return List.of(maze1);
    }

    public synchronized MazeGame newGame() {
        MazeGame mazeGame = new MazeGame();
        Maze maze = createMaze1();

        Player player = new Player();
        player.setCurrentRoom(maze.getRoom(1));

        mazeGame.setMaze(createMaze1());
        mazeGame.setPlayer(new Player());
        gameList.add(mazeGame);

        return mazeGame;
    }

    private static Maze createMaze1() {
        MazeBuilder mazeBuilder = new StandardMazeBuilder();

        mazeBuilder.setMazeName("Maze 1");
        mazeBuilder.setMazeId(1);

        IntStream
                .range(1,7)
                .forEach(mazeBuilder::buildRoom);

        Key k1 = new Key("Level1 Key", 2);
        Key k2 = new Key("Level2 Key", 1);

        mazeBuilder.buildDoor(1,2, Maze.Directions.NORTH);
        mazeBuilder.buildDoor(1,4, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(1,5, Maze.Directions.EAST);

        mazeBuilder.buildDoor(1,3, Maze.Directions.WEST, k2);
        mazeBuilder.buildDoor(5,6, Maze.Directions.EAST, k1);

        mazeBuilder.putItemInRoom(6, k2);
        mazeBuilder.putItemInRoom(2, k1);

        mazeBuilder.putItemInRoom(2, new Coin());
        mazeBuilder.putItemInRoom(4, new Coin());
        mazeBuilder.putItemInRoom(6, new Coin());

        mazeBuilder.setTarget(3);

        return mazeBuilder.getMaze();
    }
}
