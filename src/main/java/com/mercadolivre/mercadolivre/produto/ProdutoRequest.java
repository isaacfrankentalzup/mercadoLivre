package com.mercadolivre.mercadolivre.produto;

import com.mercadolivre.mercadolivre.categoria.Categoria;
import com.mercadolivre.mercadolivre.usuario.Usuario;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @PositiveOrZero
    @NotNull
    private Integer quantidade;
    @NotBlank @Size(max = 1000)
    private String descricao;

    private LocalDateTime creatAt = LocalDateTime.now();

    @NotNull
    private Categoria categoria;

    private Usuario usuario;


    private List<CaracteristicaRequest> caracteristicaRequestList = new ArrayList<>();

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
                          Categoria categoria,
                          List<CaracteristicaRequest> caracteristicaRequestList,
                          Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicaRequestList = caracteristicaRequestList;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<CaracteristicaRequest> getCaracteristicaRequestList() {
        return caracteristicaRequestList;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public Produto toProduto (Usuario usuario){
        return new Produto(nome,valor,quantidade,descricao,
        categoria,caracteristicaRequestList, usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", creatAt=" + creatAt +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", caracteristicaRequestList=" + caracteristicaRequestList +
                '}';
    }
}
