package tp1.exceptions;

/**
 * Base exception for all command-related errors.
 */
public class CommandException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a CommandException with a specific message.
     *
     * @param message The detail message.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a CommandException with a specific message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
