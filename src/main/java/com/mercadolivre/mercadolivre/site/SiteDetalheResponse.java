package com.mercadolivre.mercadolivre.site;

import com.mercadolivre.mercadolivre.categoria.Categoria;
import com.mercadolivre.mercadolivre.produto.Caracteristica;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SiteDetalheResponse {
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private Categoria categoria;
    private List<Caracteristica> caracteristicas;
    private List<String> links;
    private Map<String,String> opinioesRecebidas;
    private Integer qtNotas;
    private List<String> perguntasRecebidas;

    public SiteDetalheResponse(String nome, BigDecimal valor,
                               Integer quantidade, String descricao,
                               Categoria categoria, List<Caracteristica> caracteristicas,
                               List<String> links, Map<String, String> opinioesRecebidas,
                               Integer qtNotas, List<String> perguntasRecebidas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.links = links;
        this.opinioesRecebidas = opinioesRecebidas;
        this.qtNotas = qtNotas;
        this.perguntasRecebidas = perguntasRecebidas;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getLinks() {
        return links;
    }

    public Map<String, String> getOpinioesRecebidas() {
        return opinioesRecebidas;
    }

    public Integer getQtNotas() {
        return qtNotas;
    }

    public List<String> getPerguntasRecebidas() {
        return perguntasRecebidas;
    }

}
