package com.chiletel.security.dto;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
/**
 * <h2>Descripción:</h2>
 * DTO que se encarga de obtener los datos para que el usuario ingrese al sistema.
 * @author Brayan Baquero
 *
 */
@Getter
@Setter
public class LoginUsuarioDTO {
	@NotBlank
	private String nombreUsuario;
	@NotBlank
	private String password;
}
