package edu.pattern.management.exception;

/**
 * Custom exception class is used for inner processing.
 */
public class TaskException extends Exception {
    /**
     * Default constructor
     */
    public TaskException() {
    }

    /**
     * Constructor with possibility to add an Exception.
     *
     * @param cause exception (e)
     */
    public TaskException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with possibility to add an Exception and custom message for it.
     *
     * @param message custom message
     * @param cause   exception (e)
     */
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with possibility to add the message.
     *
     * @param message custom message
     */
    public TaskException(String message) {
        super(message);
    }
}
