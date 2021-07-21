package org.telepathy.reservation.service;

import org.telepathy.reservation.enums.RoomStatus;

public class RoomServiceFactory {

    public RoomService getRoomService(RoomStatus roomStatus){
        switch (roomStatus) {
            case Available:
                return new AvailableRoomService();
            case Occupied:
                return new OccupiedRoomService();
            case Vacant:
                return new VacantRoomService();
            case Repair:
                return new RepairRoomService();
            default:
                return null;
        }
    }
}
