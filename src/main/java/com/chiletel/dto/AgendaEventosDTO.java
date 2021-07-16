package com.chiletel.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>Descripción:</h2>
 * Dto que que encarga de exponer en la api los eventos(ordenes de atencion agendadas)<br>
 * de cada tecnico, y informacion adicional del cliente donde se va a atender la falla.
 * @author Brayan Baquero
 *
 */
@Data
@NoArgsConstructor
public class AgendaEventosDTO {
	public int ord_Id;
	public String inicio;
	public String Final;
	public String nombreCliente;
	public String apellidoCliente;
	public Long identificacion;
	public String tipoIncidencia;
	public String direccion;
}
