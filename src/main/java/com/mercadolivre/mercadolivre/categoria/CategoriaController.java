package com.mercadolivre.mercadolivre.categoria;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Categoria salvarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(categoriaRequest);
        return repository.save(categoriaRequest.toCategoria());
    }
    @GetMapping
    public List<Categoria> listarTudo(){
        return repository.findAll();
    }
}
