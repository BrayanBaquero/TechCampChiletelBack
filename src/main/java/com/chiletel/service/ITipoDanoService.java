package com.chiletel.service;

import java.util.List;



import com.chiletel.dto.TipoDanoDTO;

/**
 * <h2>Descripción:</h2>
 * Interfas encargada de definir metodos de interacción
 * sobre DB para TipoDañoService
 * @author Brayan Baquero
 */
public interface ITipoDanoService {
	/**
	 * <h2>Descripción:</h2>
	 * Se obtienen los tipos de daño.
	 * @return List<{@link TipoDanoDTO}
	 */
	public List<TipoDanoDTO> getAllTipoDano();
	
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar la prioridad de los tipos de daño.
	 * @param tipoDañoDTOs
	 */
	public void updateTipoDano(List<TipoDanoDTO> tipoDanoDTOs);
	

}
