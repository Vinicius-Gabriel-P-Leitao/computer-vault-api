package tech.vault.server.infra.exception.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice
public class DataIntegrityViolationExceptionHandler extends InternalServerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<OperationStatus> handleDataViolationHandler(DataIntegrityViolationException exception) {
        try {
            OperationStatus operationStatus;
            if (exception.getMessage().contains("duplicate key value violates unique constraint")) {
                operationStatus = new OperationStatus(HttpStatus.CONFLICT.value(), "O valor inserido já existe no banco de dados!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(operationStatus);
            } else {
                operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), "Dados inseridos estão incorretos!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
            }

        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }
}
