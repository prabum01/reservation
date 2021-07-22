package org.telepathy.reservation.model;

import lombok.Data;

import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The type Reservation panel.
 */
@Data
public class ReservationPanel {
    private static PriorityBlockingQueue<Room> priorityRooms;
    private static HashMap<Integer, Room> hotelRooms;
}
