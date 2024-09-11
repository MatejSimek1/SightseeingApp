package hr.tis.hackaton.sightseeingapp.configuration;

import hr.tis.hackaton.sightseeingapp.dto.ExceptionMessageWrapper;
import hr.tis.hackaton.sightseeingapp.exception.NoAttractionFoundException;
import hr.tis.hackaton.sightseeingapp.exception.NoPictureFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
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

    @ExceptionHandler(NoAttractionFoundException.class)
    public final ResponseEntity<ExceptionMessageWrapper> handleNoProductFoundException(NoAttractionFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionMessageWrapper(exception.getMessage()));
    }

    @ExceptionHandler(NoPictureFoundException.class)
    public final ResponseEntity<ExceptionMessageWrapper> handleNoProductFoundException(NoPictureFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionMessageWrapper(exception.getMessage()));
    }

}
