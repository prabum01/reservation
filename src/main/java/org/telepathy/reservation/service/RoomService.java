package org.telepathy.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telepathy.reservation.model.Room;

public abstract class RoomService {

    @Autowired
    public DataService dataService;

    public abstract void serviceRoom(Room room);


}
