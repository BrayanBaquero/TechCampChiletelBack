package com.chiletel.service;

import java.util.List;



import com.chiletel.dto.TipoDañoDTO;

/**
 * <h2>Descripción:</h2>
 * Interfas encargada de definir metodos de interacción
 * sobre DB para TipoDañoService
 * @author Brayan Baquero
 */
public interface ITipoDañoService {
	/**
	 * <h2>Descripción:</h2>
	 * Se obtienen los tipos de daño.
	 * @return List<{@link TipoDañoDTO}
	 */
	public List<TipoDañoDTO> getAllTipoDaño();
	
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar la prioridad de los tipos de daño.
	 * @param tipoDañoDTOs
	 */
	public void updateTipoDaño(List<TipoDañoDTO> tipoDañoDTOs);
	

}
