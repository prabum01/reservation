package org.telepathy.reservation.exception;

public class InvalidRoomNumberException extends Throwable {

    private String message;

    public InvalidRoomNumberException(String message) {
        super(message);
        this.message = message;
    }
}
