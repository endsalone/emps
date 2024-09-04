package br.com.itau.emps.presentation.validators;

import br.com.itau.emps.presentation.annotations.ValidCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<ValidCnpj, String> {
    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        var hasOnlyNumbers = cnpj.matches("\\d{14}");
        if (!hasOnlyNumbers) {
            return false;
        }
        var areAllDigitsEqual = cnpj.chars().distinct().count() == 1;

        if (areAllDigitsEqual) {
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            num = (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig13 = '0';
        else
            dig13 = (char) ((11 - r) + 48);

        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - r) + 48);
        }

        return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
    }
}
