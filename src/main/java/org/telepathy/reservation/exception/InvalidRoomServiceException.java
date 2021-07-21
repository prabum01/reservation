package org.telepathy.reservation.exception;

public class InvalidRoomServiceException extends Throwable {

    private String message;

    public InvalidRoomServiceException(String message) {
        super(message);
        this.message = message;
    }
}
