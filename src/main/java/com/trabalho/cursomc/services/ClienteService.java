package com.trabalho.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalho.cursomc.domain.AreaUrbana;
import com.trabalho.cursomc.domain.Cliente;
import com.trabalho.cursomc.domain.Endereco;
import com.trabalho.cursomc.domain.enuns.TipoCliente;
import com.trabalho.cursomc.dto.ClienteDTO;
import com.trabalho.cursomc.dto.ClienteNewDTO;
import com.trabalho.cursomc.repositories.ClienteRepository;
import com.trabalho.cursomc.repositories.EnderecoRepository;
import com.trabalho.cursomc.services.exception.DataIntegrityException;
import com.trabalho.cursomc.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository endRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		endRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void deleteById(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possue pedidos!");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		AreaUrbana cid = new AreaUrbana(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getFreguesia(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefone().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefone().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefone().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	

}
