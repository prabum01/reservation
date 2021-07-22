package org.telepathy.reservation.model;

import lombok.Data;
import org.telepathy.reservation.enums.RoomStatus;

/**
 * The type Room.
 */
@Data
public class Room {

    /**
     * Instantiates a new Room.
     *
     * @param roomId     the room id
     * @param roomNumber the room number
     * @param roomStatus the room status
     */
    public Room(Integer roomId, String roomNumber, RoomStatus roomStatus){
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
    }
    private Integer roomId;
    private String roomNumber;
    private RoomStatus roomStatus;
}
