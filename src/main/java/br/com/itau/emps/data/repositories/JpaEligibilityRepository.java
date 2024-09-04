package br.com.itau.emps.data.repositories;

import br.com.itau.emps.core.entities.Eligibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEligibilityRepository extends JpaRepository<Eligibility, String> {
    Eligibility findByCnpj(String cnpj);
}
