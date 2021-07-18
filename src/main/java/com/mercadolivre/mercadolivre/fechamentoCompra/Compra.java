package com.mercadolivre.mercadolivre.fechamentoCompra;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoEscolhido;
    @Positive
    private int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra(@NotNull @Valid Produto produtoEscolhido, @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quatidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }
}
