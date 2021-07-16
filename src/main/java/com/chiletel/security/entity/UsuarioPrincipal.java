package com.chiletel.security.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * <h2>Descripción:</h2>
 * Clase encargada de los privilegios de usuario
 * @author Brayan Baquero
 */
@Getter
@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {
	private String nombre;
	@Getter(value = AccessLevel.NONE)
	private String nombreUsuario;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	//Asignar privilegios a usuario 
	//Convertir usuario de entidad de base de datos en usuario Spring Security (privilegios)
	public static UsuarioPrincipal build(Usuario usuario) {
		//Convertir clase role en clase GranteedAuthority
		List<GrantedAuthority> authorities=
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(
						rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
}
