package com.mercadolivre.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();




    @Deprecated
    public Usuario(){
    }

    public Usuario(String email, SenhaLimpa senhaLimpa) {
        //Assert.hasLength(email,"Email nao pode ser em branco");
        //Assert.notNull(senhaLimpa,"A senha não pode ser em branco");

        this.email = email;
        this.senha = senhaLimpa.hash();

        //Assert.isTrue(verificaEntradas(),"Campos não podem ser nulos");
    }

    //public boolean verificaEntradas(){
    //    return this.email != null || this.senha != null;
    //}

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
    }  // ????????????????????????

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() { return this.senha; }

    @Override
    public String getUsername() { return this.email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
