package com.chiletel.dto;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase encargada exponer la información asociada a los tecnicos.
 * Se realiza el mapeo de la entidad Tecnicos a esta clase
 * Cada propiedad de esta clase contiene anotaciones de validacion
 *  con sus respectivos mensajes, si llegase a haber una falla de validacion 
 *  se lanzara la excepcion MethodArgumentNotValidException se sera manejada
 *  por la clase ExceptionConfig. 
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoDTO {
	@NotEmpty
	@Size(min = 3,max = 50,message = "Debe tener mas de 3 caracteres y menos de 50")
	private String nombre;
	
	@NotEmpty(message = "Campo no puede estar vacio")
	@Size(min = 3,max = 50,message = "Debe tener mas de 3 caracteres y menos de 50")
	private String apellido;
	
	@Digits(integer = 11,fraction = 0)
	@Min(value = 10000)
	private Long numeroIden;
	
	@Email(message = "Formato de correo electronico no valido")
	@NotEmpty(message = "Campo no puede estar vacio")
	private String email;
	
	@NotBlank(message = "El campo es requerido")
	@Size(min = 3,max = 20,message = "Debe tener mas de 3 digitos y menos de 19")
	private String telefono;
	
	@NotEmpty(message = "Campo no puede estar vacio")
	@Size(min = 3,max = 40,message = "Debe tener mas de 3 caracteres y menos de 40")
	private String direccion;
	
	@NotEmpty(message = "Campo no puede estar vacio")
	@Size(min = 3,max = 50,message = "Debe tener mas de 3 caracteres y menos de 20")
	private String cuadrilla;
	
	@NotEmpty(message = "Debe existir al menos un tipo de daño")
	private Set<String> tDano=new HashSet<>();


	
	
}
