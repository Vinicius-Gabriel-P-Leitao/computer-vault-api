package tech.vault.server.infra.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validador para endereços IPv4.
 *
 * <p>Esta classe implementa a interface {@link ConstraintValidator} para validar se uma string é um endereço IPv4 válido,
 * utilizando uma expressão regular.</p>
 */
public class Ipv4Validation implements ConstraintValidator<Ipv4Tester, String> {
    private static final String IPV4_PATTERN = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    /**
     * Verifica se a string fornecida é um endereço IPv4 válido.
     *
     * @param value                      O valor que será validado.
     * @param constraintValidatorContext O contexto do validador, utilizado para criar mensagens de erro personalizadas.
     * @return {@code true} se o valor for um endereço IPv4 válido, caso contrário {@code false}.
     * Se o valor for inválido, adiciona uma mensagem de violação de restrição ao contexto do validador.
     */
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

