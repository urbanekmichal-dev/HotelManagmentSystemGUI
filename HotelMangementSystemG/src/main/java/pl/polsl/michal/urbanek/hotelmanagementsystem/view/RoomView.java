package pl.polsl.michal.urbanek.hotelmanagementsystem.view;

import java.util.List;
import java.util.Scanner;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RoomList;

/**
 * View class that enables displaying and gets room data.
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class RoomView {

    /**
     * Displays a label when the user chooses to add a new room.
     */
    public void addRoomLabel() {
        System.out.println("\nAdding a new room to the base!");
    }

    /**
     * Gets the price per night from the user.
     *
     * @return double value representing the price per night
     */
    public double getPriceFromConsole() {
        Scanner scanner = new Scanner(System.in);
        double price;

        System.out.println("Enter price per day: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter price per day:");
            scanner.next();
        }
        price = scanner.nextDouble();

        return price;
    }

    /**
     * Gets type of room from user
     *
     * @return integer value representing type of room
     */
    public int getNumberOfPeopleFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int type;

        System.out.println("Enter number of people: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter number of people:");
            scanner.next();
        }
        type = scanner.nextInt();

        return type;
    }

    /**
     * Gets floor of the room from user
     *
     * @return integer value representing floor
     */
    public int getFloorFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int floor;

        System.out.println("Enter floor: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter floor: ");
            scanner.next();
        }
        floor = scanner.nextInt();

        return floor;
    }

    /**
     * Gets number of the room from user
     *
     * @return integer values representing room number
     */
    public int getRoomNumberFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int roomNumber;

        System.out.println("Enter room number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter room number: ");
            scanner.next();
        }
        roomNumber = scanner.nextInt();

        return roomNumber;
    }

    /**
     * Displays all rooms from the list
     *
     * @param roomList room list object
     */
    public void showAllRooms(RoomList roomList) {
        List<Room> rooms = roomList.getRooms();
        System.out.println("All rooms available for booking");

        System.out.println("| Id | Room number | Floor | Type | Price |");
        System.out.println("--------------------------------------------");
        for (Room room : rooms) {
            System.out.print(room.getID() + " | ");
            System.out.print(room.getRoomNumber() + " | ");
            System.out.print(room.getFloor() + " | ");
            System.out.print(room.getNumberOfPeople() + " | ");
            System.out.print(room.getPrice() + " | ");
            System.out.println();
        }
    }

    /**
     * Displays all rooms from the list which is passed as a parameter
     *
     * @param rooms room list
     */
    public void showAllRoomsFromLIst(List<Room> rooms) {
        System.out.println("All rooms available for booking");

        System.out.println("| Id | Room number | Floor | Type | Price |");
        System.out.println("--------------------------------------------");
        for (Room room : rooms) {
            System.out.print(room.getID() + " | ");
            System.out.print(room.getRoomNumber() + " | ");
            System.out.print(room.getFloor() + " | ");
            System.out.print(room.getNumberOfPeople() + " | ");
            System.out.print(room.getPrice() + " | ");
            System.out.println();
        }
    }

    /**
     * Displays a message informing that the room list is empty
     */
    public void emptyRoomListMessage() {
        System.out.println("The room list is empty. Add a new room to the list");
    }

    /**
     * Displaying a message informing that the room has been successfully added
     * to the list
     */
    public void succesfullyAddedMessage() {
        System.out.println("Succesfully added a new room to the list");
    }

    /**
     * Displaying a message informing that the room with previously entered id
     * has not been found
     */
    public void nullRoomMessage() {
        System.out.println("Failed to create a new car");
    }

    /**
     * Displays the room menu and returns the number of the option selected by
     * the user.
     *
     * @return integer value representing a selected menu option
     */
    public int showRoomMenu() {
        System.out.println("Room menu");
        System.out.println("[1] - Show all rooms");
        System.out.println("[2] - Add room");
        System.out.println("[3] - Edit room");
        System.out.println("[4] - Delete room");
        System.out.println("[5] - Search room menu");
        System.out.println("[6] - Back to booking menu\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Your choice: ");

        int choice;
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Your choice: ");
            scanner.next();
        }
        choice = scanner.nextInt();

        return choice;
    }

    /**
     * Displaying a message when user chooses to add a new room
     */
    public void updateRoomMessage() {
        System.out.println("\nEnter the updated data about the selected room");
        System.out.println("Enter the ID of the room you want to edit: ");
    }

    /**
     * Gets the room id from the user.
     *
     * @return integer value representing room id
     */
    public int getRoomIdFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the ID of the room: ");
            scanner.next();
        }
        id = scanner.nextInt();

        return id;
    }

    /**
     * Displays a message informing that the room has been successfully updated
     * in the list.
     */
    public void succesfullyUpdateMessage() {
        System.out.println("\nSuccessfully updated information about the room");
    }

    /**
     * Displays a message informing that a room with the previously entered ID
     * has not been found.
     *
     * @param message information to show
     */
    public void nullRoomMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message informing that a room with the previously entered ID
     * has not been found.
     *
     * @param id room identification number
     */
    public void nullRoomMessage(int id) {
        System.out.println("No room with ID equal to " + id + " was found!");
    }

    /**
     * Displays a label when the user chooses to delete room from the list.
     */
    public void deleteRoomLabel() {
        System.out.println("Enter the ID of the room you want to delete: ");
    }

    /**
     * Displays a message informing that the room has been successfully deleted
     * from the list.
     */
    public void succesfullyDeletedMessage() {
        System.out.println("The room was successfully removed from the list");
    }
}
