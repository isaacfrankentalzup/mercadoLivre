package com.mercadolivre.mercadolivre.site;

import com.mercadolivre.mercadolivre.categoria.Categoria;
import com.mercadolivre.mercadolivre.produto.Caracteristica;
import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.produto.ProdutoRepository;
import com.mercadolivre.mercadolivre.produto.foto.Foto;
import com.mercadolivre.mercadolivre.produto.foto.FotoRepository;
import com.mercadolivre.mercadolivre.produto.opiniao.Opiniao;
import com.mercadolivre.mercadolivre.produto.opiniao.OpiniaoRepository;
import com.mercadolivre.mercadolivre.produto.pergunta.Pergunta;
import com.mercadolivre.mercadolivre.produto.pergunta.PerguntaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/sitedetalhes")
public class SiteDetalheController {

    private ProdutoRepository produtoRepository;
    private FotoRepository fotoRepository;
    private OpiniaoRepository opiniaoRepository;
    private PerguntaRepository perguntaRepository;

    public SiteDetalheController(ProdutoRepository produtoRepository,
                                 FotoRepository fotoRepository,
                                 OpiniaoRepository opiniaoRepository,
                                 PerguntaRepository perguntaRepository) {
        this.produtoRepository = produtoRepository;
        this.fotoRepository = fotoRepository;
        this.opiniaoRepository = opiniaoRepository;
        this.perguntaRepository = perguntaRepository;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<?> siteDetalhes(@PathVariable Long produtoId){
        Optional<Produto> existeProduto = produtoRepository.findById(produtoId);
        if(existeProduto.isPresent()){
            //lista de variáveis pegando dados de diversas classes
            Produto produto  = existeProduto.get();
            String nome = produto.getNome();
            BigDecimal valor = produto.getValor();
            Integer quantidade = produto.getQuantidade();
            String descricao = produto.getDescricao();
            Categoria categoria = produto.getCategoria();
            List<Caracteristica> caracteristicas = produto.getCaracteristicas();

            //consultar fotos
            List<Foto> fotos = fotoRepository.findByProdutoId(produto.getId());
            List<String> links = new ArrayList<>();
            for (Foto item: fotos) {
                links.add(item.getLinkImagem());
            }

//            List<String> listaLinks = fotos.stream().map(x->x.getLinkImagem()).collect(Collectors.toList());


            //consultar opinioes
            List<Opiniao> opinioes = opiniaoRepository.findByProdutoId(produto.getId());
            Integer qtNotas = opinioes.size();
            Double somaNotas = 0.0;
            Map<String,String> opinioesRecebidas = new HashMap<>();
            for (Opiniao linha: opinioes) {
                somaNotas += linha.getNota();
                opinioesRecebidas.put(linha.getTitulo(),linha.getDescricao());
            }
            Double mediaNotas = (somaNotas / qtNotas);

            //consultar perguntas
            List<Pergunta> perguntas = perguntaRepository.findByProdutoId(produto.getId());
            List<String> perguntasRecebidas = new ArrayList<>();
            for (Pergunta linha: perguntas) {
                perguntasRecebidas.add(linha.getPergunta());
            }

            SiteDetalheResponse response = new SiteDetalheResponse(
                    nome,valor,quantidade,descricao,categoria,
                    caracteristicas,links,opinioesRecebidas,qtNotas,perguntasRecebidas);

            return ResponseEntity.ok(response);

        }
        return ResponseEntity.notFound().build();
    }



}
