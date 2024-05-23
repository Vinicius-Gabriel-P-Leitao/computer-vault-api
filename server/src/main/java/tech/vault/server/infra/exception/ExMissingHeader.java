package tech.vault.server.infra.exception;

import lombok.Getter;

@Getter
public class ExMissingHeader extends RuntimeException {
    private final String headerName;

    public ExMissingHeader(String headerName) {
        super("Required request header '" + headerName + "' is not present");
        this.headerName = headerName;
    }
}
