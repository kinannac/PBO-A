
/**
 * Write a description of class CommandWords here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandWords {
    private static final String validCommands[] = {
        "go", "look", "take", "inventory", "help", "quit"
    };

    public boolean isCommand(String aString) {
        for (String command : validCommands) {
            if (command.equals(aString))
                return true;
        }
        return false;
    }

    public void showAll() {
        for (String command : validCommands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
