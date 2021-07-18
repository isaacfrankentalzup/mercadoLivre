package com.mercadolivre.mercadolivre.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.mercadolivre.categoria.Categoria;
import com.mercadolivre.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    //@ExisteCampo(atributo = "id", aClass = Produto.class, groups = Groups.Produto.class)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "decimal(10,2) not null check(valor>0)")
    private BigDecimal valor;
    @Column(columnDefinition = "int not null check(quantidade>=0)")
    private Integer quantidade;
    @Column(nullable = false, length = 1000)
    private String descricao;

    private LocalDateTime creatAt = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    @Deprecated //Hibernate
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade,
                   String descricao, Categoria categoria,
                   //List<CaracteristicaRequest> caracteristicas)
                   List<CaracteristicaRequest> caracteristicas,
                    Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas.stream()
                .map(e->new Caracteristica(e.getNome(),e.getDescricao(),this))
                .collect(Collectors.toList());

        this.usuario = usuario;
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
            return usuario;
        }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean abataEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade de ser maior que zero"
                + quantidade);

        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }


}
