package com.jhon.desafio.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhon.desafio.event.RecursoCriadoEvent;
import com.jhon.desafio.model.Endereco;
import com.jhon.desafio.repository.EnderecoRepository;

@RestController
@RequestMapping({"/endereco"})
public class EnderecoController {

	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Endereco> save(@Valid @RequestBody Endereco endereco,
			HttpServletResponse response) {
		Endereco enderecoSalvo = repository.save(endereco);
		//retorna no header o endereço criado a uri.
		publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvo.getId()));
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
	}
	
	//buscar por id
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					return ResponseEntity.ok().body(record);
				}).orElse(ResponseEntity.notFound().build());
				
	}
	
	//buscar todos
	@GetMapping
	public List<Endereco> findAll(){
		return repository.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
