package pl.polsl.michal.urbanek.hotelmanagementsystem.controller;

import pl.polsl.michal.urbanek.hotelmanagementsystem.view.BookingViewGui;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Booking;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.BookingList;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.MenuType;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RoomList;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.BookingView;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomView;

/**
 * Controller class dealing with the processing of booking data. It provides for
 * catching exceptions and is responsible for passing the appropriate data to
 * the model and view.
 *
 * @author Michał Urbanek
 * @version 1.1
 */
public class BookingController {

    /**
     * booking list
     */
    private BookingList bookingList;
    /**
     * booking-related view class object
     */
    private BookingView viewBooking;
    /**
     * room-related view class object
     */
    private RoomView viewRoom;
    /**
     * room list
     */
    private RoomList roomlist;
    /**
     * controller class object that manages the booking
     */
    private RoomController roomController;
    /**
     * controller class object associated with data find
     */
    private RoomSearchController roomSearchController;

    /**
     * Non-parameter constructor
     */
    private BookingViewGui bookingOptions;

    public BookingController() {
        this.viewBooking = new BookingView();
        this.bookingList = new BookingList();
        this.viewRoom = new RoomView();
        this.roomlist = new RoomList();
        this.roomSearchController = new RoomSearchController(roomlist, viewRoom);
        this.roomController = new RoomController(this.roomlist, this.viewRoom, roomSearchController);
        this.bookingOptions = null;
    }
/**
 * Parameter constructor 
 * @param bookingOptions bookingOptions menu
 */
    public BookingController(BookingViewGui bookingOptions) {
        this.viewBooking = new BookingView();
        this.bookingList = new BookingList();
        this.viewRoom = new RoomView();
        this.roomlist = new RoomList();
        this.roomSearchController = new RoomSearchController(roomlist, viewRoom);
        this.roomController = new RoomController(this.roomlist, this.viewRoom, roomSearchController);
        this.bookingOptions = bookingOptions;
    }

