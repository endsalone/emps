package br.com.itau.emps.presentation.exceptions;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class GlobalExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(HandlerMethodValidationException ex) {

        Map<String, String> errorMap = new HashMap<>();
        List<ParameterValidationResult> errors = ex.getAllValidationResults();

        for (ParameterValidationResult result : errors) {
            errorMap.put(result.getMethodParameter().getParameterName(), result.getResolvableErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(errorMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
