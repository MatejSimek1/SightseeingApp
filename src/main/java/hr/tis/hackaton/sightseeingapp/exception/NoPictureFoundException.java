package hr.tis.hackaton.sightseeingapp.exception;

public class NoPictureFoundException extends RuntimeException {
    public NoPictureFoundException(String message) {
        super(message);
    }

    public NoPictureFoundException() {
    }
}
