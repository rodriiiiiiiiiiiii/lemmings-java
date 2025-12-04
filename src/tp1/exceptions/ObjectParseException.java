package tp1.exceptions;

public class ObjectParseException extends GameParseException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectParseException(String message) {
        super(message);
    }

    public ObjectParseException(String message, Throwable cause) {
        super(message, cause);
    }
}