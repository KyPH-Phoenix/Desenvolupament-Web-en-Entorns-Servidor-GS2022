package com.liceu.maze.service;

import com.liceu.maze.exceptions.AlreadyOpenDoorException;
import com.liceu.maze.exceptions.NotDoorException;
import com.liceu.maze.exceptions.NotValidDirectionException;
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
        Maze maze2 = createMaze2();

        return List.of(maze1, maze2);
    }

    public synchronized MazeGame newGame(int id) {
        MazeGame mazeGame = new MazeGame();
        Maze maze = getMaze(id);

        Player player = new Player();

        try {
            player.setCurrentRoom(maze.getRoom(1));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        mazeGame.setMaze(maze);
        mazeGame.setPlayer(player);

        gameList.add(mazeGame);

        return mazeGame;
    }

    public synchronized void removeGame(MazeGame mazeGame) {
        gameList.remove(mazeGame);
    }

    public synchronized MazeGame resetGame(int mapId, MazeGame mazeGame) {
        removeGame(mazeGame);

        mazeGame = newGame(mapId);

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

        mazeBuilder.setMazeName("Mapa1");
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
        MazeBuilder mazeBuilder = new StandardMazeBuilder();

        mazeBuilder.setMazeName("Mapa2");
        mazeBuilder.setMazeId(2);

        IntStream
                .range(1,18)
                .forEach(mazeBuilder::buildRoom);

        mazeBuilder.buildDoor(1,2, Maze.Directions.NORTH);
        mazeBuilder.buildDoor(4,5, Maze.Directions.EAST);
        mazeBuilder.buildDoor(2,3, Maze.Directions.NORTH);
        mazeBuilder.buildDoor(1,6, Maze.Directions.EAST);
        mazeBuilder.buildDoor(7,8, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(1,10, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(11, 12, Maze.Directions.WEST);
        mazeBuilder.buildDoor(12,13, Maze.Directions.WEST);
        mazeBuilder.buildDoor(1, 14, Maze.Directions.WEST);
        mazeBuilder.buildDoor(14, 15, Maze.Directions.WEST);
        mazeBuilder.buildDoor(15, 16, Maze.Directions.NORTH);
        mazeBuilder.buildDoor(16, 17, Maze.Directions.NORTH);

        Key k1 = new Key("k1", 2);
        Key k2 = new Key("k2", 1);
        Key k3 = new Key("k3", 2);
        Key k4 = new Key("k4", 3);

        mazeBuilder.buildDoor(6,7, Maze.Directions.EAST,k1);
        mazeBuilder.buildDoor(10, 11, Maze.Directions.SOUTH, k2);
        mazeBuilder.buildDoor(8, 9, Maze.Directions.SOUTH, k3);
        mazeBuilder.buildDoor(3, 4, Maze.Directions.EAST, k4);

        mazeBuilder.putItemInRoom(3, k1);
        mazeBuilder.putItemInRoom(8, k2);
        mazeBuilder.putItemInRoom(13, k3);
        mazeBuilder.putItemInRoom(9, k4);

        mazeBuilder.putItemInRoom(2, new Coin());
        mazeBuilder.putItemInRoom(7, new Coin());
        mazeBuilder.putItemInRoom(11, new Coin());
        mazeBuilder.putItemInRoom(12, new Coin());
        mazeBuilder.putItemInRoom(13, new Coin());
        mazeBuilder.putItemInRoom(15, new Coin());
        mazeBuilder.putItemInRoom(16, new Coin());
        mazeBuilder.putItemInRoom(17, new Coin());

        mazeBuilder.setTarget(5);

        return mazeBuilder.getMaze();
    }

    public String getJsonInfo(MazeGame game) {
        JSONObject root = new JSONObject();

        JSONObject player = new JSONObject();
        Room currentRoom = game.getPlayer().getCurrentRoom();
        player.put("currentRoom", currentRoom.getNumber());
        player.put("coins", game.getPlayer().getCoinsCount());
        player.put("keys", game.getPlayer().getKeysCount());

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
        room.put("target", currentRoom.isTarget());

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

        if (direction == null) throw new NotValidDirectionException();

        Player player = mazeGame.getPlayer();
        MapSide mapSide = player.getCurrentRoom().getSide(direction);

        if (mapSide.getClass() == Door.class) {
            Door door = (Door) mapSide;

            if (door.isOpen()) throw new AlreadyOpenDoorException();

            if (playerHasKey(player, door)) {
                door.open();
                mazeGame.setMessage("Has obert la porta.");
            } else {
                mazeGame.setMessage("No tens la clau per obrir aquesta porta");
            }
        } else {
            throw new NotDoorException();
        }

        return mazeGame;
    }

    private boolean playerHasKey(Player player, Door door) {
        List<Key> keyList = player.getKeyList();

        return keyList
                .stream()
                .anyMatch(key -> key.opensDoor(door));
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
