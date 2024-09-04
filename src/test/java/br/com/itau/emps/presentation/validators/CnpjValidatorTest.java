package br.com.itau.emps.presentation.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class CnpjValidatorTest {

    @InjectMocks
    private CnpjValidator cnpjValidator;

    @BeforeEach
    public void setUp() {
        cnpjValidator = new CnpjValidator();
    }

    @Test
    public void shouldReturnTrueForValidCnpj() {
        String validCnpj = "12345678000195";
        boolean result = cnpjValidator.isValid(validCnpj, null);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseForInvalidCnpj() {
        String invalidCnpj = "12345678";
        boolean result = cnpjValidator.isValid(invalidCnpj, null);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForWhenCnpjIsSameChar() {
        boolean result = cnpjValidator.isValid("11111111111111", null);
        assertFalse(result);
    }
}
