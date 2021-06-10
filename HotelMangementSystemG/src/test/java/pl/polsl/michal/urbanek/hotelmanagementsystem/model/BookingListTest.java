package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * A class for testing all public methods of the booking list
 *
 * @author Micha Urbanek
 * @version 1.0
 */
public class BookingListTest {

    /**
     * Test of addBooking method, of class BookingList.
     *
     * @param booking parametric booking
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleBookings")
    public void testAddBooking(Booking booking) throws InvalidDataException {
        //GIVEN
        BookingList bookings = new BookingList();

        //WHEN
        bookings.addBooking(booking);

        //THEN
        assertEquals(booking.getCheckInDate(), bookings.getBookings().get(0).getCheckInDate(), "Variable values ​​are not the same! - check-in date booking");
        assertEquals(booking.getCheckOutDate(), bookings.getBookings().get(0).getCheckOutDate(), "Variable values ​​are not the same! - check-out date booking");
        assertEquals(booking.getRoom(), bookings.getBookings().get(0).getRoom(), "Variable values ​​are not the same! - room");
        assertEquals(booking.getChecInPeriods(), bookings.getBookings().get(0).getChecInPeriods(), "Variable values ​​are not the same! - check-in periods");
        assertEquals(booking.getBookingCost(), bookings.getBookings().get(0).getBookingCost(), "Variable values ​​are not the same! - booking cost");
    }
    
    
    

    /**
     * Test of updateBooking method, of class BookingList.
     *
     * @param newBooking parametric bookings
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleBookings")
    public void testUpdateBooking(Booking newBooking) throws InvalidDataException {
        //Given 
        BookingList bookings = new BookingList();
        Room room = new Room(1, 2, 4, 200.50);
        Booking booking = new Booking("11/01/2020", "12/01/2020", room);
        bookings.addBooking(booking);

        //WHEN
        bookings.updateBooking(booking, newBooking);

        //THEN
        assertEquals(booking.getCheckInDate(), bookings.findByID(newBooking.getID()).getCheckInDate(), "Variable values ​​are not the same! - check-in date booking");
        assertEquals(booking.getCheckOutDate(), bookings.findByID(newBooking.getID()).getCheckOutDate(), "Variable values ​​are not the same! - check-out date booking");
        assertEquals(booking.getRoom(), bookings.findByID(newBooking.getID()).getRoom(), "Variable values ​​are not the same! - room");
        assertEquals(booking.getChecInPeriods(), bookings.findByID(newBooking.getID()).getChecInPeriods(), "Variable values ​​are not the same! - check-in periods");
        assertEquals(booking.getBookingCost(), bookings.findByID(newBooking.getID()).getBookingCost(), "Variable values ​​are not the same! - booking cost");
    }

    /**
     * Test of deleteBooking method, of class BookingList.
     *
     * @param booking parametric booking
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleBookings")
    public void testDeleteBooking(Booking booking) throws InvalidDataException {
        //GIVEN 
        BookingList bookings = new BookingList();
        bookings.addBooking(booking);
        int beforeDeleteSize = bookings.getSize();
        int i = 0;

        //WHEN
        bookings.deleteBooking(booking);

        //THEN
        assertEquals(1, beforeDeleteSize, "Variable values ​​are not the same! - booking list size before delete");
        assertEquals(0, bookings.getSize(), "Variable values ​​are not the same! - booking list size after delete");
        assertEquals(-1, bookings.getBookings().indexOf(booking), "Variable values ​​are not the same! - index deleted booking should be -1");
        for (Booking bookingElem : bookings.getBookings()) {
            assertEquals(i, booking.getID());
            i++;
        }
    }

    /**
     * Test of findByID method, of class BookingList.
     *
     * @param id parametric booking id
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testFindByID(int id) throws InvalidDataException {
        //GIVEN
        BookingList bookingList = new BookingList();
        Room room1 = new Room(1, 2, 3, 400);
        Room room2 = new Room(1, 2, 3, 400);
        Booking booking1 = new Booking("15/10/2020", "20/10/2020", room1);
        Booking booking2 = new Booking("25/10/2020", "26/11/2020", room2);

        bookingList.addBooking(booking1);
        bookingList.addBooking(booking2);

        //WHEN
        Booking findBooking = bookingList.findByID(id);

        //THEN
        if (id == 0) {
            assertEquals(findBooking.getID(), booking1.getID(), "Variable values ​​are not the same! - booking1 ID ");
        } else if (id == 1) {
            assertEquals(findBooking.getID(), booking2.getID(), "Variable values ​​are not the same! - booking2 ID ");
        } else {
            assertNull(findBooking, "Find object - null");
        }
    }

    /**
     * Test for throwing an exception when adding a new booking to the list
     *
     * @param booking parametric booking
     */
    @ParameterizedTest
    @MethodSource("nullBooking")
    public void testOfException(Booking booking) {
        //GIVEN
        BookingList bookingList = new BookingList();

        //WHEN
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> bookingList.addBooking(booking));

        //THEN
        assertEquals("Error in adding a new booking to the system. Check the correctness of the data.", exception.getMessage());
    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return stream of sample bookings
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    static Stream sampleBookings() throws InvalidDataException {
        Room room = new Room(1, 2, 4, 200.50);

        return Stream.of(new Booking("11/01/2020", "12/01/2020", room),
                new Booking("15/10/2020", "20/10/2020", room),
                new Booking("25/10/2020", "26/11/2020", room));
    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return null booking object
     */
    static Stream nullBooking() {
        Booking booking = null;
        return Stream.of(booking);
    }

}
