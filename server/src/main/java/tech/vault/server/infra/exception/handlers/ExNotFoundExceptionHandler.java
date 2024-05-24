package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.ExNotFound;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class ExNotFoundExceptionHandler extends InternalServerExceptionHandler {

    @ExceptionHandler(ExNotFound.class)
    public ResponseEntity<OperationStatus> handleNotFound(ExNotFound exception) {
        try {
            OperationStatus operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }
}
