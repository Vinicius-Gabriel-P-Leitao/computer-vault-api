package tech.vault.server.infra.exception.handlers;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class JwtExceptionHandler {
    private OperationStatus operationStatus;

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<OperationStatus> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        operationStatus = new OperationStatus(HttpStatus.FORBIDDEN, "Token JWT inválido: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(operationStatus);
    }
}
