package pl.polsl.michal.urbanek.hotelmanagementsystem.controller;

import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomViewGui;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RoomList;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomView;

/**
 * Controller class dealing with the processing of room data. It provides for
 * catching exceptions and is responsible for passing the appropriate data to
 * the model and view.
 *
 * @author Michał Urbanek
 * @version 1.1
 */
public class RoomController {

    /**
     * Room list
     */
    private RoomList rooms;
    /**
     * room-related view class object
     */
    private RoomView viewRoom;
    /**
     * controller class object associated with data find
     */
    private RoomSearchController roomSearchController;

    private RoomViewGui roomOptions;

    /**
     * Constructor
     *
     * @param roomOptions
     * @param rooms room list
     * @param viewRoom room-related view class object
     * @param roomSearchController controller class object with data find
     */
    public RoomController(RoomList rooms, RoomView viewRoom, RoomSearchController roomSearchController) {
        this.rooms = rooms;
        this.viewRoom = viewRoom;
        this.roomSearchController = roomSearchController;

    }

    /**
     * Param constructor
     *
     * @param roomOptions an object of class room options
     * @param rooms
     * @param viewRoom
     * @param roomSearchController
     */
    public RoomController(RoomViewGui roomOptions, RoomList rooms, RoomView viewRoom, RoomSearchController roomSearchController ) {
        this.rooms = rooms;
        this.viewRoom = viewRoom;
        this.roomSearchController = roomSearchController;
        this.roomOptions = roomOptions;
    }

    /**
     * Returns a new object based on the data retrieved from the view from the
     * user.
     *
     * @return a new room object
     */
    public Room CreateRoom() {
        int roomNumber = viewRoom.getRoomNumberFromConsole();
        int floor = viewRoom.getFloorFromConsole();
        double price = viewRoom.getPriceFromConsole();
        int roomType = viewRoom.getNumberOfPeopleFromConsole();

        return new Room(roomNumber, roomType, floor, price);
    }

    /**
     * Returns a new object based on the data retrieved from the view from the
     * user.
     *
     * @param roomNumber string containing the room number
     * @param floor string containing the floor
     * @param price string containing the price
     * @param roomType string containing the room type
     * @return a new room object
     */
    public Room CreateRoom(String roomNumber, String floor, String price, String roomType) {
        int roomNumber1 = Integer.parseInt(roomNumber);
        int roomType1 = Integer.parseInt(roomType);
        int floor1 = Integer.parseInt(floor);
        double price1 = Double.parseDouble(price);

        System.out.println(roomNumber1 + roomType1 + floor1 + price1);

        return new Room(roomNumber1, roomType1, floor1, price1);
    }

    /**
     * Displays a list of all rooms
     */
    public void showRoomList() {
        if (rooms.getRooms().isEmpty()) {
            viewRoom.emptyRoomListMessage();

        } else {
            viewRoom.showAllRooms(rooms);
        }
    }

    /**
     * Add a new room to the list
     */
    public void AddRoom() {
        viewRoom.addRoomLabel();
        Room room = CreateRoom();

        try {
            rooms.addRoom(room);
            viewRoom.succesfullyAddedMessage();
        } catch (InvalidDataException e) {
            viewRoom.nullRoomMessage();
        }
    }

    /**
     *
     * Add a new room to the list
     *
     * @param roomNumber string containing the room number
     * @param floor string containing the floor
     * @param price string containing the price
     * @param roomType string containing the room type
     * @return returns true if the addition was correct
     */
    public boolean addRoom(String roomNumber, String floor, String price, String roomType) {
        boolean isValid = validatiomRoomData(roomNumber, roomType, floor, price);
        
        if (isValid) {
            Room room = CreateRoom(roomNumber, floor, price, roomType);

            try {
                rooms.addRoom(room);
            } catch (InvalidDataException e) {
                roomOptions.errorMessage(e.getMessage());
                
            }
            return true;
        }
        roomOptions.errorMessage("Invalid data");
        return false;
    }

