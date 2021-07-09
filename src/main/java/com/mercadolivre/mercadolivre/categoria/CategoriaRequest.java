package com.mercadolivre.mercadolivre.categoria;

import com.mercadolivre.mercadolivre.validacao.ValidaCampoDuplicado;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    @ValidaCampoDuplicado(atributo = "nome", aClass = Categoria.class)
    private String nome;

    private Categoria categoriaMae;

    public CategoriaRequest(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public Categoria toCategoria(){
        return new Categoria(nome, categoriaMae);
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }
}
