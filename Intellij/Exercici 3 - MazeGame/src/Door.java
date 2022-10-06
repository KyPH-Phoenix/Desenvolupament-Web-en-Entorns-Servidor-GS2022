public class Door implements MapSite {
    private boolean open;
    private Room room1;
    private Room room2;

    public Door(boolean open) {
        this.open = open;
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
