package tech.vault.server.infra.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tech.vault.server.infra.validation.constraint.Ipv4Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Ipv4Validation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ipv4Tester {
    String message() default "IP invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
