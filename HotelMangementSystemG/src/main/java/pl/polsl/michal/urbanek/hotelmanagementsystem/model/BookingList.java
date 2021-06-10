package pl.polsl.michal.urbanek.hotelmanagementsystem.model;
import java.util.LinkedList;
import java.util.List;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * Model class for basics operations on bookings
 * 
 * @author Michał Urbanek
 * @version 1.0
 */
public class BookingList {
/**
 * List of all bookings
 */
    private List<Booking> bookingList;
    /**
     * Size of the list of bookings
     */
    private int size;

    /**
     * Non-parameter constructor 
     */
    public BookingList() {
        bookingList = new LinkedList<>();
    }
/**
 * Gets the number of bookings in the list
 * @return integer containing the number of bookings in the list.
 */
    public int getSize() {
        size = bookingList.size();
        return size;
    }

    /**
     * Gets a list of all bookings from the list
     * @return a list of all bookings
     */
    public List<Booking> getBookings() {
        return bookingList;
    }

    /**
     * Adds a new booking to the list.
     * @param booking the booking to be added to the list
     * @throws InvalidDataException  exception thrown in case of incorrect data
     */
    public void addBooking(Booking booking) throws InvalidDataException {
        if (booking != null) {
            booking.setID(this.getSize());
            bookingList.add(booking);
        } else {
            throw new InvalidDataException("Error in adding a new booking to the system. Check the correctness of the data.");
        }
    }

    /**
     * Updates data about the new booking specified as a parameter.
     * 
     * @param newBooking  new object with updated data that will be inserted in place of the old one
     * @param oldBooking old object that will be replaced
     * @throws InvalidDataException  exception thrown in case of incorrect data
     */
    public void updateBooking(Booking newBooking, Booking oldBooking) throws InvalidDataException {
        if (newBooking != null && oldBooking != null) {
            newBooking.setID(oldBooking.getID());
            bookingList.set(newBooking.getID(), newBooking);
        } else {
            throw new InvalidDataException("Error updating booking data, please check the correctness of the data.");
        }
    }
/**
 * Sets the ID of all bookings after deleting one of them
 * 
 */
    public void setBookingId() {
        if (size > 0) {
            int i = 0;
            for (Booking booking : bookingList) {
                booking.setID(i);
                i++;
            }
        }
    }
/**
 * Deletes a booking from the list
 * @param booking the booking to be deleted from the list
 * @throws InvalidDataException exception thrown in case of incorrect data
 */
    public void deleteBooking(Booking booking) throws InvalidDataException {
        if (booking != null) {
            bookingList.remove(booking);
            setBookingId();
        } else {
            throw new InvalidDataException("Error removing the booking from the system. Check the correctness of the data.");
        }
    }

    /**
     * Returns the booking with the id given in the parameter.
     * @param id booking identification number
     * @return object Booking with the identifier given in the parameter
     */
    public Booking findByID(int id) {
        for (Booking booking : bookingList) {
            if (booking.getID() == id) {
                return booking;
            }
        }
        return null;
    }
}
