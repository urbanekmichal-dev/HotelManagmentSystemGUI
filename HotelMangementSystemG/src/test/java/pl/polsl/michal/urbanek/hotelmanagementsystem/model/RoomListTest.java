package pl.polsl.michal.urbanek.hotelmanagementsystem.model;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions.InvalidDataException;

/**
 * A class for testing all public methods of the RoomList
 *
 * @author Micha Urbanek
 * @version 1.0
 */
public class RoomListTest {

    /**
     * Test of addRoom method, of class RoomList
     *
     * @param room parametric room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleRooms")
    public void testAddRoom(Room room) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();

        //WHEN
        roomList.addRoom(room);

        //THEN
        assertEquals(room.getRoomNumber(), roomList.getRooms().get(0).getRoomNumber(), "Variable values ​​are not the same! - room number");
        assertEquals(room.getFloor(), roomList.getRooms().get(0).getFloor(), "Variable values ​​are not the same! - floor");
        assertEquals(room.getNumberOfPeople(), roomList.getRooms().get(0).getNumberOfPeople(), "Variable values ​​are not the same! - room type");
        assertEquals(room.getPrice(), roomList.getRooms().get(0).getPrice(), "Variable values ​​are not the same! - price");
    }
    
     
    
    

    /**
     * Test of updateRoom method, of class RoomList
     *
     * @param oldRoom parametric room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleRooms")
    public void testUpdateRoom(Room oldRoom) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();
        Room newRoom = new Room(5, 3, 3, 200);
        roomList.addRoom(oldRoom);

        //WHEN
        roomList.updateRoom(newRoom, oldRoom);

        //THEN
        assertEquals(newRoom.getRoomNumber(), roomList.findByID(oldRoom.getID()).getRoomNumber(), "Variable values ​​are not the same! - room number");
        assertEquals(newRoom.getFloor(), roomList.findByID(oldRoom.getID()).getFloor(), "Variable values ​​are not the same! - floor");
        assertEquals(newRoom.getNumberOfPeople(), roomList.findByID(oldRoom.getID()).getNumberOfPeople(), "Variable values ​​are not the same! - room type");
        assertEquals(newRoom.getPrice(), roomList.findByID(oldRoom.getID()).getPrice(), "Variable values ​​are not the same! - price");
    }

    /**
     * Test of deleteRoom method, of class RoomList.
     *
     * @param room parametric room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleRooms")
    public void testDeleteRoom(Room room) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        int beforeDeleteSize = roomList.getSize();
        int i = 0;
        //WHEN
        roomList.deleteRoom(room);

        //THEN
        assertEquals(1, beforeDeleteSize, "Variable values ​​are not the same! - room list size before delete");
        assertEquals(0, roomList.getSize(), "Variable values ​​are not the same! - room list size after delete");
        assertEquals(-1, roomList.getRooms().indexOf(room), "Variable values ​​are not the same! - index deleted room should be -1");
        for (Room roomElem : roomList.getRooms()) {
            assertEquals(i, room.getID());
            i++;
        }
    }

    /**
     * Test of findByID method, of class RoomList.
     *
     * @param id parametric room id
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testFindByID(int id) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();
        Room room1 = new Room(1, 2, 3, 400);
        Room room2 = new Room(2, 3, 4, 200);
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        //WHEN
        Room room = roomList.findByID(id);

        //THEN
        if (id == 0) {
            assertEquals(room1.getID(), room.getID(), "Variable values ​​are not the same! - room1 ID ");
        } else if (id == 1) {
            assertEquals(room2.getID(), room.getID(), "Variable values ​​are not the same! - room2 ID ");
        } else {
            assertNull(room);
        }

    }

    /**
     * Test of findByFloor method, of class RoomList.
     *
     * @param rangeOfFloors parametric range of floor of room
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleRangesOfFloors")
    public void testFindByFloor(RangeOfValues rangeOfFloors) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();
        Room room1 = new Room(1, 2, 3, 300);
        Room room2 = new Room(2, 3, 4, 300);
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        //WHEN
        List<Room> rooms = roomList.findByFloor(rangeOfFloors);

        //THEN
        rooms.stream().map(room -> {
            assertTrue(room.getFloor() >= rangeOfFloors.getMin(), "Variable values ​​are not the same! - floor is less than the minimum");
            return room;
        }).forEachOrdered(room -> {
            assertTrue(room.getFloor() <= rangeOfFloors.getMax(), "Variable values ​​are not the same! - floor is greater than the maximum");
        });
    }

    /**
     * Test of findByPrice method, of class RoomList.
     *
     * @param rangeOfPrices parametric range of prices
     * @throws InvalidDataException exception thrown in case of incorrect data
     */
    @ParameterizedTest
    @MethodSource("sampleRangesOfPrices")
    public void testFindByPrice(RangeOfValues rangeOfPrices) throws InvalidDataException {
        //GIVEN
        RoomList roomList = new RoomList();
        Room room1 = new Room(1, 2, 3, 500);
        Room room2 = new Room(2, 3, 4, 600);
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        //WHEN
        List<Room> rooms = roomList.findByFloor(rangeOfPrices);

        //THEN
        rooms.stream().map(room -> {
            assertTrue(room.getPrice() >= rangeOfPrices.getMin(), "Variable values ​​are not the same! - price is less than the minimum");
            return room;
        }).forEachOrdered(room -> {
            assertTrue(room.getPrice() <= rangeOfPrices.getMax(), "Variable values ​​are not the same! - price is greater than the maximum");
        });
    }

