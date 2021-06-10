package pl.polsl.michal.urbanek.hotelmanagementsystem.view;

import java.util.List;
import java.util.Scanner;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Booking;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.BookingList;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RoomList;

/**
 * View class that enables displaying and gets booking data.
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class BookingView {

    /**
     * Displays the booking menu and returns the number of the option selected
     * by the user.
     *
     * @return integer value representing a selected menu option
     */
    public int showBookingMenu() {
        System.out.println("Booking menu");
        System.out.println("[1] - Show booking list");
        System.out.println("[2] - Add new booking");
        System.out.println("[3] - Edit booking");
        System.out.println("[4] - Delete booking");
        System.out.println("[5] - Show room options");
        System.out.println("[6] - Exit\n");

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
     * Displays all data of all bookings.
     *
     * @param bookingList
     */
    public void showAllBookings(BookingList bookingList) {

        List<Booking> bookings = bookingList.getBookings();

        System.out.println("\nAll bookings:");

        System.out.println("| ID |   Check in   |   Check out   | Check-in periods |  Finall cost  |  Room number |");
        System.out.println("---------------------------------------------------------------------------------------");
        for (Booking booking : bookings) {
            System.out.print(booking.getID() + " | ");
            System.out.print(booking.getCheckInDate() + " | ");
            System.out.print(booking.getCheckOutDate() + " | ");
            System.out.print(booking.getChecInPeriods() + " | ");
            System.out.print(booking.getBookingCost() + " | ");
            System.out.print(booking.getRoom().getRoomNumber());
            System.out.println();
        }
    }

    /**
     * Displays a label when the user chooses to add a new booking.
     */
    public void addBookingLabel() {
        System.out.println("Adding a new booking to the system");
    }

    /**
     * Gets the room ID number from the user, which will identify the room
     * assigned to the bookings.
     *
     * @param rooms rooms list
     * @param viewRoom room-related view class object
     * @return integer value representing a room identification number
     */
    public int getRoomId(RoomList rooms, RoomView viewRoom) {
        Scanner scanner = new Scanner(System.in);
        int id;
        viewRoom.showAllRooms(rooms);

        System.out.println("\nEnter ID of room: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter ID of room: ");
            scanner.next();
        }
        id = scanner.nextInt();
        return id;
    }

    /**
     * Gets the check-in date from the user.
     *
     * @return string representing the check-in date
     */
    public String getFromDateOfBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start date of booking [DD/MM/YYYY]: ");
        return scanner.nextLine();
    }

    /**
     * Gets the check-out date from the user.
     *
     * @return string representing the check-out date
     */
    public String getToDateOfBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter end date of booking [DD-MM-YYYY]: ");
        return scanner.nextLine();
    }

    /**
     * Displays a message informing that the booking has been successfully added
     * to the booking list.
     */
    public void succesfullyAddedMessage() {
        System.out.println("Successfully added a new booking to the system");
    }

    /**
     * Displays the message from the exception class object when an exception
     * occurs.
     *
     * @param message string that stores the message
     */
    public void exceptionMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message informing that a room with the previously entered ID
     * has not been found.
     *
     * @param id car identification number
     */
    public void nullRoomMessage(int id) {
        System.out.println("No room with ID equal to " + id + " was found!");
    }

    /**
     * Displays a message informing that the booking list is empty.
     */
    public void emptyBookingListMessage() {
        System.out.println("The booking list is empty. Add a new booking to the base!");
    }

    /**
     * Displays a label when the user chooses to update data about booking.
     */
    public void updateBookingLabel() {
        System.out.println("Enter the updated data about the selected booking");
        System.out.println("Enter the ID of the booking you want to edit: ");
    }

    /**
     * Displays a message informing that the booking has been successfully
     * updated in the list
     */
    public void succesfullyUpdateMessage() {
        System.out.println("Successfully updated information about the booking");
    }

    /**
     * Displays a message informing that a booking with the previously entered
     * ID has not been found.
     *
     * @param id
     */
    public void nullBookingMessage(int id) {
        System.out.println("No booking with ID equal to " + id + " was found!");
    }

    /**
     * Displays a label when the user chooses to delete booking from the list.
     */
    public void deleteBookingLabel() {
        System.out.println("Enter the ID of the booking you want to delete: ");
    }

    /**
     * Gets the booking ID number from the user, which will identify the room
     * assigned to the booking.
     *
     * @return
     */
    public int getBookingId() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the ID of the booking: ");
            scanner.next();
        }
        id = scanner.nextInt();

        return id;
    }

    //Displays a message informing that the booking has been successfully deleted from the list.
    public void successfullyDeletedMessage() {
        System.out.println("The booking was successfully removed from the system");
    }
}
