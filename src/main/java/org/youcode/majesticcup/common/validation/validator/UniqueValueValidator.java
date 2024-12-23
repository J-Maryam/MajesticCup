package org.youcode.citronix.common.validation.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.youcode.citronix.common.validation.annotation.UniqueValue;

@Component
@RequiredArgsConstructor
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    private final EntityManager em;
    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (fieldName == null) return true;
        String queryString = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :fieldName",
                entityClass.getSimpleName(),
                fieldName);

        TypedQuery<Long> query = em.createQuery(queryString, Long.class);
        query.setParameter("fieldName", fieldName);

        Long count = query.getSingleResult();
        return count == 0;
    }
}
