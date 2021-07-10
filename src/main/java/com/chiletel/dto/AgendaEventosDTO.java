package com.chiletel.dto;

import java.math.BigInteger;

public interface AgendaEventosDTO {
	public int getOrd_Id();
	public String getInicio();
	public String getFinal();
	public String getNombreCliente();
	public String getApellidoCliente();
	public BigInteger getIdentificacion();
	public String getTipoIncidencia();
	public String getDireccion();
}
