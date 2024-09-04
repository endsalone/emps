package br.com.itau.emps.presentation.annotations;

import br.com.itau.emps.presentation.validators.CnpjValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CnpjValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCnpj {

    String message() default "Invalid CNPJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}