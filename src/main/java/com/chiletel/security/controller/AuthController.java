package com.chiletel.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.chiletel.security.dto.JwtDto;
import com.chiletel.security.dto.LoginUsuarioDTO;
import com.chiletel.security.dto.NuevoUsuarioDTO;
import com.chiletel.security.service.UsuarioService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <h2>Descripción:</h2>
 * Se definen los endpoint para crear un nuevo usuario y autenticarce en la api.
 * @author Brayan Baquero
 *
 */
@Api(tags = "1: Autenticación",description = "Autenticacion de usuario en el sistema usando JWT")
//@ApiOperation(value="",position = 0)
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	UsuarioService usuarioService;

	
	@ApiOperation(hidden = true, value = "")
	@PostMapping("/nuevo")
	public ResponseEntity<MessageOk> nuevo(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuario){
		 usuarioService.addUsuario(nuevoUsuario);
		 return new ResponseEntity<MessageOk>(new MessageOk("Usuario guardado"), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Ingreso de usuario al sistema.")
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario){
		
		return new ResponseEntity<JwtDto>(usuarioService.login(loginUsuario),HttpStatus.OK);
		
	}
}
