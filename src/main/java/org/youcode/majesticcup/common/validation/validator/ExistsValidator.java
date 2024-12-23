package org.youcode.citronix.common.validation.validator;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.youcode.citronix.common.validation.annotation.Exists;

@Component
@RequiredArgsConstructor
public class ExistsValidator implements ConstraintValidator<Exists, Long> {

    private final EntityManager em;
    private Class<?> entityClass;

    @Override
    public void initialize(Exists constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id == null) return true;

        String queryString = String.format("SELECT COUNT(e) FROM %s e WHERE e.id = :id",
                entityClass.getSimpleName());

        Long count = em.createQuery(queryString, Long.class)
                .setParameter("id", id)
                .getSingleResult();

        return count > 0;
    }
}
