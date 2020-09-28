package ua.kram.tolm.exception;

public class GlobalException extends Exception {
    private static final long serialVersionUID = 4204606000601601093L;

    public GlobalException() {
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }
}
