package br.com.itau.emps.data.datasources;

import br.com.itau.emps.core.entities.Eligibility;

public interface EligibilityDataSource {
    Eligibility findByCnpj(String cnpj);
}
