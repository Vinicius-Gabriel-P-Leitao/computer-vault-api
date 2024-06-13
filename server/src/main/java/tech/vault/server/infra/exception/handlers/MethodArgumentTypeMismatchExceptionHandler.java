package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tech.vault.server.infra.exception.message.OperationStatus;

/**
 * Manipulador de exceções para tipos de argumentos de método inválidos.
 *
 * <p>Esta classe usa {@link ControllerAdvice} para capturar exceções {@link MethodArgumentTypeMismatchException}
 * e retornar respostas HTTP apropriadas.</p>
 */
@ControllerAdvice
public class MethodArgumentTypeMismatchExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de tipos de argumentos de método inválidos.
     *
     * @param exception A exceção {@link MethodArgumentTypeMismatchException} lançada.
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
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
