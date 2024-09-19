package hr.tis.hackaton.sightseeingapp.exception;

public class TravelJournalNotFoundException extends RuntimeException {
    public TravelJournalNotFoundException(String message) {
        super(message);
    }

  public TravelJournalNotFoundException() {
  }
}
