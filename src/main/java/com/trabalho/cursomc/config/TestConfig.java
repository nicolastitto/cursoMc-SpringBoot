package com.trabalho.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.trabalho.cursomc.domain.Categoria;
import com.trabalho.cursomc.domain.Produto;
import com.trabalho.cursomc.repositories.CategoriaRepository;
import com.trabalho.cursomc.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 1000.00);
		Produto p2 = new Produto(null, "Impressora", 100.00);
		Produto p3 = new Produto(null, "Rato", 10.00);
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat1);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}

}
