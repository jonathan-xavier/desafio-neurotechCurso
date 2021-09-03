package com.jhon.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhon.desafio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
