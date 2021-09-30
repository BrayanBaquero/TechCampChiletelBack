package com.chiletel.dto;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>Descripci�n:</h2>
 * Clase encargada de exponer la informaci�n de los da�os repostados.
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
