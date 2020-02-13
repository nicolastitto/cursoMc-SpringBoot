package com.trabalho.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.trabalho.cursomc.domain.AreaUrbana;
import com.trabalho.cursomc.domain.Categoria;
import com.trabalho.cursomc.domain.Distrito;
import com.trabalho.cursomc.domain.Produto;
import com.trabalho.cursomc.repositories.AreaUrbanaRepository;
import com.trabalho.cursomc.repositories.CategoriaRepository;
import com.trabalho.cursomc.repositories.DistritoRepository;
import com.trabalho.cursomc.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private AreaUrbanaRepository aURepository;	
	@Autowired
	private DistritoRepository distritoRepository;
	
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
		
		Distrito d1 = new Distrito(null, "Lisboa");
		Distrito d2 = new Distrito(null, "Porto");
		
		AreaUrbana au1 = new AreaUrbana(null, "Lisboa", d1);
		AreaUrbana au2 = new AreaUrbana(null, "Odivelas", d1);
		AreaUrbana au3 = new AreaUrbana(null, "Porto", d2);
		
		d1.getAreasUrbanas().addAll(Arrays.asList(au1, au2));
		d2.getAreasUrbanas().addAll(Arrays.asList(au3));
		
		distritoRepository.saveAll(Arrays.asList(d1, d2));
		aURepository.saveAll(Arrays.asList(au1, au2, au3));
	}

}
