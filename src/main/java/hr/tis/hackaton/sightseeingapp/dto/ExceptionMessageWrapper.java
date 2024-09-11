package hr.tis.hackaton.sightseeingapp.dto;

import java.time.LocalDateTime;

public record ExceptionMessageWrapper(
        String message,
        LocalDateTime timestamp
) {
    public ExceptionMessageWrapper(String message) {
        this(message, LocalDateTime.now());
    }
}
