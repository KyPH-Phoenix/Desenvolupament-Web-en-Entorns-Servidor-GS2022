package com.liceu.maze.service;

import com.liceu.maze.model.*;
import com.liceu.maze.util.MazeBuilder;
import com.liceu.maze.util.StandardMazeBuilder;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MazeService {
    static List<MazeGame> gameList = new ArrayList<>();

    public List<Maze> getMaps() {
        Maze maze1 = createMaze1();

        return List.of(maze1);
    }

    public synchronized MazeGame newGame(int id) throws IOException {
        MazeGame mazeGame = new MazeGame();
        Maze maze = getMaze(id);

        Player player = new Player();

        try {
            player.setCurrentRoom(maze.getRoom(1));
        } catch (NullPointerException e) {
            throw new IOException(e);
        }

        mazeGame.setMaze(maze);
        mazeGame.setPlayer(player);

        gameList.add(mazeGame);

        return mazeGame;
    }

    private Maze getMaze(int id) {
        switch (id) {
            case 1:
                return createMaze1();
            case 2:
                return createMaze2();
            default:
                return null;
        }
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

    private static Maze createMaze2() {
        return new Maze();
    }

    public String getJsonInfo(MazeGame game) {
        JSONObject root = new JSONObject();

        JSONObject player = new JSONObject();
        Room currentRoom = game.getPlayer().getCurrentRoom();
        player.put("currentRoom", currentRoom.getNumber());
        player.put("coins", game.getPlayer().getCoins());
        player.put("keys", game.getPlayer().getKeys());

        JSONObject room = new JSONObject();

        JSONObject walls = new JSONObject();
        MapSide n = currentRoom.getSide(Maze.Directions.NORTH);
        MapSide s = currentRoom.getSide(Maze.Directions.SOUTH);
        MapSide e = currentRoom.getSide(Maze.Directions.EAST);
        MapSide w = currentRoom.getSide(Maze.Directions.WEST);

        walls.put("n", checkSide(n));
        walls.put("s", checkSide(s));
        walls.put("e", checkSide(e));
        walls.put("w", checkSide(w));

        room.put("walls", walls);
        room.put("coin", currentRoom.haveCoin());
        room.put("key", currentRoom.haveKey());

        root.put("player", player);
        root.put("room", room);
        root.put("message", game.getMessage());

        game.setMessage("");

        return root.toJSONString();
    }

    private Object checkSide(MapSide side) {
        if (side.getClass() == Wall.class) {
            JSONObject wall = new JSONObject();
            wall.put("type", "wall");
            return wall;
        }

        JSONObject door = new JSONObject();
        door.put("type", "door");
        door.put("open", ((Door) side).isOpen());

        return door;
    }

    public MazeGame moveTo(String dir, MazeGame mazeGame) {
        Maze.Directions direction = getDir(dir);

        if (direction == null) return mazeGame;

        Player player = mazeGame.getPlayer();
        MapSide mapSide = player.getCurrentRoom().getSide(direction);

        String message = mapSide.enter(player);
        mazeGame.setMessage(message);

        return mazeGame;
    }

    public MazeGame getCoin(MazeGame mazeGame) {
        Player player = mazeGame.getPlayer();
        Room room = player.getCurrentRoom();

        room.getCoin(player);
        mazeGame.setMessage("Has obtingut una moneda");

        return mazeGame;
    }

    public MazeGame getKey(MazeGame mazeGame) {
        Player player = mazeGame.getPlayer();
        Room room = player.getCurrentRoom();

        String message = room.getKey(player);

        mazeGame.setMessage(message);

        return mazeGame;
    }

    public MazeGame openDoor(String dir, MazeGame mazeGame) {
        Maze.Directions direction = getDir(dir);

        if (direction == null) return mazeGame;

        Player player = mazeGame.getPlayer();
        MapSide mapSide = player.getCurrentRoom().getSide(direction);

        String message;

        if (mapSide.getClass() == Door.class) {
            Door door = (Door) mapSide;
            message = door.open();
            mazeGame.setMessage(message);
        } else {
            message = "No pots obrir una paret. Deixa d'intentar rompre el programa";
        }

        mazeGame.setMessage(message);

        return mazeGame;
    }

    private Maze.Directions getDir(String dir) {
        if (dir.equals("N")) {
            return Maze.Directions.NORTH;
        } else if (dir.equals("S")) {
            return Maze.Directions.SOUTH;
        } else if (dir.equals("E")) {
            return Maze.Directions.EAST;
        } else if (dir.equals("W")) {
            return Maze.Directions.WEST;
        } else {
            return null;
        }
    }
}
