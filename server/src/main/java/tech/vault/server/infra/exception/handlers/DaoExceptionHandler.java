package tech.vault.server.infra.exception.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.ExNotFound;
import tech.vault.server.infra.exception.message.OperationStatus;


@ControllerAdvice
public class DaoExceptionHandler {
    private OperationStatus operationStatus;

    // NOTE: Internal server error
    private ResponseEntity<OperationStatus> internalServerError() {
        operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
    }

    @ExceptionHandler(ExNotFound.class)
    private ResponseEntity<OperationStatus> computerUUIdNotFound(ExNotFound exception) {
        try {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND, exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }
}