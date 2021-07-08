package com.mercadolivre.mercadolivre.usuario;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {

        Assert.hasLength(senha, "A senha não poode ser nula");
        Assert.isTrue(senha.length()>=6, "Senha ter que ter pelo menos 6 caracteres");

        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }

}
