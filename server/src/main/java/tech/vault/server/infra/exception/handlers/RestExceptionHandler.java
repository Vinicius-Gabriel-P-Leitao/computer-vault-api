package tech.vault.server.infra.exception.handlers;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.vault.server.infra.exception.ExArgumentNotValid;
import tech.vault.server.infra.exception.message.OperationStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
        operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST, "Faltam informações no seu header: " + exception.getHeaderName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }

    @ExceptionHandler(ExArgumentNotValid.class)
    public ResponseEntity<OperationStatus> handleArgumentNotValid(ExArgumentNotValid exception) {
        operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("status", status.value());
        body.put("error", "O JSON está mal formado");
        body.put("message", exception.getCause());

        return new ResponseEntity<>(body, headers, status);
    }
}
