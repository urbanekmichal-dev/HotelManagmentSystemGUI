package pl.polsl.michal.urbanek.hotelmanagementsystem.controller;

import java.util.List;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RangeOfValues;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.Room;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.RoomList;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomView;
import pl.polsl.michal.urbanek.hotelmanagementsystem.view.RoomSearchView;

/**
 * Controller class dealing with retrieving data from the model according to the
 * parameters obtained from the view.
 *
 * @author Micha Urbanek
 * @version 1.1
 */
public class RoomSearchController {

    /**
     * room list
     */
    private RoomList roomList;
    /**
     * room-related view class object
     */
    private RoomView viewRoom;
    /**
     * search-related view class object
     */
    private RoomSearchView viewRoomSearch;

    /**
     * Constructor
     *
     * @param roomList room list object
     * @param viewRoom room-related view class object
     */
    public RoomSearchController(RoomList roomList, RoomView viewRoom) {
        this.roomList = roomList;
        this.viewRoom = viewRoom;
        this.viewRoomSearch = new RoomSearchView();
    }

    /**
     * Supports the room search menu.
     */
    public void RoomSearchMenu() {
        int choice = viewRoomSearch.showSearchMenu();

        switch (choice) {
            case 1:
                searchByFloor();
                break;
            case 2:
                searchByPrice();
                break;
            default:
                viewRoomSearch.optionOutsideMenuMessage();
                break;
        }
    }

    /**
     * Gets from the list all room with a floor acquired in the view from the
     * user.
     */
    public void searchByFloor() {

        RangeOfValues rangeOfFloor = viewRoomSearch.getFloorToSearch();
        viewRoomSearch.searchResultLabel();

        List<Room> results = roomList.findByFloor(rangeOfFloor);

        if (results.isEmpty()) {
            viewRoomSearch.emptyListMessage();
        } else {
            viewRoom.showAllRoomsFromLIst(results);
        }
    }

    /**
     * Gets all rooms from the list in accordance with the range of prices
     * obtained in the view from the user.
     */
    public void searchByPrice() {

        RangeOfValues rangeOfPrices = viewRoomSearch.getPricesToSearch();
        viewRoomSearch.searchResultLabel();

        List<Room> results = roomList.findByPrice(rangeOfPrices);

        if (results.isEmpty()) {
            viewRoomSearch.emptyListMessage();
        } else {
            viewRoom.showAllRoomsFromLIst(results);
        }
    }

}
