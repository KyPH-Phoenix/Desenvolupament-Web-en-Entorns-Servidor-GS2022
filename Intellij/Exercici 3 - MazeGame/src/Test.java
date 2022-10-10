public class Test {
    public static void main(String[] args) {
        Maze maze = new Maze();

        for (int i = 0; i < 7; i++) {
            maze.addRoom(new Room(i + 1));
        }

        Door door1 = new Door(true, 1, 2);
        maze.getRooms().get(0).setDoor(door1, 1);
        maze.getRooms().get(1).setDoor(door1, 0);

        Door door2 = new Door(true, 2, 3);
        maze.getRooms().get(1).setDoor(door2, 2);
        maze.getRooms().get(2).setDoor(door2, 3);

        Door door3 = new Door(true, 3, 4);
        maze.getRooms().get(2).setDoor(door3, 2);
        maze.getRooms().get(3).setDoor(door3, 3);

        Door door4 = new Door(true, 4, 5);
        maze.getRooms().get(3).setDoor(door4, 1);
        maze.getRooms().get(4).setDoor(door4, 0);

        Door door5 = new Door(false, 3, 6);
        maze.getRooms().get(2).setDoor(door5, 0);
        maze.getRooms().get(5).setDoor(door5, 1);

        Key key = new Key();
        key.addDoor(door5);
        maze.getRooms().get(4).setKey(key);

        Door door6 = new Door(true, 6, 7);
        maze.getRooms().get(5).setDoor(door6, 0);
        maze.getRooms().get(6).setDoor(door6, 1);

        System.out.println(maze);
    }
}
