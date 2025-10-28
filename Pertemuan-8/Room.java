
/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.HashMap;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private String item;

    public Room(String description, String item) {
        this.description = description;
        this.item = item;
        exits = new HashMap<>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\nYou see " + (item != null ? item : "nothing special") + " here.\n" + getExitString();
    }

    private String getExitString() {
        String exitString = "Exits:";
        for (String exit : exits.keySet()) {
            exitString += " " + exit;
        }
        return exitString;
    }

    public String takeItem() {
        if (item != null) {
            String taken = item;
            item = null;
            return taken;
        }
        return null;
    }
}