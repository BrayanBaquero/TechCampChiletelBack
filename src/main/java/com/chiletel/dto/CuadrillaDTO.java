package com.chiletel.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase encargada exponer la información asociada a las cuadrillas.
 * Se realiza el mapeo de la entidad Cuadrilla a esta clase
 * Cada propiedad de esta clase contiene anotaciones de validacion
 *  con sus respectivos mensajes, si llegase a haber una falla de validacion 
 *  se lanzara la excepcion MethodArgumentNotValidException que sera manejada
 *  por la clase ExceptionConfig. 
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuadrillaDTO {
	private String nombre;
	private int miembros;
	private Set<String> zona=new HashSet<>();
}
