package com.pet.project.exceptions;

/**
 * To be thrown when 500+ status code returned
 */
public class ServerException extends RuntimeException {

    public ServerException(final String message) {
        super(message);
    }

    public ServerException(final Throwable cause) {
        super(cause);
    }

    public ServerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServerException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public ServerException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
