package com.chiletel.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaEventosDTOO {
	public int ord_Id;
	public String inicio;
	public String Final;
	public String nombreCliente;
	public String apellidoCliente;
	public Long identificacion;
	public String tipoIncidencia;
	public String direccion;
}
