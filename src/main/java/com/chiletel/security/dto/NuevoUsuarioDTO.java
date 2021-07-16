package com.chiletel.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * <h2>Descripción:</h2>
 * Clase que define los datos que debera ingresar el usuario para loguearce.
 * @author Brayan Baquero
 *
 */
@Getter
@Setter
public class NuevoUsuarioDTO {
	@NotBlank
	@Size(max = 20)
	private String nombre;
	@NotBlank
	@Size(max = 20)
	private String nombreUsuario;
	@Email
	@Size(max = 100)
	private String email;
	@NotBlank
	@Size(max = 100)
	private String password;
	private Set<String> roles=new HashSet<>();
}
