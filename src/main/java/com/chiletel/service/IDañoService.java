package com.chiletel.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;

/**
 * <p>
 * <h2>Descripción:</h2>
 * Interfas encargada de definir metodos de interaccion
 * sobre DB para DañosService
 * @author Brayan Baquero
 */
public interface IDañoService {
	
	/**
	 * <h2>Descripción:</h2>
	 * Añadir un nuevo daño.
	 * @param dañoDTO
	 */
	public void addDaño(DañoNuevoDTO dañoDTO);
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener los daños registrados paginados-
	 * @param pageable
	 * @return page<{@link DañoVerReporteDTO}>
	 */
	public Page<DañoVerReporteDTO> getDaños(Pageable pageable);
}
