package exceptions;

public class OberonException extends Exception {
    public OberonException(String message) {
        super(message);
    }

    public OberonException() {
        this("Oberon Exception");
    }
}
