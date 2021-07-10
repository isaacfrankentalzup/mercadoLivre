package com.mercadolivre.mercadolivre.produto.pergunta;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String pergunta;

    private LocalDateTime createAt = LocalDateTime.now(); //default

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    @Deprecated //hibernate
    public Pergunta() {
    }

    public Pergunta(String titulo, String pergunta, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
