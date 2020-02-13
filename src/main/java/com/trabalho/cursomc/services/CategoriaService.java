package com.trabalho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.trabalho.cursomc.domain.Categoria;
import com.trabalho.cursomc.repositories.CategoriaRepository;
import com.trabalho.cursomc.services.exception.DataIntegrityException;
import com.trabalho.cursomc.services.exception.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repository.save(obj);
	}

	public void deleteById(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos!");
		}
	}
}
