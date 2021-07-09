package com.mercadolivre.mercadolivre.usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String perfil;

    public Perfil() {
    }

    @JsonCreator
    public Perfil(@JsonProperty("perfil") String perfil) {
        this.perfil = perfil;
    }

    public Long getId() { return id; }

    public String getPerfil() { return perfil; }

    @Override
    public String getAuthority() {
        return this.perfil;
    }
}
