import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
