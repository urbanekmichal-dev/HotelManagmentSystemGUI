package pl.polsl.michal.urbanek.hotelmanagementsystem.view;

import java.util.Scanner;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RangeOfValues;

/**
 * View class that allows interaction with the user in terms of searching for
 * data according to the parameters provided by him.
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class RoomSearchView {

    /**
     * Displays the search menu and returns the number of the option selected by
     * the user.
     *
     * @return integer value representing a selected menu option
     */
    public int showSearchMenu() {

        System.out.println("[1] - Search by floor");
        System.out.println("[2] - Search by price");

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
     * Displays a message that appears when selecting an option outside of the
     * menu
     */
    public void optionOutsideMenuMessage() {
        System.out.println("Please enter a number between 1-2");
    }

    /**
     * Returns a new model class object for storing a range of floors .
     *
     * @return range of floors
     */
    public RangeOfValues getFloorToSearch() {
        Scanner scanner = new Scanner(System.in);
        int min, max;

        System.out.println("Enter the minimum floor: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the minimum floor: ");
            scanner.next();
        }
        min = scanner.nextInt();

        System.out.println("Enter the maximum floor: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the maximum floor: ");
            scanner.next();
        }
        max = scanner.nextInt();

        return new RangeOfValues(min, max);
    }

    /**
     * Displays the label when the user sees the search results.
     */
    public void searchResultLabel() {
        System.out.println("\nRooms meeting the assumptions");
    }

    /**
     * Displays a message informing you that the room list is empty.
     */
    public void emptyListMessage() {
        System.out.println("No rooms meeting the given assumptions were found");
    }

    /**
     * Returns a new class object for storing a range of prices per night.
     *
     * @return
     */
    public RangeOfValues getPricesToSearch() {
        Scanner scanner = new Scanner(System.in);
        int min, max;

        System.out.println("Enter the minimum price: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the minimum price: ");
            scanner.next();
        }
        min = scanner.nextInt();

        System.out.println("Enter the maximum price: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That is not a valid number!");
            System.out.println("Enter the maximum price: ");
            scanner.next();
        }
        max = scanner.nextInt();

        return new RangeOfValues(min, max);
    }

}
