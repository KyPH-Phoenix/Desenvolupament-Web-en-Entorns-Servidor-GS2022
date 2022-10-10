public interface MazeBuilder {
    void buildRooom(int nRoom);
    void setTarget(int nRoom);
    void buildDoor(int roomFrom, int roomTo, Maze.Directions dir);
    void buildDoor(int roomFrom, int roomTo, Maze.Directions dir, Key key);
    void putKeyInRoom(int nRoom, Key key);
    Maze getMaze();
}
