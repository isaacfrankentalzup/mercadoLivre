package com.mercadolivre.mercadolivre.produto.foto;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;

public class FotoRequest {
    @NotBlank
    private String linkImagem;
    private Produto produto;

    public FotoRequest(String linkImagem, Produto produto) {
        this.linkImagem = linkImagem;
        this.produto = produto;
    }

    public String getLinkImagem() {
        return linkImagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public Foto toFoto(String email, ProdutoRepository repository){

        boolean seEmailLogado = repository.existsByUsuarioEmail(email);


        if(seEmailLogado){
            return new Foto(linkImagem, produto);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Produto nao permitido para usuario");
    }

    @Override
    public String toString() {
        return "FotoRequest{" +
                "linkImagem='" + linkImagem + '\'' +
                ", produto=" + produto +
                '}';
    }
}
