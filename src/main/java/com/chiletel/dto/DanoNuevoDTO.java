package com.chiletel.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase encargada de recibir los datos entranter en la api para registrar un daño.<br>
 * Se realiza el mapeo de la entidad Tecnicos a esta clase <br>
 * Cada propiedad de esta clase contiene anotaciones de validacion <br>
 *  con sus respectivos mensajes, si llegase a haber una falla de validacion <br> 
 *  se lanzara la excepcion MethodArgumentNotValidException se sera manejada <br>
 *  por la clase ExceptionConfig. 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanoNuevoDTO {
	
	@Digits(integer = 11,fraction = 0)
	@Min(value = 10000)
	private long cliente;
	
	@Size(min = 3,max = 40)
	private String tipoDano;
	
	@Size(min = 3,max = 150)
	private String descripcion;
	
}
