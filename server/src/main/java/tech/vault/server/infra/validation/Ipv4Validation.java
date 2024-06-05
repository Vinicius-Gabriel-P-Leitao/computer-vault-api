package tech.vault.server.infra.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class Ipv4Validation implements ConstraintValidator<Ipv4Tester, String> {
    private static final String IPV4_PATTERN = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || !pattern.matcher(value).matches()) {
            // Add constraint violation message
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Endereço IP inválido")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

