import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Item> inventory = new ArrayList<>();

    public void addItem(Item item) {
        this.inventory.add(item);
    }
}
