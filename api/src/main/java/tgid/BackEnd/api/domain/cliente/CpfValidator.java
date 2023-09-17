package tgid.BackEnd.api.domain.cliente;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public void initialize(ValidCPF constraintAnnotation) {
        // Lógica de inicialização, se necessário
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false; // CPF deve conter exatamente 11 dígitos numérico
        }

        int[] digits = new int[11]; // faz o hash do cpf
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
            sum2 += digits[i] * (11 - i);
        }

        int remainder1 = sum1 % 11;
        int remainder2 = sum2 % 11;

        if (remainder1 < 2) {
            if (digits[9] != 0) {
                return false;
            }
        } else {
            if (digits[9] != 11 - remainder1) {
                return false;
            }
        }

        if (remainder2 < 2) {
            if (digits[10] != 0) {
                return false;
            }
        } else {
            if (digits[10] != 11 - remainder2) {
                return false;
            }
        }

        return true; // CPF válido
    }
}