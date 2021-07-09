package com.mercadolivre.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableSpringDataWebSupport
@EnableCaching
@SpringBootApplication
public class MercadolivreApplication {

	public static void main(String[] args) {

		SpringApplication.run(MercadolivreApplication.class, args);
	}

}
