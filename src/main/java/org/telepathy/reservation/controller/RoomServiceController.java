package org.telepathy.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telepathy.reservation.exception.InvalidRoomNumberException;
import org.telepathy.reservation.exception.InvalidRoomServiceException;
import org.telepathy.reservation.service.DataService;

/**
 * The type Room service controller.
 */
@RestController
public class RoomServiceController {

    @Autowired
    private DataService dataService;

    /**
     * Gets available rooms.
     *
     * @return the available rooms
     */
    @GetMapping(value = "/api/rooms", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getAvailableRooms() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dataService.getAvailableRooms());
    }

    /**
     * Check in response entity.
     *
     * @return the response entity
     */
    @PostMapping(value = "/api/in", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity checkIn() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dataService.checkIn());
    }

    /**
     * Check out response entity.
     *
     * @param roomNumber the room number
     * @return the response entity
     * @throws InvalidRoomNumberException  the invalid room number exception
     * @throws InvalidRoomServiceException the invalid room service exception
     */
    @PostMapping(value = "/api/out", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity checkOut(@RequestParam(value = "roomNumber", required = true) String roomNumber)
            throws InvalidRoomNumberException, InvalidRoomServiceException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dataService.checkOut(roomNumber));
    }

    /**
     * Clean response entity.
     *
     * @param roomNumber the room number
     * @return the response entity
     * @throws InvalidRoomNumberException  the invalid room number exception
     * @throws InvalidRoomServiceException the invalid room service exception
     */
    @PostMapping(value = "/api/clean", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity clean(@RequestParam(value = "roomNumber", required = true) String roomNumber)
            throws InvalidRoomNumberException, InvalidRoomServiceException {
        dataService.clean(roomNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(HttpStatus.OK.toString());
    }

    /**
     * Out of service response entity.
     *
     * @param roomNumber the room number
     * @return the response entity
     * @throws InvalidRoomNumberException  the invalid room number exception
     * @throws InvalidRoomServiceException the invalid room service exception
     */
    @PostMapping(value = "/api/outOfService", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity outOfService(@RequestParam(value = "roomNumber", required = true) String roomNumber)
            throws InvalidRoomNumberException, InvalidRoomServiceException {
        dataService.outOfService(roomNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(HttpStatus.OK.toString());
    }

    /**
     * Repair response entity.
     *
     * @param roomNumber the room number
     * @return the response entity
     * @throws InvalidRoomNumberException  the invalid room number exception
     * @throws InvalidRoomServiceException the invalid room service exception
     */
    @PostMapping(value = "/api/repair", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity repair(@RequestParam(value = "roomNumber", required = true) String roomNumber)
            throws InvalidRoomNumberException, InvalidRoomServiceException {
        dataService.repair(roomNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(HttpStatus.OK.toString());
    }
}
