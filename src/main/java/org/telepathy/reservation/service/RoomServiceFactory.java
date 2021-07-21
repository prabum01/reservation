package org.telepathy.reservation.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.telepathy.reservation.enums.RoomStatus;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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
