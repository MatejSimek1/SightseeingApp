package hr.tis.hackaton.sightseeingapp.configuration;

import hr.tis.hackaton.sightseeingapp.dto.ExceptionMessageWrapper;
import hr.tis.hackaton.sightseeingapp.exception.AttractionNotFoundException;
import hr.tis.hackaton.sightseeingapp.exception.TravelJournalNotFoundException;
import hr.tis.hackaton.sightseeingapp.exception.UserEntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAnyException(Exception exception) {
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Unhandled exception uuid: '{}'", uuid, exception);
        String errorMessage = String.format("An unexpected error occurred. Reference ID: %s", uuid);
        return ResponseEntity.internalServerError().body(errorMessage);
    }

    @ExceptionHandler(AttractionNotFoundException.class)
    public final ResponseEntity<ExceptionMessageWrapper> handleNoProductFoundException(AttractionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessageWrapper(exception.getMessage()));
    }

    @ExceptionHandler(TravelJournalNotFoundException.class)
    public final ResponseEntity<ExceptionMessageWrapper> handleNoProductFoundException(TravelJournalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessageWrapper(exception.getMessage()));
    }

    @ExceptionHandler(UserEntityNotFoundException.class)
    public final ResponseEntity<ExceptionMessageWrapper> handleNoProductFoundException(UserEntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessageWrapper(exception.getMessage()));
    }

}
