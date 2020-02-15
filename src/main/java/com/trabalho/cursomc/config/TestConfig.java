package com.trabalho.cursomc.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.trabalho.cursomc.domain.AreaUrbana;
import com.trabalho.cursomc.domain.Categoria;
import com.trabalho.cursomc.domain.Cliente;
import com.trabalho.cursomc.domain.Distrito;
import com.trabalho.cursomc.domain.Endereco;
import com.trabalho.cursomc.domain.ItemPedido;
import com.trabalho.cursomc.domain.Pagamento;
import com.trabalho.cursomc.domain.PagamentoBoleto;
import com.trabalho.cursomc.domain.PagamentoCartao;
import com.trabalho.cursomc.domain.Pedido;
import com.trabalho.cursomc.domain.Produto;
import com.trabalho.cursomc.domain.enuns.EstadoPagamento;
import com.trabalho.cursomc.domain.enuns.TipoCliente;
import com.trabalho.cursomc.repositories.AreaUrbanaRepository;
import com.trabalho.cursomc.repositories.CategoriaRepository;
import com.trabalho.cursomc.repositories.ClienteRepository;
import com.trabalho.cursomc.repositories.DistritoRepository;
import com.trabalho.cursomc.repositories.EnderecoRepository;
import com.trabalho.cursomc.repositories.ItemPedidoRepository;
import com.trabalho.cursomc.repositories.PagamentoRepository;
import com.trabalho.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository endRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository iPRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Distrito d1 = new Distrito(null, "Lisboa");
		Distrito d2 = new Distrito(null, "Porto");

		AreaUrbana au1 = new AreaUrbana(null, "Lisboa", d1);
		AreaUrbana au2 = new AreaUrbana(null, "Odivelas", d1);
		AreaUrbana au3 = new AreaUrbana(null, "Porto", d2);

		d1.getAreasUrbanas().addAll(Arrays.asList(au1, au2));
		d2.getAreasUrbanas().addAll(Arrays.asList(au3));

		distritoRepository.saveAll(Arrays.asList(d1, d2));
		aURepository.saveAll(Arrays.asList(au1, au2, au3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", TipoCliente.PESSOAFISICA);

		cli1.getTelefone().addAll(Arrays.asList("999999999", "999888888"));

		Endereco e1 = new Endereco(null, "Av republica", "lote A", "6 andar", "Marques de pombal", "00000-000", cli1,
				au1);
		Endereco e2 = new Endereco(null, "Rua major caudas", "34", "1 esq", "Odivelas", "00000-000", cli1, au2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		endRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 1000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 10.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 10.00, 1, 100.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		iPRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
