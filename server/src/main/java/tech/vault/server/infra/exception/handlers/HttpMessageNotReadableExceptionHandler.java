package tech.vault.server.infra.exception.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador de exceções para mensagens HTTP formatados incorretamente.
 *
 * <p>Esta classe estende {@link ResponseEntityExceptionHandler} para capturar exceções {@link HttpMessageNotReadableException}
 * e retornar respostas HTTP apropriadas.</p>
 */
@ControllerAdvice
public class HttpMessageNotReadableExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Manipula exceções de mensagens HTTP formatados incorretamente.
     *
     * @param exception A exceção {@link HttpMessageNotReadableException} lançada.
     * @param headers   Os cabeçalhos HTTP.
     * @param status    O status HTTP.
     * @param request   O pedido web.
     * @return Um {@link ResponseEntity} contendo um corpo com a mensagem de erro e o status HTTP apropriado.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", "Certifique-se de que o JSON esteja bem formatado");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, headers, status);
    }
}
