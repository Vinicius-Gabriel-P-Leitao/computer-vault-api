package tech.vault.server.infra.exception;

import lombok.Getter;

@Getter
public class ExJwtExpired extends RuntimeException {
    private final String jwtExpired;

    public ExJwtExpired(String jwtExpired) {
        super("Required request header '" + jwtExpired + "' is not present");
        this.jwtExpired = jwtExpired;
    }

}
