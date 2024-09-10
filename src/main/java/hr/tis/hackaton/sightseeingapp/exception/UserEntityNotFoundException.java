package hr.tis.hackaton.sightseeingapp.exception;

public class UserEntityNotFoundException extends RuntimeException {
  public UserEntityNotFoundException(String message) {
    super(message);
  }
  public UserEntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  public UserEntityNotFoundException() {
  }
}
