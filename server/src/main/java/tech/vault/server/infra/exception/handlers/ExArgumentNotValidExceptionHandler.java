package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.ExArgumentNotValid;
import tech.vault.server.infra.exception.message.OperationStatus;

/**
 * Manipulador de exceções para argumentos inválidos.
 *
 * <p>Esta classe usa {@link ControllerAdvice} para capturar exceções {@link ExArgumentNotValid}
 * e retornar respostas HTTP apropriadas.</p>
 */
@ControllerAdvice
public class ExArgumentNotValidExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de argumentos inválidos.
     *
     * @param exception A exceção {@link ExArgumentNotValid} lançada.
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
    @ExceptionHandler(ExArgumentNotValid.class)
    public ResponseEntity<OperationStatus> handleArgumentNotValid(ExArgumentNotValid exception) {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }
}
