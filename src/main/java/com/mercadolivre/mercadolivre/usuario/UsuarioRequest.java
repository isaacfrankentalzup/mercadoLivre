package com.mercadolivre.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    //@ValidaCampoDuplicado(atributo = "email", aClass = Usuario.class)
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String email,
                          @NotBlank @Size(min = 6) String senha) {

        this.email = email;
        this.senha = senha;

    }

    public Usuario toUsuario(){
        return new Usuario(this.email, new SenhaLimpa(senha));
    }

}
