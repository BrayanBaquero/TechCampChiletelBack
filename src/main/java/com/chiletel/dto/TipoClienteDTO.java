package com.chiletel.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <h2>Descripción:</h2>
 * Clase encargada de recibir los datos entrantes en la api para actualizar<br>
 *  la prioridad de tipo de cliente.
 * @author Brayan Baquero
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoClienteDTO {
	@Size(min = 3,max = 40)
	private String nombre;
	@Min(value = 1)
	private int prioridad;
}
