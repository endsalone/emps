package br.com.itau.emps.domain.dtos;

import org.springframework.hateoas.RepresentationModel;

public class EligibilityResponseDTO extends RepresentationModel<EligibilityResponseDTO> {

    private final String cnpj;
    private final boolean isEligible;

    public EligibilityResponseDTO(String cnpj, boolean isEligible) {
        this.cnpj = cnpj;
        this.isEligible = isEligible;
    }

    public String getCnpj() {
        return cnpj;
    }

    public boolean isEligible() {
        return isEligible;
    }
}
