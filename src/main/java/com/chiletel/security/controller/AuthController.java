package com.chiletel.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.security.dto.JwtDto;
import com.chiletel.security.dto.LoginUsuario;
import com.chiletel.security.dto.NuevoUsuario;
import com.chiletel.security.entity.Rol;
import com.chiletel.security.entity.Usuario;
import com.chiletel.security.enums.RolNombre;
import com.chiletel.security.jwt.JwtProvider;
import com.chiletel.security.service.RolService;
import com.chiletel.security.service.UserDetailsServiceImpl;
import com.chiletel.security.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@Api(tags = "1: Autenticación",description = "Autenticacion de usuario en el sistema usando JWT")
//@ApiOperation(value="",position = 0)
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RolService rolService;
	@Autowired
	JwtProvider jwtProvider;
	
	@ApiOperation(hidden = true, value = "")
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		 if(bindingResult.hasErrors())
			return new ResponseEntity("Campos puestos en orden invalido",HttpStatus.BAD_REQUEST );
		 if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
				 return new ResponseEntity("Ese nombre ya existe",HttpStatus.BAD_REQUEST);
		 if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			 return new ResponseEntity("Ese email ya existe",HttpStatus.BAD_REQUEST);
		 Usuario usuario=new Usuario(nuevoUsuario.getNombre(),nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(),passwordEncoder.encode(nuevoUsuario.getPassword()));
		 
		 Set<Rol> roles=new HashSet<>();
		 roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		 if(nuevoUsuario.getRoles().contains("admin"))
			 roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		 usuario.setRoles(roles);
		 usuarioService.save(usuario);
		 return new ResponseEntity("Usuario guardado", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Ingreso de usuario al sistema.")
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new BadRequestException("Campos mal puestos");
			//return new ResponseEntity("Campos mal puestos",HttpStatus.BAD_REQUEST );
		try {
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt=jwtProvider.generateToken(authentication);
			UserDetails userDetails=(UserDetails) authentication.getPrincipal();
			JwtDto jwtDto=new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
			return new ResponseEntity(jwtDto,HttpStatus.OK);
		} catch (Exception e) {
			throw new BadRequestException("Usuario o contraseña invalidos");
		}
		
	}
}
