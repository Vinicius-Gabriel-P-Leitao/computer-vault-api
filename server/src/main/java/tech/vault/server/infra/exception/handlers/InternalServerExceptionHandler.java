package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import tech.vault.server.infra.exception.message.OperationStatus;

/**
 * Manipulador de exceções para erros internos do servidor.
 *
 * <p>Esta classe usa {@link ControllerAdvice} para fornecer um método padrão para retornar respostas HTTP apropriadas
 * quando ocorrem erros internos do servidor.</p>
 */
@ControllerAdvice
public class InternalServerExceptionHandler {

    /**
     * Retorna uma resposta HTTP para erros internos do servidor.
     *
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
    protected ResponseEntity<OperationStatus> internalServerError() {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
    }
}
