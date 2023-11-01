package com.example.springbootadea.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootadea.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>,UsuarioDao {

	public Optional<Usuario> findByLogin(String login);
	
	public Optional<Usuario> findByLoginAndPassword(String login,String password);
}
