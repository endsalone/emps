package br.com.itau.emps.data.repositories;

import br.com.itau.emps.core.entities.Eligibility;
import br.com.itau.emps.data.datasources.EligibilityDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class EligibilityRepository implements EligibilityDataSource {

    private final JpaEligibilityRepository jpaEligibilityRepository;

    public EligibilityRepository(JpaEligibilityRepository jpaEligibilityRepository) {
        this.jpaEligibilityRepository = jpaEligibilityRepository;
    }

    @Override
    public Eligibility findByCnpj(String cnpj) {
        return jpaEligibilityRepository.findByCnpj(cnpj);
    }
}
