package org.telepathy.reservation.exception;

/**
 * The type Invalid room number exception.
 */
public class InvalidRoomNumberException extends Throwable {

    private String message;

    /**
     * Instantiates a new Invalid room number exception.
     *
     * @param message the message
     */
    public InvalidRoomNumberException(String message) {
        super(message);
        this.message = message;
    }
}
