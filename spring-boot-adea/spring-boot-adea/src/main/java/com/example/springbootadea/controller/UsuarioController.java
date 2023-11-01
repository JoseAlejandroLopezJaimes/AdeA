package com.example.springbootadea.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootadea.dao.UsuarioDao;
import com.example.springbootadea.dao.UsuarioRepository;
import com.example.springbootadea.dto.LoginRequest;
import com.example.springbootadea.entities.Usuario;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		String encodedPassword = Base64.getEncoder().encodeToString(loginRequest.getPassword().getBytes());
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLoginAndPassword(loginRequest.getLogin(),encodedPassword);
		if(!optionalUsuario.isPresent()) {
			return new ResponseEntity<>("No existe usuario registrado con estos datos",HttpStatus.NO_CONTENT);
		}
		
		Usuario usuarioLogin = optionalUsuario.get();
		/*if(FechaUtilsHelper.esFechaMenorQue(usuarioLogin.getFechaVigencia(),new Date())) {
			return new ResponseEntity<>("La fecha de vigencia del usuario a Expirado",HttpStatus.NOT_ACCEPTABLE);
		}*/
		
		usuarioLogin.setNoAcceso( usuarioLogin.getNoAcceso() != null ?
				usuarioLogin.getNoAcceso()+1:1
				);
		usuarioRepository.save(usuarioLogin);
		return new ResponseEntity<>(usuarioLogin,HttpStatus.OK);
	}

	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody Usuario newUsuario){
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(newUsuario.getLogin());
		if(optionalUsuario.isPresent()) {
			return new ResponseEntity<>("Ya existe usuario con este Login",HttpStatus.BAD_REQUEST);
		}
		
		String encodedPassword = Base64.getEncoder().encodeToString(newUsuario.getPassword().getBytes());
		newUsuario.setPassword(encodedPassword);
		newUsuario.setFechaAlta(new Date());
		newUsuario.setFechaModificacion(new Date());
		Usuario usuario = usuarioRepository.save(newUsuario);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody Usuario editUsuario){
		if(editUsuario.getEstatus().equals(UsuarioDao.ESTADO_REVOCADO)) {
			editUsuario.setFechaRevocado(new Date());
		}	
		
		if(editUsuario.getEstatus().equals(UsuarioDao.ESTADO_INACTIVO)) {
			editUsuario.setFechaBaja(new Date());
		}	
		
		editUsuario.setFechaModificacion(new Date());
		usuarioRepository.save(editUsuario);
		return new ResponseEntity<>("Usuario Actualizado correctamente",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeUsuario(@PathVariable("id") Long idUsuario){
		usuarioRepository.deleteById(idUsuario);
		return new ResponseEntity<>("Usuario Eliminado correctamente",HttpStatus.OK);
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<?> getAllUsuarios(){
		List<Usuario> listUsuarios = usuarioRepository.findAll();
 		return new ResponseEntity<>(listUsuarios,HttpStatus.OK);
	}
	

	@GetMapping(path = "/search")
	public ResponseEntity<?> getSearchUsuarios(@RequestParam MultiValueMap<String, ?> params){
		List<Usuario> listUsuario = usuarioRepository.findByFilters(params);
		return new ResponseEntity<>(listUsuario ,HttpStatus.OK);
	}
}
