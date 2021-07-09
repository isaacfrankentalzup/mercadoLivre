package com.mercadolivre.mercadolivre.produto;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void metodo(@RequestBody @Valid ProdutoRequest produtoRequest){

        repository.save(produtoRequest.toProduto());

    }

    @GetMapping
    public List<Produto> listarTudo(){
        return repository.findAll();
    }

}
