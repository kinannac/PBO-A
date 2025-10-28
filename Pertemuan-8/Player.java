
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<String> inventory;

    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You are not carrying anything.");
        } else {
            System.out.println("You have:");
            for (String item : inventory) {
                System.out.println("- " + item);
            }
        }
    }
}