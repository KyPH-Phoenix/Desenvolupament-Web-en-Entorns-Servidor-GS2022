import java.util.List;

public class Key implements Item {
    private List<Door> doorList;

    public List<Door> getDoorList() {
        return doorList;
    }

    public void addDoor(Door door) {
        this.doorList.add(door);
    }
}