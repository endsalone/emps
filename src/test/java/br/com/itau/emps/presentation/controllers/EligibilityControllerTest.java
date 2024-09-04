package br.com.itau.emps.presentation.controllers;

import br.com.itau.emps.core.entities.Eligibility;
import br.com.itau.emps.core.usecases.CheckEligibilityUseCase;
import br.com.itau.emps.domain.dtos.EligibilityResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class EligibilityControllerTest {

    @InjectMocks
    private EligibilityController eligibilityController;

    @Mock
    private CheckEligibilityUseCase checkEligibilityUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEligibleResponseWhenCnpjIsEligible() {
        String cnpj = "12345678000195";
        Eligibility eligibility = new Eligibility(cnpj, true);
        when(checkEligibilityUseCase.check(cnpj)).thenReturn(eligibility);

        ResponseEntity<EntityModel<EligibilityResponseDTO>> response = eligibilityController.checkEligibility(cnpj);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getContent().isEligible());
        assertEquals(cnpj, response.getBody().getContent().getCnpj());

        Link expectedLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EligibilityController.class).checkEligibility(cnpj)).withSelfRel();
        assertEquals(expectedLink.getHref(), response.getBody().getLinks().getRequiredLink("self").getHref());
    }

    @Test
    public void shouldReturnNotEligibleResponseWhenCnpjIsNotEligible() {
        String cnpj = "98765432000185";
        Eligibility eligibility = new Eligibility(cnpj, false);
        when(checkEligibilityUseCase.check(cnpj)).thenReturn(eligibility);

        ResponseEntity<EntityModel<EligibilityResponseDTO>> response = eligibilityController.checkEligibility(cnpj);

        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().getContent().isEligible());
        assertEquals(cnpj, response.getBody().getContent().getCnpj());

        Link expectedLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EligibilityController.class).checkEligibility(cnpj)).withSelfRel();
        assertEquals(expectedLink.getHref(), response.getBody().getLinks().getRequiredLink("self").getHref());
    }
}