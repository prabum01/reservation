package org.telepathy.reservation.model;

import lombok.Data;
import org.telepathy.reservation.enums.RoomStatus;

@Data
public class Room {

    public Room(Integer roomId, String roomNumber, RoomStatus roomStatus){
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
    }
    private Integer roomId;
    private String roomNumber;
    private RoomStatus roomStatus;
}
