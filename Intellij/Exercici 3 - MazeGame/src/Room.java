public class Room {
    // Hacer con un mapa mejor
    private MapSite[] sites = {new Wall(), new Wall(), new Wall(), new Wall()};

    // Hacer con item
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

    @Override
    public String toString() {
        String s = String.format("\nRoom nº%d", this.roomNumber);
        s += String.format("\nNorth: %s\nSouth: %s\nEast: %s\nWest: %s", this.sites[0].getType(this.roomNumber),
                this.sites[1].getType(this.roomNumber), this.sites[2].getType(this.roomNumber),
                this.sites[3].getType(this.roomNumber));
        return s;
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
