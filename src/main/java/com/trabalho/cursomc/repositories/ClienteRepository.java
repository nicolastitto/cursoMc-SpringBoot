package com.trabalho.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

}