    /**
     * Test for throwing an exception when adding a new room to the repository.
     *
     * @param room parametric room
     */
    @ParameterizedTest
    @MethodSource("nullRoom")
    public void testOfException(Room room) {
        //GIVEN
        RoomList roomList = new RoomList();

        //WHEN
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> roomList.addRoom(room));

        //THEN
        assertEquals("Error in adding a new room to the system. Check the correctness of the data.", exception.getMessage());
    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return stream of sample rooms
     */
    static Stream sampleRooms() {
        return Stream.of(new Room(1, 2, 3, 135.50),
                new Room(2, 3, 4, 5.50),
                new Room(3, 3, 4, 200.50)
        );

    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return stream of sample ranges of floors
     */
    static Stream sampleRangesOfFloors() {

        RangeOfValues rangeOfFloors1 = new RangeOfValues(1, 2);
        RangeOfValues rangeOfFloors2 = new RangeOfValues(2, 3);
        RangeOfValues rangeOfFloors3 = new RangeOfValues(4, 10);
        RangeOfValues rangeOfFloors4 = new RangeOfValues(10, 20);
        RangeOfValues rangeOfFloors5 = new RangeOfValues(20, 40);

        return Stream.of(rangeOfFloors1, rangeOfFloors2, rangeOfFloors3, rangeOfFloors4, rangeOfFloors5);
    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return stream of sample ranges of prices
     */
    static Stream sampleRangesOfPrices() {

        RangeOfValues rangeOfPrices1 = new RangeOfValues(100, 200);
        RangeOfValues rangeOfPrices2 = new RangeOfValues(200, 300);
        RangeOfValues rangeOfPrices3 = new RangeOfValues(400, 500);
        RangeOfValues rangeOfPrices4 = new RangeOfValues(600, 1000);
        RangeOfValues rangeOfPrices5 = new RangeOfValues(1000, 2000);

        return Stream.of(rangeOfPrices1, rangeOfPrices2, rangeOfPrices3, rangeOfPrices4, rangeOfPrices5);
    }

    /**
     * Method for manufacturing data for tests.
     *
     * @return null room object
     */
    static Stream nullRoom() {
        Room room = null;
        return Stream.of(room);
    }

}
