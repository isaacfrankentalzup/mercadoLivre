package com.mercadolivre.mercadolivre.usuario;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioRepository repository;

    @PostMapping
    public void salvarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        repository.save(usuarioRequest.toUsuario());
    }

    @GetMapping
    public List<Usuario> listarTudo(){

        return repository.findAll();
    }
}
