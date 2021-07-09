package com.mercadolivre.mercadolivre.produto;

import com.mercadolivre.mercadolivre.usuario.Usuario;
import com.mercadolivre.mercadolivre.usuario.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository repository;
    private UsuarioRepository usuarioRepository;

    public ProdutoController(ProdutoRepository repository,
                             UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping

        public Produto salvarProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                Principal principal){

            String email = principal.getName();
            Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

            if(usuario.isPresent()){
                return repository.save(produtoRequest.toProduto(usuario.get()));
            }

            throw new RuntimeException("Usuario Inválido!");

        }

    @GetMapping
    public List<Produto> listarTudo(){
        return repository.findAll();
    }

}
