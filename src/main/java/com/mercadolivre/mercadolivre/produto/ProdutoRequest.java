package com.mercadolivre.mercadolivre.produto;

import com.mercadolivre.mercadolivre.categoria.Categoria;

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


    private List<CaracteristicaRequest> caracteristicaRequestList = new ArrayList<>();

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
                          Categoria categoria,
                          List<CaracteristicaRequest> caracteristicaRequestList) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicaRequestList = caracteristicaRequestList;
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

    public Produto toProduto (){
        System.out.println(caracteristicaRequestList);
        return new Produto(nome,valor,quantidade,descricao,
                categoria,caracteristicaRequestList);
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
                '}';
    }
}
