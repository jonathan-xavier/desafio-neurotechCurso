package com.jhon.desafio.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhon.desafio.event.RecursoCriadoEvent;
import com.jhon.desafio.model.Usuario;
import com.jhon.desafio.repository.UsuarioRepository;

@RestController
@RequestMapping({"/usuario"})
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario,
			HttpServletResponse response){
		Usuario usuarioSalvo = repository.save(usuario);
		
		//retorna atraves do header o recurso que acabou de ser criado.
				//uri é tipo uma url, na real url é um subtipo de uri.
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
