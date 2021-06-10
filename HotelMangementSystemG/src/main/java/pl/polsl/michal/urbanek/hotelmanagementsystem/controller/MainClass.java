package pl.polsl.michal.urbanek.hotelmanagementsystem.controller;

import pl.polsl.michal.urbanek.hotelmanagementsystem.view.BookingViewGui;


/**
 * The application allows you to manage of the room in hotel. 
 * It is administered data on rooms and bookings.
 * @author Michał Urbanek
 * @version 1.0
 */
public class MainClass {

    /**
     * Creates a main application controller object for which calls a 
     * method that displays the program's main menu.
     * @param args command line parameters
     */
    public static void main(String args[]) {   
        BookingViewGui bookingOptions = new BookingViewGui();
        bookingOptions.setVisible(true);
        
    }
}