    /**
     * Supports the booking menu.
     */
    public void BookingMenu() {

        boolean exit = false;

        while (!exit) {

            MenuType menuType = MenuType.getType(viewBooking.showBookingMenu() - 1);

            switch (menuType) {
                case VIEW_BOOKINGS:
                    if (bookingList.getBookings().isEmpty()) {
                        viewBooking.emptyBookingListMessage();
                    } else {
                        showBookingList();
                    }
                    break;
                case ADD_BOOKING:
                    addBooking();
                    break;
                case UPDATE_BOOKING:
                    editBooking();
                    break;
                case DELETE_BOOKING:
                    deleteBooking();
                    break;
                case SHOW_ROOMS_MENU:
                    showRoomsMenu();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Add a new booking to the list
     */
    public void addBooking() {
        viewBooking.addBookingLabel();
        int roomId = viewBooking.getRoomId(roomlist, viewRoom);
        Room room = roomlist.findByID(roomId);

        if (room != null) {
            try {
                Booking booking = new Booking(viewBooking.getFromDateOfBooking(), viewBooking.getToDateOfBooking(), room);
                bookingList.addBooking(booking);
                viewBooking.succesfullyAddedMessage();
            } catch (InvalidDataException e) {
                viewBooking.exceptionMessage(e.getMessage());
            }

        } else {
            viewBooking.nullRoomMessage(roomId);
        }

    }
/**
* Add a new booking to the list   
  * @param checkInDate string containing check-in date
 * @param checkOutDate string containing check-out date
 * @param roomID string containing room iD
 * @param name string containing name
 * @param surname  string containing surname
 */
    public void addBooking(String checkInDate, String checkOutDate, String roomID, String name, String surname) {
        if (idValidation(roomID) && validatiomBookingData(name, surname, checkInDate, checkOutDate)) {
            int roomId = Integer.parseInt(roomID);
            Room room = roomlist.findByID(roomId);

            if (room != null) {
                try {
                    Booking booking = new Booking(checkInDate, checkOutDate, room, name, surname);
                    bookingList.addBooking(booking);

                } catch (InvalidDataException e) {
                    bookingOptions.errorMessage(e.getMessage());
                }
            } else {

                bookingOptions.errorMessage("No room with ID equal to " + roomId + " was found!");
            }
        }

    }
    /**
     * Displays a list of bookings
     */
    public void showRoomsMenu() {
        roomController.RoomMenuOption();
    }
    /**
     * Displays a list of bookings
     */
    public void showBookingList() {
        if (bookingList.getBookings().isEmpty()) {
            viewBooking.emptyBookingListMessage();
        } else {
            viewBooking.showAllBookings(bookingList);
        }
    }

    /**
     * Method for updating reservation elements
     */
    public void editBooking() {
        int bookingId;
        Booking bookingFromList;
        if (bookingList.getBookings().isEmpty()) {
            viewBooking.emptyBookingListMessage();
        } else {
            viewBooking.showAllBookings(bookingList);
            viewBooking.updateBookingLabel();

            bookingId = viewBooking.getRoomId(roomlist, viewRoom);
            bookingFromList = bookingList.findByID(bookingId);

            if (bookingFromList != null) {
                int roomId = viewBooking.getRoomId(roomlist, viewRoom);
                Room room = roomlist.findByID(roomId);

                if (room != null) {
                    try {
                        Booking booking = new Booking(viewBooking.getFromDateOfBooking(), viewBooking.getToDateOfBooking(), room);
                        bookingList.updateBooking(booking, bookingFromList);
                        viewBooking.succesfullyUpdateMessage();
                    } catch (InvalidDataException e) {
                        viewBooking.exceptionMessage(e.getMessage());
                    }
                } else {
                    viewBooking.nullRoomMessage(roomId);
                }

            } else {
                viewBooking.nullBookingMessage(bookingId);
            }
        }
    }
/**
 * method for updating reservation elements
 * @param checkInDate string containing check-in date
 * @param checkOutDate string containing check-out date
 * @param roomID string containing room iD
 * @param bookingID string containing booking ID
 * @param name string containing name
 * @param surname  string containing surname
 */
    public void editBooking(String checkInDate, String checkOutDate, String roomID, String bookingID, String name, String surname) {
        int bookingId;
        Booking bookingFromList;
        if (bookingList.getBookings().isEmpty()) {
            viewBooking.emptyBookingListMessage();
        } else {
            viewBooking.showAllBookings(bookingList);
            viewBooking.updateBookingLabel();

            bookingId = Integer.parseInt(bookingID);
            bookingFromList = bookingList.findByID(bookingId);

            if (bookingFromList != null) {
                int roomId = Integer.parseInt(roomID);
                Room room = roomlist.findByID(roomId);

                if (room != null) {
                    try {
                        Booking booking = new Booking(checkInDate, checkOutDate, room);
                        bookingList.updateBooking(booking, bookingFromList);
                        viewBooking.succesfullyUpdateMessage();
                    } catch (InvalidDataException e) {
                        viewBooking.exceptionMessage(e.getMessage());
                    }
                } else {
                    viewBooking.nullRoomMessage(roomId);
                }

            } else {
                viewBooking.nullBookingMessage(bookingId);
            }
        }
    }

    /**
     * Deletes the booking with the ID gets in the view from the user
     */
    public void deleteBooking() {
        if (bookingList.getBookings().isEmpty()) {
            viewBooking.emptyBookingListMessage();
        } else {
            viewBooking.showAllBookings(bookingList);
            viewBooking.deleteBookingLabel();

            int id = viewBooking.getBookingId();
            Booking booking = bookingList.findByID(id);

            try {
                bookingList.deleteBooking(booking);
                viewBooking.successfullyDeletedMessage();
            } catch (InvalidDataException e) {
                viewBooking.nullBookingMessage(id);
            }
        }
    }
/**
 * Deletes the booking from the list 
 * @param ID string containing the room ID
 */
    public void deleteBooking(String ID) {
        if (bookingList.getBookings().isEmpty()) {
            //viewBooking.emptyBookingListMessage();
        } else {
            viewBooking.showAllBookings(bookingList);
            viewBooking.deleteBookingLabel();

            int id = Integer.parseInt(ID);
            Booking booking = bookingList.findByID(id);

            try {
                bookingList.deleteBooking(booking);
                viewBooking.successfullyDeletedMessage();
            } catch (InvalidDataException e) {
                viewBooking.nullBookingMessage(id);
            }
        }
    }
/**
 * method that returns a list of reservations
 * @return booking list object
 */
    public BookingList getBookingList() {
        return this.bookingList;
    }
/**
 * method returning the room list object
 * @return room object
 */
    public RoomList getRoomList() {
        return this.roomlist;
    }
    
    public RoomController getRoomController() {
        return this.roomController;
    }

    /**
     *  method validating the correctness of data gets from the user
     * @param name string containing the first name
     * @param surname string containing the surname 
     * @param checkInDate string containing the check-in date
     * @param checkOutDate string containing the first name
     * @return returns true if validation passed successfully
     */
    public boolean validatiomBookingData(String name, String surname, String checkInDate, String checkOutDate) {
        boolean isValidData = true;

        if (name.isBlank()) {
            bookingOptions.errorMessage("Invalid name!");
            isValidData = false;
        }

        if (surname.isBlank()) {
            bookingOptions.errorMessage("Invalid surname!");
            isValidData = false;
        }

        if (checkInDate.isBlank()) {
            bookingOptions.errorMessage("Invalid check-in date!");
            isValidData = false;
        }

        if (checkInDate.isBlank()) {
            bookingOptions.errorMessage("Invalid check-out date!");
            isValidData = false;
        }

        return isValidData;
    }

    
    /**
     * booking id validation function
     * @param bookingId a string containing the booking ID
     * @return returns true if validation was correct
     */
    public boolean idValidation(String bookingId) {
        boolean isValidData = true;
        try {
            Integer.parseInt(bookingId);
        } catch (NumberFormatException e) {
            bookingOptions.errorMessage("Invalid id");
            isValidData = false;
        }
        return isValidData;
    }

}
