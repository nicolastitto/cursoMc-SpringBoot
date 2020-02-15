package com.trabalho.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.cursomc.domain.Produto;
import com.trabalho.cursomc.dto.ProdutoDTO;
import com.trabalho.cursomc.resources.utils.URL;
import com.trabalho.cursomc.services.ProdutoSevice;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoSevice service;
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam (value = "nome", defaultValue = "") String nome, 
			@RequestParam (value = "categorias", defaultValue = "") String categorias,
			@RequestParam (value = "page", defaultValue = "0") Integer page,
			@RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPage, 
			@RequestParam (value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam (value = "direction", defaultValue = "ASC") String direction){
		String nomeDecoded = URL.decodeParam(nome);
		List<Long> ids = URL.decodeLongId(categorias); 
		Page<Produto> list = service.search(nomeDecoded,ids, page , linesPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO (obj));
		return ResponseEntity.ok().body(listDTO);	
	}
	
}
