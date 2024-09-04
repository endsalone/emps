package br.com.itau.emps.domain.interfaces;

import br.com.itau.emps.core.entities.Eligibility;

public interface EligibilityService {
    Eligibility findByCnpj(String cnpj);
}
