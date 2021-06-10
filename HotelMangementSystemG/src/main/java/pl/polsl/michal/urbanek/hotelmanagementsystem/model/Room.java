package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

/**
 * Model class for storing data about room in room list
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class Room {

    /**
     * Room identification
     */
    private int id;
    /**
     * Room number
     */
    private int roomNumber;
    /**
     * Type of room
     */
    private int typeOfRoom;
    /**
     * Price for one night of booking
     */
    private double price;
    /**
     * Local floor room
     */
    private int floor;

    /**
     * constructor
     *
     * @param roomNumber room number
     * @param typeOfRoom type of room
     * @param floor local floor room
     * @param price price for one night of booking
     */
    public Room(int roomNumber, int typeOfRoom, int floor, double price) {
        this.roomNumber = roomNumber;
        this.typeOfRoom = typeOfRoom;
        this.floor = floor;
        this.price = price;
    }

    /**
     * Returns the value of private field id
     *
     * @return integer containing the value of the private field "id"
     */
    public int getID() {
        return id;
    }
    


    /**
     * Set the value of private field id
     *
     * @param id integer room id number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value of private field roomNumber
     *
     * @return integer containing room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the value of private field numberOfPeople
     *
     * @return integer containing number of people in room
     */
    public int getNumberOfPeople() {
        return typeOfRoom;
    }

    /**
     * Returns the value of private field price
     *
     * @return double containing the value of the private field price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the value of private field floor
     *
     * @return integer containing floor of the room
     */
    public int getFloor() {
        return floor;
    }

}
