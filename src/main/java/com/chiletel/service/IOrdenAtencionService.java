package com.chiletel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.OrdenAtencionDTO;

/**
 * <h2>Descripción:<h2>
 * Interface que define el metodo para obtener las ordenes de atencion no agendadas.
 * @author Brayan Baquero
 *
 */
public interface IOrdenAtencionService {

	public Page<OrdenAtencionDTO> getAllOrdenesAtencion(Pageable pageable);

}
