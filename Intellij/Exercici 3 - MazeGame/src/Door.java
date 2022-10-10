public class Door implements MapSite {
    private boolean open;
    private int room1;
    private int room2;

    public Door(boolean open, int room1, int room2) {
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
    public String getType(int nRoom) {
        return "Door connecting with room nº" + ((nRoom == this.room1) ? this.room2 : this.room1);
    }
}
