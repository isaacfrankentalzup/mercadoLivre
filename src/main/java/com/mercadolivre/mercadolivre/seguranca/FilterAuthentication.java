package com.mercadolivre.mercadolivre.seguranca;

import org.springframework.security.core.token.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterAuthentication extends OncePerRequestFilter {
    private TokenService tokenService;

    public FilterAuthentication(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException,
            IOException {


        //EXTRAIR ESTA LÓGICA PARA UM MÉTODO PRIVADO
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            token = null;
        }else{
            token = token.substring(7,token.length());
        }

    }
}
