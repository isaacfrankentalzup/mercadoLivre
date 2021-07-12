package com.mercadolivre.mercadolivre.produto.foto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByProdutoId(Long id);
}
