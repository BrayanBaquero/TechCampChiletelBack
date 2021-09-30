package com.chiletel.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.DanoNuevoDTO;
import com.chiletel.dto.DanoVerReporteDTO;

/**
 * <p>
 * <h2>Descripción:</h2>
 * Interfas encargada de definir metodos de interaccion
 * sobre DB para DañosService
 * @author Brayan Baquero
 */
public interface IDanoService {
	
	/**
	 * <h2>Descripción:</h2>
	 * Añadir un nuevo daño.
	 * @param dañoDTO
	 */
	public void addDano(DanoNuevoDTO danoDTO);
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener los daños registrados paginados-
	 * @param pageable
	 * @return page<{@link DanoVerReporteDTO}>
	 */
	public Page<DanoVerReporteDTO> getDanos(Pageable pageable);
}
