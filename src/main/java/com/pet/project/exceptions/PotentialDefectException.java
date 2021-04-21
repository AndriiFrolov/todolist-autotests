package com.pet.project.exceptions;

/**
 * To be thrown when test script error identified
 */
public class PotentialDefectException extends RuntimeException {

    public PotentialDefectException(final String message) {
        super(message);
    }

    public PotentialDefectException(final Throwable cause) {
        super(cause);
    }

    public PotentialDefectException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PotentialDefectException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public PotentialDefectException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
