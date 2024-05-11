package tech.vault.server.infra.exception.handlers;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.vault.server.infra.exception.message.OperationStatus;

@ControllerAdvice // NOTE: Quando o controller for executado passara por esse tratamento de erros
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private OperationStatus operationStatus;

    // NOTE: Internal server error
    private ResponseEntity<OperationStatus> internalServerError() {
        operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<OperationStatus> computerUUIInvalidArgument(MethodArgumentTypeMismatchException exception) {
        try {
            operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST, "Dados inseridos são inválidos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<OperationStatus> computerDataViolationHandler(DataIntegrityViolationException exception) {
        try {
            if (exception.getMessage().contains("duplicate key value violates unique constraint")) {
                operationStatus = new OperationStatus(HttpStatus.CONFLICT, "O valor inserido já existe no banco de dados!");
            } else {
                operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST, "Dados inseridos estão incorretos!");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<OperationStatus> handleMissingHeaderException(MissingRequestHeaderException exception) {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST, "Faltam informações no seu header: " + exception.getHeaderName());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }
}
