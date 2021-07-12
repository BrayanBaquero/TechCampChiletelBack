package com.chiletel.dto;

/**
 * <h2>Descripci�n:</h2>
 * Interfaz de proyecci�n de un nativeQuery definico en ICuadrillaRepositori<br>
 * que se encarga de obtener las cuadrillas activas y el numero de t�cnicos <br>
 * que pertenece a cada una.
 * @author Brayan Baquero
 *
 */

public interface CuadrillasTecnicos {
	public int getId();
	public int getMiembros();
	public String getNombre();
	//public Set<Zona> getZona();
	
}
