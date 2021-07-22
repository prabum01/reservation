package org.telepathy.reservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telepathy.reservation.enums.RoomStatus;
import org.telepathy.reservation.exception.InvalidRoomNumberException;
import org.telepathy.reservation.exception.InvalidRoomServiceException;
import org.telepathy.reservation.model.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

/**
 * The type Data service.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataService {

    @Value("${number.of.floors}")
    private Integer floorCount = 4;

    @Value("${room.numbers}")
    private String roomNumbers = "A,B,C,D,E";

    private List<String> roomNumberList;
    private ConcurrentHashMap<String, Room> hotelRooms;
    private PriorityBlockingQueue<Room> priorityRooms;

    /**
     * Instantiates a new Data service.
     */
    public DataService() {
        initializeRooms();
    }

    private void initializeRooms() {
        if (floorCount < 1 || (roomNumbers != null && roomNumbers.length() < 1)) {
            throw new IllegalArgumentException("Floor or Room must be at least 1");
        }

        roomNumberList =  Arrays.stream(roomNumbers.split(",")).collect(Collectors.toList());
        hotelRooms = new ConcurrentHashMap<>();
        priorityRooms = new PriorityBlockingQueue<>(floorCount * roomNumberList.size(),
                Comparator.comparing(Room::getRoomId)
        );

        int totalRoomCounter = 1;
        int roomCounter = 0;
        for (int i = 1; i < floorCount + 1; i++) {
            while (roomCounter < roomNumberList.size()) {
                totalRoomCounter++;
                String roomNumber = i + roomNumberList.get(roomCounter);
                Room room = new Room(totalRoomCounter, roomNumber, RoomStatus.Available);
                hotelRooms.put(roomNumber, room);
                priorityRooms.add(room);
                roomCounter++;
            }
            roomCounter = 0;
            Collections.reverse(roomNumberList);
        }
    }

    /**
     * Poll priority room room.
     *
     * @return the room
     */
    public Room pollPriorityRoom() {
        return priorityRooms.poll();
    }

    /**
     * Add to priority room.
     *
     * @param room the room
     */
    public void addToPriorityRoom(Room room) {
        priorityRooms.add(room);
    }

    /**
     * Gets hotel rooms.
     *
     * @return the hotel rooms
     */
    public ConcurrentHashMap<String, Room> getHotelRooms() {
        return hotelRooms;
    }

    /**
     * Gets available rooms.
     *
     * @return the available rooms
     */
    public List<String> getAvailableRooms() {
        List<String> availableRooms = this.getHotelRooms().entrySet()
                .stream().filter(f -> f.getValue().getRoomStatus().equals(RoomStatus.Available))
                .map(m -> m.getValue().getRoomNumber())
                .collect(Collectors.toList());

        return availableRooms;
    }

    /**
     * Check in string.
     *
     * @return the string
     */
    public String checkIn() {
        Room checkedInRoom = pollPriorityRoom();
        Room currentRoom = this.getHotelRooms().get(checkedInRoom.getRoomNumber());
        currentRoom.setRoomStatus(RoomStatus.Occupied);
        return currentRoom.getRoomNumber();
    }

    /**
     * Check out string.
     *
     * @param roomNumber the room number
     * @return the string
     * @throws InvalidRoomNumberException  the invalid room number exception
     * @throws InvalidRoomServiceException the invalid room service exception
     */
    public String checkOut(String roomNumber) throws InvalidRoomNumberException, InvalidRoomServiceException {
        Room currentRoom = this.getHotelRooms().get(roomNumber);
        if(currentRoom == null){
            throw new InvalidRoomNumberException("Room " + roomNumber + " does not exists");
        }
        if (!currentRoom.getRoomStatus().toString().equals(RoomStatus.Occupied.toString())) {
            throw new InvalidRoomServiceException("Room " + roomNumber + " is not occupied for checkout");
        }
        currentRoom.setRoomStatus(RoomStatus.Vacant);
        return currentRoom.getRoomNumber();
    }

    /**
     * Clean.
     *
     * @param roomNumber the room number
     * @throws InvalidRoomServiceException the invalid room service exception
     * @throws InvalidRoomNumberException  the invalid room number exception
     */
    public void clean(String roomNumber) throws InvalidRoomServiceException, InvalidRoomNumberException {
        Room currentRoom = this.getHotelRooms().get(roomNumber);
        if(currentRoom == null){
            throw new InvalidRoomNumberException("Room " + roomNumber + " does not exists");
        }
        if (!currentRoom.getRoomStatus().toString().equals(RoomStatus.Vacant.toString())) {
            throw new InvalidRoomServiceException("Room " + roomNumber + " is not vacant for cleaning");
        }
        currentRoom.setRoomStatus(RoomStatus.Available);
        addToPriorityRoom(currentRoom);
    }

    /**
     * Out of service.
     *
     * @param roomNumber the room number
     * @throws InvalidRoomServiceException the invalid room service exception
     * @throws InvalidRoomNumberException  the invalid room number exception
     */
    public void outOfService(String roomNumber) throws InvalidRoomServiceException, InvalidRoomNumberException {
        Room currentRoom = this.getHotelRooms().get(roomNumber);
        if(currentRoom == null){
            throw new InvalidRoomNumberException("Room " + roomNumber + " does not exists");
        }
        if (!(currentRoom.getRoomStatus().toString().equals(RoomStatus.Vacant.toString())
                || currentRoom.getRoomStatus().toString().equals(RoomStatus.Available.toString())
                || currentRoom.getRoomStatus().toString().equals(RoomStatus.Occupied.toString()))) {
            throw new InvalidRoomServiceException("Room " + roomNumber + " is not vacant for repair or it been available / occupied");
        }
        currentRoom.setRoomStatus(RoomStatus.Repair);
    }

    /**
     * Repair.
     *
     * @param roomNumber the room number
     * @throws InvalidRoomServiceException the invalid room service exception
     * @throws InvalidRoomNumberException  the invalid room number exception
     */
    public void repair(String roomNumber) throws InvalidRoomServiceException, InvalidRoomNumberException {
        Room currentRoom = this.getHotelRooms().get(roomNumber);
        if(currentRoom == null){
            throw new InvalidRoomNumberException("Room " + roomNumber + " does not exists");
        }
        if (!currentRoom.getRoomStatus().toString().equals(RoomStatus.Repair.toString())) {
            throw new InvalidRoomServiceException("Room " + roomNumber + " is not for repair");
        }
        currentRoom.setRoomStatus(RoomStatus.Vacant);
    }
}
