package com.pet.project.exceptions;

/**
 * To be thrown when something fails in the core framework (Web driver setup etc.)
 */
public class SQLReturnedIncorrectDataException extends RuntimeException {

    public SQLReturnedIncorrectDataException(final String message) {
        super(message);
    }

    public SQLReturnedIncorrectDataException(final Throwable cause) {
        super(cause);
    }

    public SQLReturnedIncorrectDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SQLReturnedIncorrectDataException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public SQLReturnedIncorrectDataException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
