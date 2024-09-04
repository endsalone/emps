package br.com.itau.emps.core.usecases;

import br.com.itau.emps.core.entities.Eligibility;
import br.com.itau.emps.domain.interfaces.EligibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckEligibilityUseCaseTest {
    @InjectMocks
    private CheckEligibilityUseCase checkEligibilityUseCase;

    @Mock
    private EligibilityService eligibilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEligibleWhenCnpjIsEligible() {
        String cnpj = "12345678000195";
        Eligibility eligibility = new Eligibility(cnpj, true);

        when(eligibilityService.findByCnpj(cnpj)).thenReturn(eligibility);

        Eligibility result = checkEligibilityUseCase.check(cnpj);

        assertTrue(result.isEligible());
        assertEquals(cnpj, result.getCnpj());
    }

    @Test
    public void shouldReturnNotEligibleWhenCnpjIsNotEligible() {
        String cnpj = "98765432000185";
        Eligibility eligibility = new Eligibility(cnpj, false);

        when(eligibilityService.findByCnpj(cnpj)).thenReturn(eligibility);

        Eligibility result = checkEligibilityUseCase.check(cnpj);

        assertTrue(result.isEligible());
        assertEquals(cnpj, result.getCnpj());
    }
}
