package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vault.server.infra.exception.ExNotFound;
import tech.vault.server.infra.exception.message.OperationStatus;

/**
 * Manipulador de exceções para recursos não encontrados.
 *
 * <p>Esta classe usa {@link ControllerAdvice} para capturar exceções {@link ExNotFound}
 * e retornar respostas HTTP apropriadas.</p>
 */
@ControllerAdvice
public class ExNotFoundExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de recursos não encontrados.
     *
     * @param exception A exceção {@link ExNotFound} lançada.
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
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
