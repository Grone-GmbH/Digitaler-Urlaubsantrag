package de.grone.urlaubsAntrag.exception;

import java.time.Instant;
import java.util.List;

public record ApiError(String timestamp, int status, String error, String message, List<String> details){

    public static ApiError of(int status, String error, String message, List<String> details) {
        return new ApiError(Instant.now().toString(), status, error, message, details);
    }
}
