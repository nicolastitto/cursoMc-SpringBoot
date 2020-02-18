package com.trabalho.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.cursomc.domain.ItemPedido;
import com.trabalho.cursomc.domain.PagamentoBoleto;
import com.trabalho.cursomc.domain.Pedido;
import com.trabalho.cursomc.domain.enuns.EstadoPagamento;
import com.trabalho.cursomc.repositories.ItemPedidoRepository;
import com.trabalho.cursomc.repositories.PagamentoRepository;
import com.trabalho.cursomc.repositories.PedidoRepository;
import com.trabalho.cursomc.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired PagamentoRepository pagtoRepository;
	
	@Autowired
	private ProdutoSevice produtoService;
	
	@Autowired ItemPedidoRepository ipRepository;
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto)obj.getPagamento();
			BoletoService.preencherPagtoBoleto(pagto, obj.getInstante());
		}
		obj=repository.save(obj);
		pagtoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		ipRepository.saveAll(obj.getItens());
		return obj;
	}
}
