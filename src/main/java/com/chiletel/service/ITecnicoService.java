package com.chiletel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.TecnicoDTO;
/**
 * <p>
 * Interfas encargada de definir metodos de interacción
 * sobre DB para TecnicoService
 * @author Brayan Baquero
 */

public interface ITecnicoService {
	/**
	 * <h2>Descripción:</h2>
	 * Se obtienen los tecnicos activos
	 * @param pageable
	 * @return Lista paginada de técnicos
	 */
	public Page<TecnicoDTO> getTecnicos(Pageable pageable);
	/**
	 * <h2>Descripción:</h2>
	 * Agregar técnico
	 * @param tecnicoDTO
	 */
	public void addTecnico(TecnicoDTO tecnicoDTO);
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar datos de un técnico
	 * @param ident Identificación de usuario (cedula)
	 * @param tecnicoDTO
	 */
	public void updateTecnico(Long ident,TecnicoDTO tecnicoDTO);
	/**
	 * <h2>Descripción:</h2>
	 * Borrar tecnico
	 * @param ident Identificación de usuario (cedula)
	 */
	public void deleteTecnico(Long ident);
	/**
	 * <h2>Descripción:</h2>
	 * @param ident Identificación de usuario (cedula)
	 * @return TecnicoDTO 
	 */
	public TecnicoDTO getTecnicoByIdentificacion(Long ident);
}
