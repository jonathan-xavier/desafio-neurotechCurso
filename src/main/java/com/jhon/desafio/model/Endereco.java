package com.jhon.desafio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String logadouro;
	
	@NotNull
	private int numero;
	
	private String complemento;
	@NotNull
	private String bairro;
	@NotNull
	private String cidade;
	@NotNull
	private String estado;
	@NotNull
	private Long cep;
	
	//antacao para resolver referencia ciclica.
	@JsonBackReference
	@ManyToOne
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	
	
}
