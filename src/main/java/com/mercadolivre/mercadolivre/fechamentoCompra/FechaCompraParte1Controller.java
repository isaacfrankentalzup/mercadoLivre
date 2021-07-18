package com.mercadolivre.mercadolivre.fechamentoCompra;

import com.mercadolivre.mercadolivre.produto.Produto;
import com.mercadolivre.mercadolivre.usuario.Usuario;
import com.mercadolivre.mercadolivre.usuario.UsuarioRepository;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.BindException;

@RestController
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/compras")
    @Transactional
    public String cria(@RequestBody @Valid NovaCompraRequest request) {

        Produto produtoAserComprado = manager.find(Produto.class,
                request.getIdProduto());

        int quantidade = request.getQuantidade();
        System.out.println("quantidade = " + quantidade);
        boolean abateu = produtoAserComprado.abataEstoque(quantidade);

        if (abateu) {
            Usuario comprador = usuarioRepository.findByEmail("alberto@deveficiente.com").get();
            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoAserComprado, quantidade, comprador, request.getGateway());
            manager.persist(novaCompra);

            if(gateway.equals(GatewayPagamento.pagseguro)){
                String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(novaCompra,getId().toString);
                return "pagseguro.com" + novaCompra.getId() +
                        + "?redirectUrl={urlRetornoAppPosPagamento}"+urlRetornoPagseguro;
            }else{
                String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(novaCompra,getId().toString);
                return "paypal.com" + novaCompra.getId() +
                        + "?redirectUrl={urlRetornoAppPosPagamento}"+urlRetornoPaypal;

            }

        }
        BindException problemaComEstoque = new BindException(request, "NovaCompraRequest");
        problemaComEstoque.reject(null, "não foi possível realizar compra por causa do estoquec");
        throw problemaComEstoque;
    }

}
