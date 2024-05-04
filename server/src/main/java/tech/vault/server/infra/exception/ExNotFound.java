package tech.vault.server.infra.exception;

public class ExNotFound extends RuntimeException {
    public ExNotFound(String meString) {
        super(meString);
    }
}