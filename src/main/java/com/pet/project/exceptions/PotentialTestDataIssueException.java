package com.pet.project.exceptions;

/**
 * To be thrown when test script error identified
 */
public class PotentialTestDataIssueException extends RuntimeException {

    public PotentialTestDataIssueException(final String message) {
        super(message);
    }

    public PotentialTestDataIssueException(final Throwable cause) {
        super(cause);
    }

    public PotentialTestDataIssueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PotentialTestDataIssueException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public PotentialTestDataIssueException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
