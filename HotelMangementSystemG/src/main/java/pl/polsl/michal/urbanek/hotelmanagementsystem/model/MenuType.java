package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

/**
 * The class implies what option in the menu has been selected.
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public enum MenuType {
    VIEW_BOOKINGS, ADD_BOOKING, UPDATE_BOOKING, DELETE_BOOKING, SHOW_ROOMS_MENU, EXIT;

    /**
     * Returns the value of enum menu type
     *
     * @param i iterator
     * @return returns an enum value of menu type
     */
    public static MenuType getType(int i) {
        return values()[i];
    }
}
