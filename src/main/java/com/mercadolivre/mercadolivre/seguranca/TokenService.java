package com.mercadolivre.mercadolivre.seguranca;

import com.mercadolivre.mercadolivre.usuario.Usuario;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String gerarToken(Authentication authentication){

        //pegar o ID do usuario que está dentro do objeto authentication
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Long idUsuario =  usuario.getId();

        //pegar data de geração e expiracao utilizando a API Date
        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + 30000l);

        String meuToken = Jwts.builder()
                .setIssuer("API do Mercado Livre") //qual cliente está consumindo
                .setSubject(idUsuario.toString()) //passo o ID como String
                .setIssuedAt(hoje) //data de geração do Token
                .setExpiration(expiracao) //data da expiração deste token
                .signWith(SignatureAlgorithm.HS256, "senha") //tipo de criptografia e senha da aplicação
                .compact();

        return meuToken;
    }

    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey("senha")
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey("senha")
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());

    }

}
