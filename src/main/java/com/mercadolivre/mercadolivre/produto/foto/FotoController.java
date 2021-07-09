package com.mercadolivre.mercadolivre.produto.foto;

import com.mercadolivre.mercadolivre.produto.ProdutoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("fotos")
public class FotoController {
    private FotoRepository repository;
    private ProdutoRepository produtoRepository;

    public FotoController(FotoRepository repository,
                          ProdutoRepository produtoRepository) {

        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public void salvarFoto(@RequestBody @Valid FotoRequest fotoRequest,
                           Principal principal){

        repository.save(fotoRequest.toFoto(principal.getName(), produtoRepository));

    }

}
