package br.com.itau.emps.presentation.controllers;

import br.com.itau.emps.core.usecases.CheckEligibilityUseCase;
import br.com.itau.emps.domain.dtos.EligibilityResponseDTO;
import br.com.itau.emps.presentation.annotations.ValidCnpj;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/eligibility")
public class EligibilityController {

    private final CheckEligibilityUseCase checkEligibilityUseCase;

    public EligibilityController(CheckEligibilityUseCase checkEligibilityUseCase) {
        this.checkEligibilityUseCase = checkEligibilityUseCase;
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<EntityModel<EligibilityResponseDTO>> checkEligibility(
            @Valid @ValidCnpj @PathVariable String cnpj
    ) {
        var eligibility = checkEligibilityUseCase.check(cnpj);

        Link checkElegibilityLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(EligibilityController.class)
                        .checkEligibility(cnpj)
        ).withSelfRel();

        var responseDTO = new EligibilityResponseDTO(eligibility.getCnpj(), eligibility.isEligible());
        var resource = EntityModel.of(responseDTO);
        resource.add(checkElegibilityLink);

        return ResponseEntity.ok(resource);
    }
}
