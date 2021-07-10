package com.mercadolivre.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExisteCampoValidator.class)
public @interface ExisteCampo {
    String message() default "Campo Não Existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String atributo();
    Class<?> aClass();

}
