package com.mercadolivre.mercadolivre.autenticacao;

import com.mercadolivre.mercadolivre.usuario.SenhaLimpa;
import com.mercadolivre.mercadolivre.usuario.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public Usuario toUsuario(){ return new Usuario(email, new SenhaLimpa(senha)); }
}
