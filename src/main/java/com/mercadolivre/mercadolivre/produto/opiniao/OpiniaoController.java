package com.mercadolivre.mercadolivre.produto.opiniao;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("opinioes")
public class OpiniaoController {
    private OpiniaoRepository repository;

    public OpiniaoController(OpiniaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Opiniao metodo(@RequestBody @Valid OpiniaoRequest opiniaoRequest){
        return repository.save(opiniaoRequest.toOpiniao());
    }

    @GetMapping
    public List<Opiniao> listarTudo(){
        return repository.findAll();
    }

}
