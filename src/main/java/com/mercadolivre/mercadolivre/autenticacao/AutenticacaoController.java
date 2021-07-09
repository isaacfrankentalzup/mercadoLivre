package com.mercadolivre.mercadolivre.autenticacao;

import com.mercadolivre.mercadolivre.seguranca.TokenResponse;
import com.mercadolivre.mercadolivre.seguranca.TokenService;
import com.mercadolivre.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    private UsuarioRepository repository;

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    @Autowired
    public AutenticacaoController(UsuarioRepository repository,
                                  AuthenticationManager authenticationManager,
                                  TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken dadosLogin;

        dadosLogin = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getSenha());
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authenticate);


            return ResponseEntity.ok(new TokenResponse(token, "Beare"));


        }catch (AuthenticationException ex){
            return ResponseEntity.badRequest().build();
        }
    }



}
