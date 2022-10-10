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

    @Override
    public String toString() {
        String s = String.format("Maze composed by %d rooms", this.rooms.size());
        for (int i = 0; i < this.rooms.size(); i++) {
            s += "\n" + rooms.get(i).toString();
        }
        return s;
    }
}
