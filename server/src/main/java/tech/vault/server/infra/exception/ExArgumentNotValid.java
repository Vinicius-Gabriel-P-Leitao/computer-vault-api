package tech.vault.server.infra.exception;

import lombok.Getter;

@Getter
public class ExArgumentNotValid extends RuntimeException {
    public ExArgumentNotValid(String meString) {
        super(meString);
    }
}
