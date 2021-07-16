package com.chiletel.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.security.dto.JwtDto;
import com.chiletel.security.dto.LoginUsuarioDTO;
import com.chiletel.security.dto.NuevoUsuarioDTO;
import com.chiletel.security.entity.Rol;
import com.chiletel.security.entity.Usuario;
import com.chiletel.security.enums.RolNombre;
import com.chiletel.security.jwt.JwtProvider;
import com.chiletel.security.repositoty.IRolRepositoty;
import com.chiletel.security.repositoty.IUsuarioRepository;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de definir los servicios asociados o crear un nuevo <br>
 * usuario y logearse en el sistema
 * @author Brayan Baquero
 *
 */
@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	IRolRepositoty rolRepository;
	@Autowired
	JwtProvider jwtProvider;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	
	public void addUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) {
		if(usuarioRepository.existsByNombreUsuario(nuevoUsuarioDTO.getNombreUsuario()))
			throw new BadRequestException("Ese nombre ya existe");
		 if(usuarioRepository.existsByEmail(nuevoUsuarioDTO.getEmail()))
			    throw new BadRequestException("Ese email ya existe");
		 
		 Usuario usuario=new Usuario(nuevoUsuarioDTO.getNombre(),nuevoUsuarioDTO.getNombreUsuario(),nuevoUsuarioDTO.getEmail(),passwordEncoder.encode(nuevoUsuarioDTO.getPassword()));
		 
		 Set<Rol> roles=new HashSet<>();
		 roles.add(rolRepository.findByRolNombre(RolNombre.ROLE_USER).get());
		 if(nuevoUsuarioDTO.getRoles().contains("admin"))
			 roles.add(rolRepository.findByRolNombre(RolNombre.ROLE_ADMIN).get());
		 usuario.setRoles(roles);
		 usuarioRepository.save(usuario);
	}
	
	public JwtDto login(LoginUsuarioDTO loginUsuario) {
		
		try {
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt=jwtProvider.generateToken(authentication);
			UserDetails userDetails=(UserDetails) authentication.getPrincipal();
			JwtDto jwtDto=new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
			return jwtDto;
		} catch (Exception e) {
			throw new BadRequestException("Usuario o contraseña invalidos");
		}
		
	}
}

