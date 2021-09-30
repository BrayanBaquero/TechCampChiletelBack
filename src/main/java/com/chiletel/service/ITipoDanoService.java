package com.chiletel.service;

import java.util.List;



import com.chiletel.dto.TipoDanoDTO;

/**
 * <h2>Descripci�n:</h2>
 * Interfas encargada de definir metodos de interacci�n
 * sobre DB para TipoDa�oService
 * @author Brayan Baquero
 */
public interface ITipoDanoService {
	/**
	 * <h2>Descripci�n:</h2>
	 * Se obtienen los tipos de da�o.
	 * @return List<{@link TipoDanoDTO}
	 */
	public List<TipoDanoDTO> getAllTipoDano();
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualizar la prioridad de los tipos de da�o.
	 * @param tipoDa�oDTOs
	 */
	public void updateTipoDano(List<TipoDanoDTO> tipoDanoDTOs);
	

}
