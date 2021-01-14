package com.example.osworksapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.osworksapi.domain.model.Cliente;
import com.example.osworksapi.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;	
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	

	@GetMapping ("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long ClienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(ClienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
			
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar (@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);	
	}
	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Validated @PathVariable Long clienteId,
			@RequestBody Cliente cliente) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long ClienteId){
		
		
		if (!clienteRepository.existsById(ClienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(ClienteId);
		return ResponseEntity.noContent().build();
		
		
	}
}



