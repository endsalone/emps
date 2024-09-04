package br.com.itau.emps.core.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eligibility")
public class Eligibility {

    @Id
    private String cnpj;
    private boolean isEligible;


    public Eligibility(String cnpj, boolean isEligible) {
        this.cnpj = cnpj;
        this.isEligible = isEligible;
    }

    public Eligibility() {}

    public String getCnpj() {
        return cnpj;
    }

    public boolean isEligible() {
        return isEligible;
    }
}
