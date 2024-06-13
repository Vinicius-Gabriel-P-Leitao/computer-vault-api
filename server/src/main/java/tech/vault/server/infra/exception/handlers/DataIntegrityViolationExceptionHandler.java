package tech.vault.server.infra.exception.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.message.OperationStatus;

/**
 * Manipulador de exceções para violações de integridade de dados.
 *
 * <p>Esta classe usa {@link ControllerAdvice} para capturar exceções {@link DataIntegrityViolationException}
 * e retornar respostas HTTP apropriadas.</p>
 */
@ControllerAdvice
public class DataIntegrityViolationExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de violação de integridade de dados.
     *
     * @param exception A exceção {@link DataIntegrityViolationException} lançada.
     * @return Um {@link ResponseEntity} contendo um objeto {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
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
