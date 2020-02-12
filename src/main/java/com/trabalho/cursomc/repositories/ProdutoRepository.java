package com.trabalho.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
