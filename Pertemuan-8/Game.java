
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private boolean finished;

    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player(currentRoom);
        finished = false;
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office;

        // buat room
        outside = new Room("outside the main entrance of the university", "a fountain");
        theatre = new Room("in a lecture theatre", "a projector");
        pub = new Room("in the campus pub", "a drink");
        lab = new Room("in a computing lab", "a laptop");
        office = new Room("in the computing admin office", "some documents");

        // atur arah keluar
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        currentRoom = outside;  // posisi awal pemain
    }

    public void play() {
        printWelcome();

        while (!finished) {
            Command command = parser.getCommand();
            processCommand(command);
        }

        System.out.println("Thank you for playing. Goodbye!");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a simple text adventure game.");
        System.out.println("Type 'help' if you need assistance.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't know what you mean...");
            return;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "look":
                lookAround();
                break;
            case "take":
                takeItem();
                break;
            case "inventory":
                player.showInventory();
                break;
            case "quit":
                finished = true;
                break;
            default:
                System.out.println("I don't know what you mean...");
        }
    }

    private void printHelp() {
        System.out.println("You are lost. You wander around the campus.");
        System.out.println("Your command words are:");
        System.out.println("   go look take inventory help quit");
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door that way!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    private void lookAround() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void takeItem() {
        Room room = player.getCurrentRoom();
        String item = room.takeItem();

        if (item != null) {
            player.addItem(item);
            System.out.println("You took the " + item + ".");
        } else {
            System.out.println("There's nothing to take here.");
        }
    }
}
