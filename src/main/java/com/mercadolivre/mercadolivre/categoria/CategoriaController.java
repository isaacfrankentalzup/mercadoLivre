package com.mercadolivre.mercadolivre.categoria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Categoria salvarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){

        System.out.println(categoriaRequest);
        return repository.save(categoriaRequest.toCategoria());

    }
}
