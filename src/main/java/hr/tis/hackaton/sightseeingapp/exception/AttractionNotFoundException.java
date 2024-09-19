package hr.tis.hackaton.sightseeingapp.exception;

public class AttractionNotFoundException extends RuntimeException {
    public AttractionNotFoundException(String message) {
        super(message);
    }

  public AttractionNotFoundException() {
  }
}
