package ua.kram.tolm.exception;

public class DBException extends GlobalException{
    private static final long serialVersionUID = -8204897453357084862L;

    public DBException() {
        super();
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
