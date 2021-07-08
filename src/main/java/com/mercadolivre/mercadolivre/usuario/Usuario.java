package com.mercadolivre.mercadolivre.usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Usuario(){
    }

    public Usuario(String email, SenhaLimpa senhaLimpa) {
        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
