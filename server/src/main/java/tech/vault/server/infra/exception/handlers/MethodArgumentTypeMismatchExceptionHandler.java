package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class MethodArgumentTypeMismatchExceptionHandler extends InternalServerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<OperationStatus> handleInvalidArgument(MethodArgumentTypeMismatchException exception) {
        try {
            OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), "Dados inseridos são inválidos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }
}
