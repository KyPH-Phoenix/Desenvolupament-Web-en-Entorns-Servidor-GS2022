public class Room {
    private MapSite[] sites = {new Wall(), new Wall(), new Wall(), new Wall()};
    private Key key = null;
    private int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setDoor(Door door, int side) {
        // side
        // 0 = North, 1 = South, 2 = East, 3 = West //
        this.sites[side] = door;
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


/*

┌ ┐ └ ┘ | -

┌---------┐
|         |
|         |
|         |
└---------┘

┌---------┐ l1
|      k1 └---
|           /
|         ┌---
└---------┘

   | / | l1
┌--┘   └--┐
|         |
|         |
|         |
└---------┘

 */
