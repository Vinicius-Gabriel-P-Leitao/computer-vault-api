package tech.vault.server.infra.exception.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class OperationStatus {
    @JsonProperty("status")
    private HttpStatus httpStatus;

    @JsonProperty("message")
    private String httpMessage;
}