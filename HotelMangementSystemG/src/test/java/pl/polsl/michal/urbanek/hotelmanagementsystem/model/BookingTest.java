package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * A class for testing all public methods of the booking
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class BookingTest {

    /**
     * Test of getChecInPeriods method, of class Booking.
     *
     * @param checkIn parametric check-in date
     * @param checkOut parametric check-out date
     * @param days parametric number of days
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @CsvSource({"01/06/2020,12/06/2020,12", "10/10/2019,12/10/2019,3", "17/12/2020,19/12/2020,3", "05/12/2020,10/12/2020,6"})
    public void testGetChecInPeriods(String checkIn, String checkOut, String days) throws InvalidDataException {
        //GIVEN
        Room room = new Room(4, 4, 5, 300);
        Booking booking = new Booking(checkIn, checkOut, room);

        //WHEN
        long daysPeriods = booking.getChecInPeriods();

        //THEN
        assertEquals(Integer.parseInt(days), daysPeriods, "Variable values ​​are not the same! - check-in days");
    }

    /**
     * Test of getBookingCost method, of class Booking.
     *
     * @param checkIn parametric check-in date
     * @param checkOut parametric check-out date
     * @param cost parametric final cost
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @CsvSource({"01/06/2020,12/06/2020,3600.0", "10/10/2019,12/10/2019,900.0", "17/12/2020,19/12/2020,900", "05/12/2020,10/12/2020,1800.0"})
    public void testGetBookingCost(String checkIn, String checkOut, String cost) throws InvalidDataException {
        //GIVEN
        Room room = new Room(4, 4, 5, 300);
        Booking booking = new Booking(checkIn, checkOut, room);

        //WHEN
        double bookingCost = booking.getBookingCost();

        //THEN
        assertEquals(Double.parseDouble(cost), bookingCost, "Variable values ​​are not the same! - booking cost");
    }

    /**
     * Test for throwing an appropriate exception when specifying an incorrect
     * date
     *
     * @param checkIn parametric check-in date
     * @param checkOut parametric check-out date
     */
    @ParameterizedTest
    @CsvSource({"15.08.2020,12-13-2020", "15/12/2019,13-11-2020", "15/08/2020,12/13/2020"})
    public void testValidDate(String checkIn, String checkOut) {
        //GIVEN
        Room room = new Room(4, 4, 5, 300);

        try {
            //WHEN
            Booking booking = new Booking(checkIn, checkOut, room);
            //THEN

        } catch (InvalidDataException e) {
            fail("Incorrect data format.");
        }
    }
}
