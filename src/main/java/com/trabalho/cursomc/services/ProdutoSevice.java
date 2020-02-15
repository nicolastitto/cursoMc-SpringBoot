package com.trabalho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.trabalho.cursomc.domain.Categoria;
import com.trabalho.cursomc.domain.Produto;
import com.trabalho.cursomc.repositories.CategoriaRepository;
import com.trabalho.cursomc.repositories.ProdutoRepository;
import com.trabalho.cursomc.services.exception.ResourceNotFoundException;

@Service
public class ProdutoSevice {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository catRepository;
	
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepository.findAllById(ids);
		 
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
}
