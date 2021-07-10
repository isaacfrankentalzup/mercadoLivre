package com.mercadolivre.mercadolivre.produto.opiniao;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

public class OpiniaoRequest {
    @Min(1) @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    @NotNull
    @Valid
    //@ConvertGroup(from = Default.class, to = Groups.Usuario.class)
    private Usuario usuario;

    @NotNull
    @Valid
    //@ConvertGroup(from = Default.class, to = Groups.Produto.class)
    private Produto produto;

    public OpiniaoRequest(Integer nota, String titulo, String descricao,
                          Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public Opiniao toOpiniao(){
        return new Opiniao(nota,titulo,descricao,usuario,produto);
    }
}
