package org.youcode.citronix.common.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.youcode.citronix.common.validation.validator.ExistsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {
    String message() default "The specified ID does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entityClass();
}
