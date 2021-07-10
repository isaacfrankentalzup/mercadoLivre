package com.mercadolivre.mercadolivre.produto.pergunta;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.produto.ProdutoRepository;
import com.mercadolivre.mercadolivre.usuario.Usuario;
import com.mercadolivre.mercadolivre.usuario.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("perguntas")
public class PerguntaController {
    private PerguntaRepository perguntaRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;

    public PerguntaController(PerguntaRepository perguntaRepository,
                              UsuarioRepository usuarioRepository,
                              ProdutoRepository produtoRepository) {
        this.perguntaRepository = perguntaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public void salvarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest,
                               Principal principal){

        perguntaRepository.save(perguntaRequest.toPergunta(principal.getName(),usuarioRepository));

        Long idproduto = perguntaRequest.getProduto().getId();

        Optional<Produto> produto = produtoRepository.findById(idproduto);

        if(produto.isPresent()){
            Optional<Usuario> usuario = usuarioRepository.findById(produto.get().getUsuario().getId());
            if(usuario.isPresent()){
                String emailDonoProduto = usuario.get().getEmail();
                SendEmail sendEmail = new SendEmail(principal.getName(),emailDonoProduto,
                        "Uma nova pergunta no seu produto", perguntaRequest.getPergunta());
                System.out.println(sendEmail);
            }
        }

    }

}
