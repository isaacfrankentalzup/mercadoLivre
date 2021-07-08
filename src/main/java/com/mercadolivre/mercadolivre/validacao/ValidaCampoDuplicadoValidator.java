package com.mercadolivre.mercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaCampoDuplicadoValidator implements ConstraintValidator<ValidaCampoDuplicado, Object> {

    private String campo;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ValidaCampoDuplicado parametros) {
        this.campo = parametros.atributo();
        this.aClass = parametros.aClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery(
                "SELECT r FROM " + aClass.getName() + " r WHERE r." + campo + "= :pValor")
                .setParameter("pValor", value);

        return query.getResultList().isEmpty();

    }
 }
