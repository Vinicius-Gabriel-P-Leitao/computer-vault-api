package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class MissingRequestHeaderExceptionHandler extends InternalServerExceptionHandler {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<OperationStatus> handleMissingHeaderException(MissingRequestHeaderException exception) {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), "Faltam informações no seu header: " + exception.getHeaderName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }
}
