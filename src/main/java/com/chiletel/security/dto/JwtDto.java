package com.chiletel.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/**
 * <h2>Descripción:</h2>
 * Dto encargado de definir la respuesta cuando el usuario se logea con éxito<br>
 * se devolvera el token generado y roles asociados al token (Authorities)
 * @author 1015453029
 *
 */
@Getter
@Setter
public class JwtDto {
	private String token;
	private String bearer="Bearer";
	private String nombreUsuario;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
	}
	
	
}
