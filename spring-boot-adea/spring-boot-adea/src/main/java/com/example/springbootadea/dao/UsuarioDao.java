package com.example.springbootadea.dao;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.example.springbootadea.entities.Usuario;

public interface UsuarioDao {
    public static final String ESTADO_REVOCADO = "R";
    public static final String ESTADO_INACTIVO = "I";
	
	public List<Usuario> findByFilters(MultiValueMap<String, ?> params);
}
