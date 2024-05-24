package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.ExArgumentNotValid;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class ExArgumentNotValidExceptionHandler extends InternalServerExceptionHandler {

    @ExceptionHandler(ExArgumentNotValid.class)
    public ResponseEntity<OperationStatus> handleArgumentNotValid(ExArgumentNotValid exception) {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }
}
