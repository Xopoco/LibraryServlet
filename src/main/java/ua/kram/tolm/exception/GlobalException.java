package ua.kram.tolm.exception;

public class GlobalException extends Exception {

    public GlobalException() {
    }
    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }
}
