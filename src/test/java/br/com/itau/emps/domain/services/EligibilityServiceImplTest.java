package br.com.itau.emps.domain.services;

import br.com.itau.emps.core.entities.Eligibility;
import br.com.itau.emps.data.repositories.EligibilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class EligibilityServiceImplTest {

    @InjectMocks
    private EligibilityServiceImpl eligibilityServiceImpl;

    @Mock
    private EligibilityRepository eligibilityRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEligibleWhenCnpjIsEligible() {
        String cnpj = "12345678000195";
        Eligibility eligibility = new Eligibility(cnpj, true);

        when(eligibilityRepository.findByCnpj(cnpj)).thenReturn(eligibility);

        Eligibility result = eligibilityServiceImpl.findByCnpj(cnpj);

        assertTrue(result.isEligible());
        assertEquals(cnpj, result.getCnpj());
    }

    @Test
    public void shouldReturnNotEligibleWhenCnpjIsNotEligible() {
        String cnpj = "98765432000185";
        Eligibility eligibility = new Eligibility(cnpj, false);

        when(eligibilityRepository.findByCnpj(cnpj)).thenReturn(eligibility);

        Eligibility result = eligibilityServiceImpl.findByCnpj(cnpj);

        assertFalse(result.isEligible());
        assertEquals(cnpj, result.getCnpj());
    }
}
