package tech.vault.server.infra.validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tech.vault.server.infra.exception.ExArgumentNotValid;
import tech.vault.server.infra.validation.Ipv4Tester;

import java.util.regex.Pattern;

public class Ipv4Validation implements ConstraintValidator<Ipv4Tester, String> {
    private static final String IPV4_PATTERN = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            throw new ExArgumentNotValid(constraintValidatorContext.getDefaultConstraintMessageTemplate());

        if (!pattern.matcher(value).matches())
            throw new ExArgumentNotValid(constraintValidatorContext.getDefaultConstraintMessageTemplate());
        return pattern.matcher(value).matches();
    }
}
