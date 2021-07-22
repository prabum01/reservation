package org.telepathy.reservation.exception;

/**
 * The type Invalid room service exception.
 */
public class InvalidRoomServiceException extends Throwable {

    private String message;

    /**
     * Instantiates a new Invalid room service exception.
     *
     * @param message the message
     */
    public InvalidRoomServiceException(String message) {
        super(message);
        this.message = message;
    }
}
