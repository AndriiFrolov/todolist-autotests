package com.pet.project.exceptions;

/**
 * To be thrown when test script error identified
 */
public class TestErrorException extends RuntimeException {

    public TestErrorException(final String message) {
        super(message);
    }

    public TestErrorException(final Throwable cause) {
        super(cause);
    }

    public TestErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TestErrorException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public TestErrorException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
