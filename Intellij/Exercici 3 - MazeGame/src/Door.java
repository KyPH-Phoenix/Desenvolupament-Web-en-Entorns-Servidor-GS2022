public class Door implements MapSite {
    private boolean open;
    private Room room1;
    private Room room2;

    public Door(boolean open, Room room1, Room room2) {
        this.open = open;
        this.room1 = room1;
        this.room2 = room2;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void openDoor() {
        this.open = true;
    }

    @Override
    public String getType() {
        return "Door";
    }
}
