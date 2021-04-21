package com.pet.project.exceptions;

/**
 * To be thrown when something fails in the core framework (Web driver setup etc.)
 */
public class TestFrameworkCoreException extends RuntimeException {

    public TestFrameworkCoreException(final String message) {
        super(message);
    }

    public TestFrameworkCoreException(final Throwable cause) {
        super(cause);
    }

    public TestFrameworkCoreException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TestFrameworkCoreException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public TestFrameworkCoreException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}
