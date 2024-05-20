package tech.vault.server.infra.exception;

import lombok.Getter;

@Getter
public class ExNotFound extends RuntimeException {
    public ExNotFound(String meString) {
        super(meString);
    }
}