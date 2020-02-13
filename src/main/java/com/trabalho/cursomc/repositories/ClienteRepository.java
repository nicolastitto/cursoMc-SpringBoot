package com.trabalho.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
