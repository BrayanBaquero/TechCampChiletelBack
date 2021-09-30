package com.chiletel.dto;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de exponer la información de los daños repostados.
 * @author Brayan Baquero
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanoVerReporteDTO {
	private long numeroIden;
	private String tipoDano;
	private Date fecha;
}
