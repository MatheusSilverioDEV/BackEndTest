package tgid.BackEnd.api.domain.empresa;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<ValidCNPJ, String> {
    @Override
    public void initialize(ValidCNPJ constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) { //CNPJ deve conter 14 digitos
            return false;
        }

        // Hash de cpf

        int[] digits = new int[14];
        for (int i = 0; i < 14; i++) {
            digits[i] = Integer.parseInt(cnpj.substring(i, i + 1));
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 12; i++) {
            sum1 += digits[i] * weights1[i];
            sum2 += digits[i] * weights2[i];
        }

        int mod1 = sum1 % 11;
        int mod2 = sum2 % 11;

        if (mod1 < 2) {
            mod1 = 0;
        } else {
            mod1 = 11 - mod1;
        }

        if (mod2 < 2) {
            mod2 = 0;
        } else {
            mod2 = 11 - mod2;
        }

        return digits[12] == mod1 && digits[13] == mod2;
    }
}
