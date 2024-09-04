package br.com.itau.emps.core.usecases;

import br.com.itau.emps.domain.interfaces.EligibilityService;
import br.com.itau.emps.core.entities.Eligibility;
import org.springframework.stereotype.Service;

@Service
public class CheckEligibilityUseCase {

    private final EligibilityService eligibilityService;

    public CheckEligibilityUseCase(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    public Eligibility check(String cnpj) {
        return eligibilityService.findByCnpj(cnpj);
    }
}
