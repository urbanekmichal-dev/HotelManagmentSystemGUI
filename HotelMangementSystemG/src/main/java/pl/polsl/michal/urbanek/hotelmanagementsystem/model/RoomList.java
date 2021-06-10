package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.BookingViewGui;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomViewGui;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * Model class for basics operation on room object from list of rooms
 *
 * @author Michał Urbanek
 * @version 1.0
 */
public class RoomList {

    /**
     * List of all rooms
     */
    private List<Room> rooms;
    /**
     * Size of the list of all rooms
     */
    private int size;

    
    /**
     * Non-parameter constructor
     */
    public RoomList() {
        rooms = new LinkedList<>();
    }
    

    /**
     * Gets the number of rooms in the list of rooms
     *
     * @return integer containing the number of rooms in the list of room
     */
    public int getSize() {
        size = rooms.size();
        return size;
    }

    /**
     * Gets the list of all rooms in list of rooms
     *
     * @return a list of all rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Adds a new room to the list of rooms
     *
     * @param room the room to be added to list of rooms
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public void addRoom(Room room) throws InvalidDataException {
        if (room != null) {
            room.setId(getSize());
            rooms.add(room);
        } else {
            throw new InvalidDataException("Error in adding a new room to list.Check the correctness of the data");
        }

    }

    /**
     * Updates data about the room specified as a parameter
     *
     * @param newRoom a new object with updated data that will be inserted in
     * place of the old one
     * @param oldRoom old object that will be replaced
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public void updateRoom(Room newRoom, Room oldRoom) throws InvalidDataException {
        if (newRoom != null && oldRoom != null) {
            newRoom.setId(oldRoom.getID());
            rooms.set(newRoom.getID(), newRoom);
        } else {
            throw new InvalidDataException("Error updating room data, please check the correctness of the data.");
        }
    }

    /**
     * Sets the ID of all rooms after deleting one of them, so that the correct
     * room numbering is maintained.
     */
    public void setRoomIds() {
        if (size > 0) {
            int i = 0;
            for (Room room : rooms) {
                room.setId(i);
                i++;
            }
        }
    }

    /**
     * Deletes a room form the list
     *
     * @param room the room to be deleted from the list
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    public void deleteRoom(Room room) throws InvalidDataException {
        if (room != null) {
            rooms.remove(room);
            setRoomIds();
        } else {
            throw new InvalidDataException("Error removing the room from the list.Check the correctness of the data.");
        }
    }

    /**
     * Returns the room with the ID given in the parameter.
     *
     * @param id room id number
     * @return object Room with the identifier given in the parameter
     */
    public Room findByID(int id) {
        for (Room room : getRooms()) {
            if (room.getID() == id) {
                return room;
            }
        }
        return null;
    }

    /**
     * Returns filtered data from a list of all room by floor specified in the
     * parameter.
     *
     * @param rangeOfFloor range of floor wanted
     * @return list of rooms matching the search criteria
     */
    public List<Room> findByFloor(RangeOfValues rangeOfFloor) {

        List<Room> results = this.getRooms()
                .stream()
                .filter(p -> p.getFloor() >= rangeOfFloor.getMin() && p.getFloor() <= rangeOfFloor.getMax())
                .collect(Collectors.toList());

        return results;
    }

    /**
     * Returns filtered data from a list of all rooms by range of room price per
     * night specified in the parameter.
     *
     * @param rangeOfPrices range of room price wanted
     * @return list of rooms matching the search criteria
     */
    public List<Room> findByPrice(RangeOfValues rangeOfPrices) {

        List<Room> results = this.getRooms()
                .stream()
                .filter(p -> p.getPrice() >= rangeOfPrices.getMin() && p.getPrice() <= rangeOfPrices.getMax())
                .collect(Collectors.toList());

        return results;
    }
    

}
