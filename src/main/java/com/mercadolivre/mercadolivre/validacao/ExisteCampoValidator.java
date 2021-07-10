package com.mercadolivre.mercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteCampoValidator implements ConstraintValidator<ExisteCampo, Object> {

    private String atributo;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager manager;

    public void initialize(ExisteCampo constraint) {

        this.atributo = constraint.atributo();
        this.aClass = constraint.aClass();

    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        Query query = manager.createQuery(
                "SELECT u FROM " + aClass.getName() + " u WHERE u."+atributo + "= :pValor");

        query.setParameter("pValor",obj);

        return !(query.getResultList().isEmpty());


    }

}
