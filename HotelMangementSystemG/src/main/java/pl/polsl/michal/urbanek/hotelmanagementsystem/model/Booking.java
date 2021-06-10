package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * Model class for storing data about a single booking
 *
 * @author Michał Urbanek
 * @version 1.1
 */
public class Booking {

    /**
     * booking identification number
     */
    private int id;
    /**
     * Check-in date of booking
     */
    private String fromDate;
    /**
     * Check-out date of booking
     */
    private String toDate;
    /**
     * Booking room
     */
    private Room room;
    /**
     * Number of days of booking
     */
    private long bookingDays;
    /**
     * Finall cost of booking
     */
    private double bookingCost;
    /**
     * user name
     */
    private String name;
    
    /**
     * user surname
     */
    private String surname;

    /**
     * Constructor
     *
     * @param fromDate check-in date
     * @param toDate check-out date
     * @param room a booking room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public Booking(String fromDate, String toDate, Room room) throws InvalidDataException {
        Date fromDate1 = setDate(fromDate);
        Date toDate1 = setDate(toDate);
        this.fromDate = fromDate;
        this.toDate = toDate;
        dateValidation(fromDate1, toDate1);
        this.room = room;
    }
    
    /**
     * Constructor 

     * @param name user name
     * @param surname user surname
     * @param fromDate check-in date
     * @param toDate check-out date
     * @param room a booking room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public Booking(String fromDate, String toDate, Room room,String name, String surname) throws InvalidDataException {
        Date fromDate1 = setDate(fromDate);
        Date toDate1 = setDate(toDate);
        this.fromDate = fromDate;
        this.toDate = toDate;
        dateValidation(fromDate1, toDate1);
        this.name=name;
        this.surname=surname;
        this.room = room;
    }
    
    

    /**
     * Checks the correctness of the date
     *
     * @param fromDate1 check-in date
     * @param toDate1 check-out date
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public void dateValidation(Date fromDate1, Date toDate1) throws InvalidDataException {
        if (this.fromDate.isBlank() || fromDate == null || fromDate1.after(toDate1)) {
            throw new InvalidDataException("Invalid date. Please check that it is in the correct format [DD/MM/YYYY]."
                    + " \nThe booking end date cannot be later than the start date.");
        } else if (this.toDate.isBlank() || toDate1 == null || toDate1.before(toDate1)) {
            throw new InvalidDataException("Invalid date. Please check that it is in the correct format [DD/MM/YYYY]. "
                    + "\nThe booking end date cannot be later than the start date.");
        }

    }

    /**
     * Returns a date passed as a string in the appropriate format.
     *
     * @param dateString string to create the date
     * @return date in the format corresponding to the pattern
     */
    public Date setDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
        return date;
    }

    /**
     * Returns the value of the private field "id"
     *
     * @return integer containing the value of the private field in the booking
     */
    public int getID() {
        return id;
    }

    /**
     * 
     * Returns the value of the private field "name"
     *
     * @return string containing the value of the private field in the booking
     */
    public String getName()
    {
        return this.name;
    }
      /**
     * 
     * Returns the value of the private field "surname"
     *
     * @return string containing the value of the private field in the booking
     */
    public String getSurname()
    {
        return this.surname;
    }
    
    /**
     * Set the value of the private field "id"
     *
     * @param id booking identification number
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Returns the value of the private field check-in
     *
     * @return string containing the value of the private field check-in
     */
    public String getCheckInDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date date = setDate(fromDate);
        return dateFormat.format(date);
    }

    /**
     * Returns the value of the private field check-out
     *
     * @return string containing the value of the private field check-out
     */
    public String getCheckOutDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = setDate(toDate);
        return dateFormat.format(date);
    }

    /**
     * Returns the value of the private field bookingDays
     *
     * @return integer containing the value of the private field bookingDays
     */
    public long getChecInPeriods() {
        Date startDate = setDate(fromDate);
        Date endDate = setDate(toDate);
        long dateDifference = endDate.getTime() - startDate.getTime();
        bookingDays = (dateDifference / (1000 * 60 * 60 * 24)) % 365;
        bookingDays++;
        return bookingDays;
    }

    /**
     * Returns the value of the private field bookingCost
     *
     * @return double containing the value of the private field bookingCost
     */
    public double getBookingCost() {
        bookingCost = this.getChecInPeriods() * this.room.getPrice();
        return bookingCost;
    }

    /**
     * Returns the object of the private field room
     *
     * @return object containing the value of the private field room
     */
    public Room getRoom() {
        return room;
    }
}