    /**
     * room id validation function
     *
     * @param id a string containing the room ID
     * @return returns true if validation was correct
     */
    public boolean validId(String id) {
        boolean isValidData = true;
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("room number");
            isValidData = false;
        }
        return isValidData;
    }

    /**
     * Supports the room menu.
     */
    public void RoomMenuOption() {
        int choice = 0;

        while (choice != 6) {
            choice = viewRoom.showRoomMenu();

            switch (choice) {
                case 1:
                    showRoomList();
                    break;
                case 2:
                    AddRoom();
                    break;
                case 3:
                    editRoom();
                    break;
                case 4:
                    deleteRoom();
                    break;
                case 5:
                    roomSearchController.RoomSearchMenu();
                    break;
                default:
                    break;

            }

        }
    }

    /**
     * Edits the room with the ID gets in the view from the user
     */
    public void editRoom() {
        if (rooms.getRooms().isEmpty()) {
            viewRoom.emptyRoomListMessage();
        } else {
            viewRoom.showAllRooms(rooms);
            viewRoom.updateRoomMessage();

            int roomId = viewRoom.getRoomIdFromConsole();
            Room editedRoom = rooms.findByID(roomId);
            if (editedRoom != null) {
                try {
                    Room room = CreateRoom();
                    rooms.updateRoom(room, editedRoom);
                    viewRoom.succesfullyUpdateMessage();
                } catch (InvalidDataException e) {
                    viewRoom.nullRoomMessage();
                }

            } else {
                viewRoom.nullRoomMessage(roomId);
            }
        }
    }

    /**
     * Edits the room with the ID gets in the view from the user
     *
     * @param id string containing the room id
     * @param roomNumber string containing the room number
     * @param floor string containing the floor
     * @param price string containing the price
     * @param typeRoom string containing the room type
     * @return
     */
    public boolean editRoom(String id, String roomNumber, String typeRoom, String floor, String price) {
        boolean isValidID = validId(id);
        boolean idValidRoomData = validatiomRoomData(roomNumber, roomNumber, floor, price);
        if (!validId(id) || !idValidRoomData) {
            roomOptions.errorMessage("Invalid data!");
            return false;
        }
        if (rooms.getRooms().isEmpty()) {
            roomOptions.errorMessage("Empty room list");
        } else {
            int roomId = Integer.parseInt(id);
            Room editedRoom = rooms.findByID(roomId);
            if (editedRoom != null) {
                try {
                    Room room = CreateRoom(roomNumber, typeRoom, floor, price);
                    rooms.updateRoom(room, editedRoom);
                    //roomOptions.setMessageOk("Succesful updated");
                } catch (InvalidDataException e) {
                    roomOptions.errorMessage("Room null object");
                }

            } else {
                roomOptions.errorMessage("Room null object");
            }
        }
        return true;
    }

    /**
     * Deletes the room with the ID gets in the view from the user
     */
    public void deleteRoom() {

        if (rooms.getRooms().isEmpty()) {
            viewRoom.emptyRoomListMessage();
        } else {
            viewRoom.showAllRooms(rooms);
            viewRoom.deleteRoomLabel();

            int id = viewRoom.getRoomIdFromConsole();
            Room room = rooms.findByID(id);
            try {
                rooms.deleteRoom(room);
                viewRoom.succesfullyDeletedMessage();
            } catch (InvalidDataException e) {
                viewRoom.nullRoomMessage(id);
            }
        }
    }

    /**
     * Deletes the room with the ID gets in the view from the user
     *
     * @param ID string containing room id
     * @return returns true if the room has been removed correctly
     */
    public boolean deleteRoom(String ID) {
        boolean isValidID = validId(ID);
        if (!isValidID) {
            roomOptions.errorMessage("Invalid id");
            return false;
        }

        if (rooms.getRooms().isEmpty()) {
            roomOptions.errorMessage("The room list is empty. Add a new room to the list");
        } else {
            int id = Integer.parseInt(ID);
            Room room = rooms.findByID(id);
            try {
                rooms.deleteRoom(room);
                roomOptions.errorMessage("The room was successfully removed from the list");
            } catch (InvalidDataException e) {
                roomOptions.errorMessage("No room with ID equal to " + id + " was found!");
            }
        }
        return true;
    }

    /**
     * method validating the correctness of data gets from the user
     *
     * @param roomNumber string containing the room number
     * @param roomType string containing the room type
     * @param floor string containing the floor
     * @param price string containing the price
     * @return
     */
    public boolean validatiomRoomData(String roomNumber, String roomType, String floor, String price) {
        boolean isValidData = true;
        
        if(roomNumber.isBlank() || roomType.isBlank() || floor.isBlank() || price.isBlank()) return false;
        try {
            Integer.parseInt(roomNumber);
        } catch (NumberFormatException e) {
            roomOptions.errorMessage(e.getMessage());
            isValidData = false;
        }
        
        try {
            Integer.parseInt(roomType);
        } catch (NumberFormatException e) {
            roomOptions.errorMessage("Invalid room type");
            isValidData = false;
        }

        try {
            Integer.parseInt(floor);
        } catch (NumberFormatException e) {
            roomOptions.errorMessage("Invalid floor");
            isValidData = false;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price");
            isValidData = false;
        }
        return isValidData;
    }

    /**
     * method returning the room list object
     *
     * @return room list object
     */
    public RoomList getRoomList() {
        return this.rooms;
    }

}
