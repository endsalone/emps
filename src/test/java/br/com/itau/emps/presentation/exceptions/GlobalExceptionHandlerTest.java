package br.com.itau.emps.presentation.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void shouldHandleValidationErrors() {
        HandlerMethodValidationException exception = mock(HandlerMethodValidationException.class);
        ParameterValidationResult validationResult = mock(ParameterValidationResult.class);
        MethodParameter methodParameter = mock(MethodParameter.class);

        when(validationResult.getMethodParameter()).thenReturn(methodParameter);
        when(validationResult.getMethodParameter().getParameterName()).thenReturn("cnpj");
        when(validationResult.getResolvableErrors()).thenReturn(List.of(new ObjectError("cnpj", "Invalid CNPJ")));
        when(exception.getAllValidationResults()).thenReturn(Collections.singletonList(validationResult));

        ResponseEntity<Map<String, String>> responseEntity = globalExceptionHandler.handleValidationErrors(exception);

        Map<String, String> expectedErrorMap = new HashMap<>();
        expectedErrorMap.put("cnpj", "Invalid CNPJ");

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrorMap, responseEntity.getBody());
    }
}
