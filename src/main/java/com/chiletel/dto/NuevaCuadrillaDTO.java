package com.chiletel.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase encargada de obtener datos de la api para cread nuevas cuadrillas<br>
 * y actualizar los datos de una cuadrilla ya existente.
 * @author Brayan Baquero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevaCuadrillaDTO {
	@Size(min = 3,max = 20)
	private String nombre;
	@NotEmpty
	private Set<String> zona;
}
