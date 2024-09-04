package br.com.itau.emps.domain.services;

import br.com.itau.emps.core.entities.Eligibility;
import br.com.itau.emps.data.datasources.EligibilityDataSource;
import br.com.itau.emps.domain.interfaces.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final EligibilityDataSource eligibilityDataSource;

    public EligibilityServiceImpl(EligibilityDataSource eligibilityDataSource) {
        this.eligibilityDataSource = eligibilityDataSource;
    }

    @Override
    public Eligibility findByCnpj(String cnpj) {
        return eligibilityDataSource.findByCnpj(cnpj);
    }
}
