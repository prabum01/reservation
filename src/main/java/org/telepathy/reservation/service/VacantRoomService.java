package org.telepathy.reservation.service;

import org.telepathy.reservation.enums.RoomStatus;
import org.telepathy.reservation.model.Room;

/**
 * The type Vacant room service.
 */
public class VacantRoomService extends RoomService {

    @Override
    public void serviceRoom(Room room) {
        room.setRoomStatus(RoomStatus.Occupied);
        dataService.pollPriorityRoom();
    }
}
