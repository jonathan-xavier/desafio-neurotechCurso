package com.jhon.desafio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;
	
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	@NotNull
	private String cpf;
	
	//eu coloco o manage reference do lado que eu quero que venha os resultados.
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Endereco> enderecos = new ArrayList<>();
}
