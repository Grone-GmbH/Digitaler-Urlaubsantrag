package de.grone.urlaubsAntrag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Zentrale Fehlerbehandlung für alle Controller.
 * Wird in einem Controller oder Service eine Exception geworfen, fängt diese Klasse sie ab
 * und wandelt sie in eine einheitliche JSON-Antwort (ApiError) mit dem passenden HTTP-Statuscode um.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 400 - Eingabe ungültig: @Valid hat ein Feld abgelehnt (z. B. @NotNull, @Size).
     * Für jedes fehlerhafte Feld wird eine Meldung in die Detailliste geschrieben.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        // Jeden Feldfehler in einen Text "feld: meldung" umwandeln
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return build(HttpStatus.BAD_REQUEST, "Die Eingabe ist ungültig", details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class) //400 ungültiges JSON oder falsches Datum
    public ResponseEntity<ApiError> handleUnreadable(HttpMessageNotReadableException ex) {
        return build(HttpStatus.BAD_REQUEST,
                "Der Request body ist ungültig (z.B falsches Datumformat (yyyy-mm-dd)", List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class) //**400 - Ein Parameter in der URL hat falschen Typ
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return build(HttpStatus.BAD_REQUEST, "Ungültiger Wert für Parameter '" + ex.getName() + "'", List.of());
    }

    @ExceptionHandler(BadRequestException.class) //400
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());
    }

    /**
     * 401 - Anmeldung fehlgeschlagen (falscher Benutzername oder falsches Passwort).
     * Die Meldung ist absichtlich allgemein, damit niemand erfährt, welcher der beiden Werte falsch war.
     */
    @ExceptionHandler(AuthenticationException.class) //401
    public ResponseEntity<ApiError> handleAuthentication(AuthenticationException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Benutzername oder Passwort falsch", List.of());
    }

    @ExceptionHandler(AccessDeniedException.class) //** 403 - Der Benutzer ist angemeldet, hat aber keine Berechtigung
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return build(HttpStatus.FORBIDDEN, "Keine Berechtigung für diese Aktion", List.of());
    }

    @ExceptionHandler(ResourceNotFoundException.class) //404
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(ConflictException.class) //409
    public ResponseEntity<ApiError> handleConflict(ConflictException ex) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), List.of());
    }

    /** Hilfsmethode: baut aus Status, Meldung und Details die HTTP-Antwort mit dem ApiError-Body. */
    private ResponseEntity<ApiError> build(HttpStatus status, String message, List<String> details) {
        ApiError body = ApiError.of(status.value(), status.getReasonPhrase(), message, details);
        return ResponseEntity.status(status).body(body);
    }
}
