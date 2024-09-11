package hr.tis.hackaton.sightseeingapp.exception;

public class NoAttractionFoundException extends RuntimeException {
    public NoAttractionFoundException(String message) {
        super(message);
    }

  public NoAttractionFoundException() {
  }
}
