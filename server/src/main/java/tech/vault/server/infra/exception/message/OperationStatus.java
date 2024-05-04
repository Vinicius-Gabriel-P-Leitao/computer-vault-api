package tech.vault.server.infra.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class OperationStatus {
    private HttpStatus httpStatus;
    private String httpMessage;
}