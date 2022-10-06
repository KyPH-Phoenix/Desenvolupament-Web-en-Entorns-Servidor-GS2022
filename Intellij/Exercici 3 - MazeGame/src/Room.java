public class Room {
    private MapSite[] sites = {new Wall(), new Wall(), new Wall(), new Wall()};
    private Key key = null;
    private int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public MapSite[] getSites() {
        return sites;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
