package com.chiletel.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;

/**
 * <p>
 * <h2>Descripci�n:</h2>
 * Interfas encargada de definir metodos de interaccion
 * sobre DB para Da�osService
 * @author Brayan Baquero
 */
public interface IDa�oService {
	
	/**
	 * <h2>Descripci�n:</h2>
	 * A�adir un nuevo da�o.
	 * @param da�oDTO
	 */
	public void addDa�o(Da�oNuevoDTO da�oDTO);
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Obtener los da�os registrados paginados-
	 * @param pageable
	 * @return page<{@link Da�oVerReporteDTO}>
	 */
	public Page<Da�oVerReporteDTO> getDa�os(Pageable pageable);
}
