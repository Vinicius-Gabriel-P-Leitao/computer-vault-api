package tech.vault.server.infra.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Ipv4Validation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ipv4Tester {
    String message() default "IP invalido";
}
