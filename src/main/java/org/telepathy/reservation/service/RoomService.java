package org.telepathy.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telepathy.reservation.model.Room;

/**
 * The type Room service.
 */
public abstract class RoomService {

    /**
     * The Data service.
     */
    @Autowired
    public DataService dataService;

    /**
     * Service room.
     *
     * @param room the room
     */
    public abstract void serviceRoom(Room room);


}
