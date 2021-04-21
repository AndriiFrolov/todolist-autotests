package com.pet.project.exceptions;

/**
 * To be thrown when something fails in the core framework (Web driver setup etc.)
 */
public class MissedPropertiesException extends RuntimeException {

    public MissedPropertiesException(final String message) {
        super(message);
    }

    public MissedPropertiesException(final Throwable cause) {
        super(cause);
    }

    public MissedPropertiesException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MissedPropertiesException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public MissedPropertiesException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
