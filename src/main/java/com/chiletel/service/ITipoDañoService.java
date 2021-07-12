package com.chiletel.service;

import java.util.List;



import com.chiletel.dto.TipoDa�oDTO;

/**
 * <h2>Descripci�n:</h2>
 * Interfas encargada de definir metodos de interacci�n
 * sobre DB para TipoDa�oService
 * @author Brayan Baquero
 */
public interface ITipoDa�oService {
	/**
	 * <h2>Descripci�n:</h2>
	 * Se obtienen los tipos de da�o.
	 * @return List<{@link TipoDa�oDTO}
	 */
	public List<TipoDa�oDTO> getAllTipoDa�o();
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualizar la prioridad de los tipos de da�o.
	 * @param tipoDa�oDTOs
	 */
	public void updateTipoDa�o(List<TipoDa�oDTO> tipoDa�oDTOs);
	

}
