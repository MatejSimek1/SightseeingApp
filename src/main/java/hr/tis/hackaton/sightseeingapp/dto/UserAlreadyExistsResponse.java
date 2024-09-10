package hr.tis.hackaton.sightseeingapp.dto;

import java.time.LocalDateTime;

public record UserAlreadyExistsResponse(
        String message,
        LocalDateTime timestamp
) {
}
